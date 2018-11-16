package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.service.*;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/final/approval")
public class SubjectFinalApprovalController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private SubjectFinalApprovalService subjectFinalApprovalService;

    @Autowired
    private UnitTreeService unitTreeService;

    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Autowired
    private DownloadFileService downloadFileService;

    @GetMapping("/show/{subject_id}")
    public Map<String, Object> show(@PathVariable Long subject_id) {
        return onDataResp(subjectFinalApprovalService.selectBySubjectId(subject_id));
    }

    @PostMapping("/check")
    @RequiresRoles(value = {RoleType.CITY_RESEARCH, RoleType.SCHOOL_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> check(@RequestParam Long subject_id, @RequestParam Boolean check,
                                     @RequestParam String leader_name, @RequestParam String note,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                     HttpServletRequest request)
    {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("负责人姓名不能为空");
        if (note == null || note.trim().trim().length() == 0) return onBadResp("意见不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");

        SubjectFinalApproval finalApproval = new SubjectFinalApproval();
        finalApproval.setSubjectId(subject_id);

        SubjectFinal subjectFinal = new SubjectFinal();

        User currentUser = UserManager.getUser(request);
        Long[] unitIds;
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                finalApproval.setFirstTrialLeaderName(leader_name.trim());
                finalApproval.setFirstTrialOpinion(note);
                finalApproval.setFirstTrialCheck(check);
                finalApproval.setFirstTrialCheckTime(check_time);

                subjectFinal.setState(check ? SubjectFinalState.IN_CITY_CHECK : SubjectFinalState.RETURN_BACk);

                if (subjectFinalApprovalService.updateBySubjectId(finalApproval, subjectFinal,
                        new SubjectFinalState[]{SubjectFinalState.IN_CITY_CHECK}, null) > 0)
                {
                    return onSuccessRep("审核成功");
                }
                break;
            case SCHOOL_RESEARCH:
                finalApproval.setSchoolLeaderName(leader_name.trim());
                finalApproval.setSchoolNote(note);
                finalApproval.setSchoolCheck(check);
                finalApproval.setSchoolCheckTime(check_time);

                subjectFinal.setState(check ? SubjectFinalState.IN_CITY_CHECK : SubjectFinalState.RETURN_BACk);

                unitIds = new Long[] {currentUser.getUnit().getId()};
                if (subjectFinalApprovalService.updateBySubjectId(finalApproval, subjectFinal,
                        new SubjectFinalState[]{SubjectFinalState.IN_SCHOOL_CHECK}, unitIds) > 0)
                {
                    return onSuccessRep("审核成功");
                }
                break;
        }

        initSubjectFinal(subject_id, finalApproval);
        return onBadResp("审核失败");
    }

    @PostMapping("/check/approval")
    @Transactional
    @RequiresRoles(value = {RoleType.CITY_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> checkApproval(@RequestParam Long subject_id, @RequestParam Boolean check, @RequestParam(required = false) CommonsMultipartFile file) {
        if (check == null) return onBadResp("请选择是否推荐");
        if (check && (file == null || file.isEmpty())) return onBadResp("文件不能为空");

        SubjectFinalApproval finalApproval = new SubjectFinalApproval();
        finalApproval.setSubjectId(subject_id);

        SubjectFinal subjectFinal = new SubjectFinal();
        subjectFinal.setState(check ? SubjectFinalState.PASS_EXPERT_CHECK : SubjectFinalState.RETURN_BACk);

        String filePath = null;
        if (check) {
            filePath = fileUploadUtils.getWordPath(file);
            if (filePath == null) return onBadResp("该文件不符合格式");

            finalApproval.setExpertOpinion(new DownloadFile(filePath, file.getOriginalFilename()));
            downloadFileService.insert(finalApproval.getExpertOpinion());
        }

        if (subjectFinalApprovalService.updateBySubjectId(finalApproval, subjectFinal, new SubjectFinalState[] {SubjectFinalState.IN_CITY_CHECK}, null) > 0) {
            fileUploadUtils.saveFile(file, filePath);
            return onSuccessRep("上传成功");
        }

        initSubjectFinal(subject_id, finalApproval);
        return onBadResp("上传失败");
    }

    @PostMapping("/check/firsttrial")
    @RequiresRoles(value = {RoleType.CITY_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> checkFirstTrial(@RequestParam Long subject_id, @RequestParam Boolean check,
                                     @RequestParam String leader_name, @RequestParam String opinion,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                     HttpServletRequest request)
    {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("负责人姓名不能为空");
        if (opinion == null || opinion.trim().trim().length() == 0) return onBadResp("意见不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");

        SubjectFinalApproval finalApproval = new SubjectFinalApproval();
        finalApproval.setSubjectId(subject_id);
        finalApproval.setFirstTrialLeaderName(leader_name.trim());
        finalApproval.setFirstTrialOpinion(opinion);
        finalApproval.setFirstTrialCheck(check);
        finalApproval.setFirstTrialCheckTime(check_time);

        SubjectFinal subjectFinal = new SubjectFinal();
        subjectFinal.setState(check ? SubjectFinalState.PASS_FIRSTTRIAL : SubjectFinalState.RETURN_BACk);

        if (subjectFinalApprovalService.updateBySubjectId(finalApproval, subjectFinal,
                new SubjectFinalState[]{SubjectFinalState.PASS_EXPERT_CHECK}, null) > 0)
        {
            return onSuccessRep("审核成功");
        }

        initSubjectFinal(subject_id, finalApproval);
        return onBadResp("审核失败");
    }

    @PostMapping("/check/finaltrial")
    @Transactional
    @RequiresRoles(value = {RoleType.CITY_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> checkFinalTrial(@RequestParam Long subject_id, @RequestParam Boolean check,
                                               @RequestParam String leader_name, @RequestParam String opinion,
                                               @RequestParam String conclusion_number,
                                               @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                               HttpServletRequest request)
    {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("负责人姓名不能为空");
        if (opinion == null || opinion.trim().trim().length() == 0) return onBadResp("意见不能为空");
        if (conclusion_number == null || conclusion_number.trim().trim().length() == 0) return onBadResp("证书编号不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");

        SubjectFinalApproval finalApproval = new SubjectFinalApproval();
        finalApproval.setSubjectId(subject_id);
        finalApproval.setFinalTrialLeaderName(leader_name.trim());
        finalApproval.setFinalTrialOpinion(opinion);
        finalApproval.setFinalTrialCheck(check);
        finalApproval.setFinalTrialCheckTime(check_time);

        SubjectFinal subjectFinal = new SubjectFinal();
        subjectFinal.setState(check ? SubjectFinalState.COMPLETE : SubjectFinalState.RETURN_BACk);

        if (subjectFinalApprovalService.updateBySubjectId(finalApproval, subjectFinal,
                new SubjectFinalState[]{SubjectFinalState.PASS_FIRSTTRIAL}, null) > 0)
        {
            Subject subject = new Subject();
            subject.setId(subject_id);
            subject.setConclusionNumber(conclusion_number.trim());
            subjectService.updateById(subject, null, null, null);

            return onSuccessRep("审核成功");
        }

        initSubjectFinal(subject_id, finalApproval);
        return onBadResp("审核失败");
    }


    private void initSubjectFinal(Long subjectId, SubjectFinalApproval finalApproval) {
        if (subjectFinalService.selectBySubjectId(subjectId) != null && subjectFinalApprovalService.selectBySubjectId(subjectId) == null) {
            subjectFinalApprovalService.insert(finalApproval);
        }
    }
}
