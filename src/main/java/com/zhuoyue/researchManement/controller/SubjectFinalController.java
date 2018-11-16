package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.DownloadFile;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.SubjectFinalManager;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.enums.SubjectFinalType;
import com.zhuoyue.researchManement.service.DownloadFileService;
import com.zhuoyue.researchManement.service.SubjectFinalService;
import com.zhuoyue.researchManement.util.FileUploadUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/final")
public class SubjectFinalController extends BaseApiController {

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private DownloadFileService downloadFileService;

    @Autowired
    FileUploadUtils fileUploadUtils;

    @GetMapping("/list")
    public Map<String, Object> list() {
        return onDataResp(new MyPageInfo<>(subjectFinalService.list()));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @DateTimeFormat(pattern="yyyy-MM-dd") Date planning_time, String planning_form,
                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date end_time, @DateTimeFormat(pattern="yyyy-MM-dd") Date acceptance_time,
                                      SubjectFinalType type, String achievement, String content, String situation, String changes,
                                      @RequestParam(required = false) CommonsMultipartFile file, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectFinal subjectFinal = new SubjectFinal();
        subjectFinal.setSubjectId(subject_id);
        if (planning_time != null) subjectFinal.setPlanningTime(planning_time);
        if (planning_form != null) subjectFinal.setPlanningForm(planning_form.trim());
        if (end_time != null) subjectFinal.setEndTime(end_time);
        if (acceptance_time != null) subjectFinal.setAcceptanceTime(acceptance_time);
        if (type != null) subjectFinal.setType(type);
        if (achievement != null) subjectFinal.setAchievement(achievement);
        if (content != null) subjectFinal.setContent(content);
        if (situation != null) subjectFinal.setSituation(situation);
        if (changes != null) subjectFinal.setChanges(changes);

        String filePath = "";
        if (file != null && !file.isEmpty()) {
            filePath = fileUploadUtils.getWordPath(file);
            if (filePath == null) return onBadResp("该文件不符合格式");

            subjectFinal.setFile(new DownloadFile(filePath, file.getOriginalFilename()));

            downloadFileService.insert(subjectFinal.getFile());
        }

        if (subjectFinal.getPlanningTime() == null && subjectFinal.getPlanningForm() == null && subjectFinal.getEndTime() == null
                && subjectFinal.getAcceptanceTime() == null && subjectFinal.getType() == null && subjectFinal.getAchievement() == null
                && subjectFinal.getContent() == null && subjectFinal.getSituation() == null && subjectFinal.getChanges() == null
                && subjectFinal.getFile() == null)
        {
            return onBadResp("参数不能为空");
        }

        User currentUser = UserManager.getUser(request);
        if (subjectFinalService.updateBySubjectId(subjectFinal, new SubjectFinalState[]{SubjectFinalState.RETURN_BACk, SubjectFinalState.PRESUBMIT}, currentUser.getId(), null) > 0) {
            if (StringUtils.isNotEmpty(filePath)) fileUploadUtils.saveFile(file, filePath);
            return onSuccessRep("修改成功");
        }

        return onBadResp("修改失败");
    }

    @PostMapping("/submit")
    @RequiresRoles(value = RoleType.SUBJECT_HOST, logical = Logical.OR)
    public Map<String, Object> submit(@RequestParam Long subject_id, HttpServletRequest request) {
        SubjectFinal subjectFinal = new SubjectFinal();
        subjectFinal.setSubjectId(subject_id);
        subjectFinal.setState(SubjectFinalState.IN_SCHOOL_CHECK);

        Long currentUserId = UserManager.getUser(request).getId();
        if (subjectFinalService.updateBySubjectId(subjectFinal, new SubjectFinalState[]{SubjectFinalState.RETURN_BACk, SubjectFinalState.PRESUBMIT}, currentUserId, null) > 0) {
            return onSuccessRep("提交成功");
        }
        return onBadResp("提交失败");
    }
}
