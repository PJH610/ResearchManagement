package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.enums.SubjectMediumState;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import com.zhuoyue.researchManement.service.*;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/medium")
public class SubjectMediumController extends BaseApiController {

    @Autowired
    private SubjectMediumService subjectMediumService;

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    private UnitTreeService unitTreeService;

    @Autowired
    private SubjectSpecialistService subjectSpecialistService;

    @Autowired
    private SubjectFinalApprovalService subjectFinalApprovalService;

    @GetMapping("/list")
    public Map<String, Object> list(String keyword, @RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request) {
        keyword = keyword == null ? null : keyword.trim();

        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<>(subjectMediumService.list(null, keyword, new SubjectMediumState[]{SubjectMediumState.IN_CITY_CHECK}, null)));
            case AREA_RESEARCH:
                break;
            case SCHOOL_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<>(subjectMediumService.list(null, keyword, new SubjectMediumState[]{SubjectMediumState.IN_SCHOOL_CHECK}, new Long[]{currentUser.getUnit().getId()})));
            case SUBJECT_HOST:
                PageHelper.startPage(page_num,page_size);
                return onDataResp(new MyPageInfo<>(subjectMediumService.list(currentUser.getId(), keyword, null, null)));
        }
        return onBadResp("您没有权限查看信息");
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, String changes, Boolean adjust, String adjust_reason, String situation,
                                      BigDecimal funds, BigDecimal supporting_funds, BigDecimal used_funds, String funds_detail,
                                      @RequestParam(required = false) CommonsMultipartFile file, HttpServletRequest request)
    {
        SubjectMedium medium = new SubjectMedium();
        medium.setSubjectId(subject_id);

        if (changes != null) medium.setChanges(changes);
        if (adjust != null) medium.setAdjust(adjust);
        if (adjust_reason != null) medium.setAdjustReason(adjust_reason);
        if (situation != null) medium.setSituation(situation);
        if (funds != null) medium.setFunds(funds);
        if (supporting_funds != null) medium.setSupportingFunds(supporting_funds);
        if (used_funds != null) medium.setUsedFunds(used_funds);
        if (funds_detail != null) medium.setFundsDetail(funds_detail);

        String filePath = null;
        if (file != null && !file.isEmpty()) {
            filePath = fileUploadUtils.getWordPath(file);
            if (filePath == null) return onBadResp("该文件不符合格式");

            medium.setFile(new DownloadFile(filePath, file.getOriginalFilename()));
            downloadFileService.insert(medium.getFile());
        }

        Long currentUserId = UserManager.getUser(request).getId();
        if (subjectMediumService.updateBySubjectId(medium, currentUserId, new SubjectMediumState[]{SubjectMediumState.RETURN_BACk, SubjectMediumState.PRESUBMIT}, null) > 0) {
            if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
            return onSuccessRep("修改成功");
        }
        return onBadResp("修改失败");
    }

    @PostMapping("/submit")
    @RequiresRoles(value = RoleType.SUBJECT_HOST, logical = Logical.OR)
    public Map<String, Object> submit(@RequestParam Long subject_id, HttpServletRequest request) {
        SubjectMedium medium = new SubjectMedium();
        medium.setSubjectId(subject_id);
        medium.setState(SubjectMediumState.IN_SCHOOL_CHECK);

        Long currentUserId = UserManager.getUser(request).getId();
        if (subjectMediumService.updateBySubjectId(medium, currentUserId, new SubjectMediumState[]{SubjectMediumState.RETURN_BACk, SubjectMediumState.PRESUBMIT}, null) > 0) {
            return onSuccessRep("提交成功");
        }
        return onBadResp("提交失败");
    }

    @PostMapping("/check")
    @Transactional
    public Map<String, Object> check(@RequestParam Long subject_id, @RequestParam Boolean check,
                                     @RequestParam String leader_name, @RequestParam String note,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                     Long[] expert_id, HttpServletRequest request) {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("负责人姓名不能为空");
        if (note == null || note.trim().trim().length() == 0) return onBadResp("意见不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");

        SubjectMedium medium = new SubjectMedium();
        medium.setSubjectId(subject_id);

        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                medium.setCityLeaderName(leader_name.trim());
                medium.setCityNote(note);
                medium.setCityCheckTime(check_time);
                medium.setState(check ? SubjectMediumState.COMPLETE : SubjectMediumState.RETURN_BACk);

                if (subjectMediumService.updateBySubjectId(medium, null, new SubjectMediumState[]{SubjectMediumState.IN_CITY_CHECK}, null) > 0) {

                    if (expert_id != null && expert_id.length > 0) {
                        List<SubjectSpecialist> specialists = new ArrayList<>();
                        for (Long userId : expert_id) {
                            SubjectSpecialist specialist = new SubjectSpecialist();
                            specialist.setSubjectId(subject_id);
                            specialist.setUserId(userId);
                            specialist.setType(SubjectSpecialistType.MEDIUM_CHECK);
                            specialists.add(specialist);
                        }
                        subjectSpecialistService.deleteBySubjectId(subject_id, SubjectSpecialistType.MEDIUM_CHECK);
                        subjectSpecialistService.insert(specialists);
                    }

                    SubjectFinal subjectFinal = new SubjectFinal();
                    subjectFinal.setSubjectId(subject_id);
                    subjectFinal.setState(SubjectFinalState.PRESUBMIT);
                    subjectFinalService.insert(subjectFinal);

                    SubjectFinalApproval finalApproval = new SubjectFinalApproval();
                    finalApproval.setSubjectId(subject_id);
                    subjectFinalApprovalService.insert(finalApproval);

                    return onSuccessRep("审核成功");
                }
                break;
            case SCHOOL_RESEARCH:
                medium.setSchoolLeaderName(leader_name.trim());
                medium.setSchoolNote(note);
                medium.setSchoolCheckTime(check_time);
                medium.setState(check ? SubjectMediumState.IN_CITY_CHECK : SubjectMediumState.RETURN_BACk);

                if (subjectMediumService.updateBySubjectId(medium, null, new SubjectMediumState[]{SubjectMediumState.IN_SCHOOL_CHECK}, new Long[]{currentUser.getUnit().getId()}) > 0) {
                    return onSuccessRep("审核成功");
                }
                break;
        }
        return onBadResp("审核失败");
    }
}