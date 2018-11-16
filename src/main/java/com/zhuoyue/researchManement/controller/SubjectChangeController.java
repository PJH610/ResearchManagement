package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectChange;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.RoleType;
import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import com.zhuoyue.researchManement.enums.SubjectChangeState;
import com.zhuoyue.researchManement.enums.SubjectChangeType;
import com.zhuoyue.researchManement.service.SubjectChangeService;
import com.zhuoyue.researchManement.service.SubjectService;
import com.zhuoyue.researchManement.service.UnitTreeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/change")
public class SubjectChangeController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectChangeService subjectChangeService;

    @Autowired
    private UnitTreeService unitTreeService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long subject_id, @RequestParam SubjectChangeType type, Long user_id,
                                   @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time, String finalresult)
    {
        if (subject_id == null) return onBadResp("课题id不能为空");
        if (type == null) return onBadResp("变更类型不能为空");

        SubjectChange change = new SubjectChange();
        change.setSubjectId(subject_id);
        change.setType(type);
        change.setState(SubjectChangeState.IN_SCHOOL_CHECK);
        switch (type) {
            case CHANGE_HOST:
                if (user_id == null) return onBadResp("新负责人不能为空");
                change.setUserId(user_id);
                break;
            case CHANGE_COMPLETE_TIME:
                if (complete_time == null) return onBadResp("新完成时间不能为空");
                change.setCompleteTime(complete_time);
                break;
            case CHANGE_FINALRESULT:
                if (finalresult == null || finalresult.trim().length() == 0) return onBadResp("新预期成果不能为空");
                change.setFinalresult(finalresult);
                break;
        }

        subjectChangeService.insert(change);
        return onSuccessRep("提交成功");
    }

    @GetMapping("/list/subject")
    public Map<String, Object> listSubject(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size)
    {
        PageHelper.startPage(page_num,page_size);
        return onDataResp(new MyPageInfo<Subject>(subjectService.listChange(null, null, null)));
    }

    @GetMapping("/list/{subject_id}")
    @RequiresRoles(value = RoleType.SUBJECT_HOST, logical = Logical.OR)
    public Map<String, Object> listBySubjectId(@PathVariable Long subject_id) {
        return onDataResp(subjectChangeService.list(subject_id, null, null));
    }

    @GetMapping("/list")
    @RequiresRoles(value = {RoleType.CITY_RESEARCH, RoleType.AREA_RESEARCH, RoleType.SCHOOL_RESEARCH}, logical = Logical.OR)
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request)
    {
        Long[] unitIds = null;
        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                PageHelper.startPage(page_num, page_size);
                return onDataResp(new MyPageInfo<SubjectChange>(subjectChangeService.list(null, new SubjectChangeState[]{SubjectChangeState.IN_CITY_CHECK}, null)));
            case AREA_RESEARCH:
                unitIds = unitTreeService.listUnitIdByParentId(currentUser.getUnit().getId());
                PageHelper.startPage(page_num, page_size);
                return onDataResp(new MyPageInfo<SubjectChange>(subjectChangeService.list(null, new SubjectChangeState[]{SubjectChangeState.IN_AREA_CHECK}, unitIds)));
            case SCHOOL_RESEARCH:
                unitIds = new Long[]{currentUser.getUnit().getId()};
                if (unitIds.length == 0) return onDataResp(new MyPageInfo<>(new ArrayList<>()));
                PageHelper.startPage(page_num, page_size);
                return onDataResp(new MyPageInfo<SubjectChange>(subjectChangeService.list(null, new SubjectChangeState[]{SubjectChangeState.IN_SCHOOL_CHECK}, unitIds)));
        }
        return null;
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, @RequestParam SubjectChangeType type, Long user_id,
                                      @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time, String finalresult, HttpServletRequest request)
    {
        if (type == null) return onBadResp("变更类型不能为空");

        SubjectChange change = new SubjectChange();
        change.setId(id);
        change.setSubjectId(subject_id);
        change.setType(type);
        change.setState(SubjectChangeState.IN_SCHOOL_CHECK);
        switch (type) {
            case CHANGE_HOST:
                if (user_id == null) return onBadResp("新负责人不能为空");
                change.setUserId(user_id);
                break;
            case CHANGE_COMPLETE_TIME:
                if (complete_time == null) return onBadResp("新完成时间不能为空");
                change.setCompleteTime(complete_time);
                break;
            case CHANGE_FINALRESULT:
                if (finalresult == null || finalresult.trim().length() == 0) return onBadResp("新预期成果不能为空");
                change.setFinalresult(finalresult);
                break;
        }

        if (subjectChangeService.updateById(change, new SubjectChangeState[]{SubjectChangeState.PRESUBMIT, SubjectChangeState.IN_SCHOOL_CHECK}) > 0) {
            return onSuccessRep("修改成功");
    }
        return onBadResp("无法修改");
    }

    @PostMapping("/check")
    @Transactional
    public Map<String, Object> check(@RequestParam Long subject_id, @RequestParam Long id, @RequestParam Boolean check,
                                     @RequestParam SubjectChangeType type, HttpServletRequest request) {
        if (check == null) return onBadResp("是否同意不能为空");

        SubjectChange change = new SubjectChange();
        change.setId(id);
        change.setSubjectId(subject_id);
        change.setType(type);

        SubjectChangeState[] currentChangeState = null;

        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                change.setState(check ? SubjectChangeState.COMPLETE : SubjectChangeState.RETURN_BACk);
                currentChangeState = new SubjectChangeState[]{SubjectChangeState.IN_CITY_CHECK};
                break;
            case AREA_RESEARCH:
                change.setState(check ? SubjectChangeState.IN_CITY_CHECK : SubjectChangeState.RETURN_BACk);
                currentChangeState = new SubjectChangeState[]{SubjectChangeState.IN_AREA_CHECK};
                break;
            case SCHOOL_RESEARCH:
                change.setState(check ? SubjectChangeState.IN_AREA_CHECK : SubjectChangeState.RETURN_BACk);
                currentChangeState = new SubjectChangeState[]{SubjectChangeState.IN_SCHOOL_CHECK};
                break;
            default:
                return onBadResp("审核异常");
        }

        if (subjectChangeService.updateById(change, currentChangeState) > 0) {
            if (currentUser.getRoleType() == RoleTypeEnum.CITY_RESEARCH) {
                SubjectChange currentChange = subjectChangeService.selectById(id);
                Subject subject = new Subject();
                subject.setId(subject_id);
                switch (type) {
                    case CHANGE_HOST:
                        subject.setUserId(currentChange.getUserId());
                        break;
                    case CHANGE_COMPLETE_TIME:
                        subject.setCompleteTime(currentChange.getCompleteTime());
                        break;
                    case CHANGE_FINALRESULT:
                        subject.setFinalresult(currentChange.getFinalresult());
                        break;
                }
                subjectService.updateById(subject, null,  null, null);
            }

            return onSuccessRep("审核成功");
        }
        return onBadResp("无法审核");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id) {
        subjectChangeService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}
