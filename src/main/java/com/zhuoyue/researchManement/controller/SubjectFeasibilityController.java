package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.SubjectFeasibility;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.service.SubjectFeasibilityService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/feasibility")
public class SubjectFeasibilityController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectFeasibilityService subjectFeasibilityService;

    @GetMapping ("/show/{subject_id}")
    public Map<String, Object> getId(@PathVariable Long subject_id) {
        return onDataResp(subjectFeasibilityService.selectBySubjectId(subject_id));
    }

    @PostMapping("/update")
    public  Map<String , Object> update(@RequestParam Long subject_id, String achievement,
                                        String task, String requirement, HttpServletRequest request) {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectFeasibility feasibility =  new SubjectFeasibility();
        feasibility.setSubjectId(subject_id);
        if (achievement != null) feasibility.setAchievement(achievement);
        if (task != null) feasibility.setTask(task);
        if (requirement != null) feasibility.setRequirement(requirement);

        if (feasibility.getAchievement() == null && feasibility.getTask() == null && feasibility.getRequirement() == null) {
            return onBadResp("参数不能为空");
        }

        if (subjectFeasibilityService.updateBySubjectId(feasibility) == 0) {
            subjectFeasibilityService.insert(feasibility);
        }
        return onSuccessRep("更新成功");
    }

}
