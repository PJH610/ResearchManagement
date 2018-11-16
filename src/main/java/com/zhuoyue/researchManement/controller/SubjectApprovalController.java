package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectApproval;
import com.zhuoyue.researchManement.bean.SubjectSpecialist;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectApprovalService;
import com.zhuoyue.researchManement.service.SubjectService;
import com.zhuoyue.researchManement.service.SubjectSpecialistService;
import com.zhuoyue.researchManement.service.UnitTreeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/approval")
public class SubjectApprovalController extends BaseApiController {

    @Autowired
    private SubjectApprovalService subjectApprovalService;

    @Autowired
    private UnitTreeService unitTreeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectSpecialistService specialistService;

    @GetMapping("/show/{subject_id}")
    public Map<String, Object> show(@PathVariable Long subject_id, HttpServletRequest request) {
        User currentUser = UserManager.getUser(request);
        if (currentUser.getRoleType() == RoleTypeEnum.CITY_RESEARCH) return onDataResp(subjectApprovalService.selectBySubjectIdWithSpecialists(subject_id));
        return onDataResp(subjectApprovalService.selectBySubjectId(subject_id));
    }

    @PostMapping("/check")
    @RequiresRoles(value = {RoleType.CITY_RESEARCH, RoleType.AREA_RESEARCH, RoleType.SCHOOL_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> check(@RequestParam Long subject_id, @RequestParam Boolean check,
                                     @RequestParam String leader_name, @RequestParam String note,
                                     @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                     HttpServletRequest request)
    {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("负责人姓名不能为空");
        if (note == null || note.trim().trim().length() == 0) return onBadResp("意见不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");

        SubjectApproval approval = new SubjectApproval();
        approval.setSubjectId(subject_id);

        Subject subject = new Subject();

        User currentUser = UserManager.getUser(request);
        Long[] unitIds;
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:

                approval.setCityLeaderName(leader_name.trim());
                approval.setCityNote(note);
                approval.setCityCheckTime(check_time);

                subject.setState(check ? SubjectState.COMPLETE : SubjectState.RETURN_BACk);

                if (subjectApprovalService.updateBySubjectId(approval, subject,
                        new SubjectState[]{SubjectState.IN_CITY_CHECK}, null) > 0)
                {
                    return onSuccessRep("审核成功");
                }
                break;
            case AREA_RESEARCH:
                approval.setAreaLeaderName(leader_name.trim());
                approval.setAreaNote(note);
                approval.setAreaCheckTime(check_time);

                subject.setState(check ? SubjectState.IN_CITY_CHECK : SubjectState.RETURN_BACk);

                unitIds = unitTreeService.listUnitIdByParentId(UserManager.getUser(request).getUnit().getId());
                if (unitIds.length == 0) return onBadResp("审核失败");
                if (subjectApprovalService.updateBySubjectId(approval, subject,
                        new SubjectState[]{SubjectState.IN_AREA_CHECK}, unitIds) > 0)
                {
                    return onSuccessRep("审核成功");
                }
                break;
            case SCHOOL_RESEARCH:
                approval.setSchoolLeaderName(leader_name.trim());
                approval.setSchoolNote(note);
                approval.setSchoolCheckTime(check_time);

                subject.setState(check ? SubjectState.IN_AREA_CHECK : SubjectState.RETURN_BACk);

                unitIds = new Long[] {currentUser.getUnit().getId()};
                if (subjectApprovalService.updateBySubjectId(approval, subject,
                        new SubjectState[]{SubjectState.IN_SCHOOL_CHECK}, unitIds) > 0)
                {
                    return onSuccessRep("审核成功");
                }
                break;
        }

        initSubjectApproval(subject_id, approval);
        return onBadResp("审核失败");
    }

    @PostMapping("/check/approval")
    @Transactional
    @RequiresRoles(value = {RoleType.CITY_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> checkApproval(@RequestParam Long subject_id, @RequestParam Boolean check,
                                             @RequestParam String leader_name, @RequestParam String note,
                                             @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date check_time,
                                             @RequestParam String project_number, @RequestParam String project_reference)
    {
        if (check == null) return onBadResp("请选择是否推荐");
        if (leader_name == null || leader_name.length() == 0) return onBadResp("单位负责人姓名不能为空");
        if (check_time == null) return onBadResp("签署时间不能为空");
        if (project_number == null || project_number.trim().length() == 0) return onBadResp("立项编号不能为空");
        if (project_reference == null || project_reference.trim().length() == 0) return  onBadResp("立项文号不能为空");
        if (note == null || note.trim().trim().length() == 0) return onBadResp("单位意见不能为空");

        SubjectApproval approval = new SubjectApproval();
        approval.setSubjectId(subject_id);

        Subject subject = new Subject();

        if (check) {
            List<SubjectSpecialist> specialists = specialistService.listApproval(subject_id);

            Boolean canApproval = SubjectManager.canApproval(specialists);
            if (canApproval == null || !canApproval) return onBadResp("不能通过审核");
        }

        approval.setCityLeaderName(leader_name.trim());
        approval.setCityNote(note);
        approval.setCityCheckTime(check_time);

        subject.setState(check ? SubjectState.COMPLETE : SubjectState.RETURN_BACk);
        subject.setProjectNumber(project_number.trim());
        subject.setProjectReference(project_reference.trim());

        if (subjectApprovalService.updateBySubjectId(approval, subject,
                new SubjectState[]{SubjectState.IN_EXPERT_CHECK}, null) > 0)
        {
            return onSuccessRep("审核成功");
        }

        initSubjectApproval(subject_id, approval);
        return onBadResp("审核失败");
    }

    private void initSubjectApproval(Long subjectId, SubjectApproval approval) {
        if (subjectService.selectById(subjectId) != null && subjectApprovalService.selectBySubjectId(subjectId) == null) {
            subjectApprovalService.insert(approval);
        }
    }
}
