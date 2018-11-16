package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.SubjectSpecialist;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/specialist")
public class SubjectSpecialistController extends BaseApiController {

    @Autowired
    private SubjectSpecialistService subjectSpecialistService;

    @GetMapping("/list/check")
    public Map<String, Object> listCheck(@RequestParam(defaultValue ="1")Integer page_num, @RequestParam(defaultValue = "10")Integer page_size, HttpServletRequest request) {
        PageHelper.startPage(page_num,page_size);
        return onDataResp(new MyPageInfo<SubjectSpecialist>(subjectSpecialistService.list(UserManager.getUser(request).getId(), null, false, null)));
    }

    @GetMapping("/list/recheck")
    public Map<String, Object> listRecheck(@RequestParam(defaultValue ="1")Integer page_num, @RequestParam(defaultValue = "10")Integer page_size, HttpServletRequest request) {
        PageHelper.startPage(page_num,page_size);
        return onDataResp(new MyPageInfo<SubjectSpecialist>(subjectSpecialistService.list(UserManager.getUser(request).getId(), null, true, new SubjectState[]{SubjectState.IN_EXPERT_CHECK})));
    }

    @GetMapping("/list/log")
    public Map<String, Object> listLog(@RequestParam(defaultValue ="1")Integer page_num, @RequestParam(defaultValue = "10")Integer page_size, HttpServletRequest request) {
        PageHelper.startPage(page_num,page_size);
        return onDataResp(new MyPageInfo<SubjectSpecialist>(subjectSpecialistService.list(UserManager.getUser(request).getId(), null, true, null)));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectSpecialistService.selectById(id));
    }

    @PostMapping("/check")
    public Map<String, Object> check(@RequestParam Long id, @RequestParam SubjectSpecialistType type,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                     @RequestParam Boolean check_state, @RequestParam String note,
                                     Integer value_score, Integer achievement_score, Integer validity_score, Integer scientific_score, Integer condition_score,
                                     HttpServletRequest request)
    {
        if (check_time == null) return onBadResp("签署时间不能为空");
        if (type == SubjectSpecialistType.SUBJECT_CHECK) {
            if (value_score == null) return onBadResp("选题研究价值不能为空");
            if (achievement_score == null) return onBadResp("研究目标具体有达成度不能为空");
            if (validity_score == null) return onBadResp("研究内容恰当、假设合理不能为空");
            if (scientific_score == null) return onBadResp("研究过程与方法是否科学不能为空");
            if (condition_score == null) return onBadResp("研究基础与研究条件不能为空");
        }
        if (check_state == null) return onBadResp("是否同意推荐不能为空");
        if (note == null || note.trim().length() == 0) return onBadResp("审核意见不能为空");

        SubjectSpecialist specialist = new SubjectSpecialist();
        specialist.setId(id);
        specialist.setCheckTime(check_time);
        specialist.setCheckState(check_state);
        specialist.setNote(note.trim());

        User currentUser = UserManager.getUser(request);
        if (type != null) {
            switch (type) {
                case SUBJECT_CHECK:
                    specialist.setValueScore(value_score);
                    specialist.setAchievementScore(achievement_score);
                    specialist.setValidityScore(validity_score);
                    specialist.setScientificScore(scientific_score);
                    specialist.setConditionScore(condition_score);

                    SubjectState[] states = null;
                    SubjectSpecialist currentSpecialist = subjectSpecialistService.selectById(id);
                    if (currentSpecialist != null && currentSpecialist.getCheckState() != null) states = new SubjectState[]{SubjectState.IN_EXPERT_CHECK};

                    if (subjectSpecialistService.updateById(specialist, currentUser.getId(), type, states) > 0) {
                        return onSuccessRep("评审成功");
                    }
                    break;
                case MEDIUM_CHECK:
                    if (subjectSpecialistService.updateById(specialist, currentUser.getId(), type, null) > 0) {
                        return onSuccessRep("评审成功");
                    }
                    break;
            }
        }
        return onBadResp("审核失败");
    }
}
