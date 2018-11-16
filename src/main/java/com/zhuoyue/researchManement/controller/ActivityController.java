package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.Activity;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.User;
import com.zhuoyue.researchManement.common.UserManager;
import com.zhuoyue.researchManement.enums.ActivityState;
import com.zhuoyue.researchManement.service.ActivityService;
import com.zhuoyue.researchManement.service.SubjectService;
import com.zhuoyue.researchManement.service.UnitTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by 12413 on 2018/5/8.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UnitTreeService unitTreeService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam String theme, @RequestParam Long subject_id, @RequestParam String author, @DateTimeFormat(pattern="yyyy-MM-dd") Date date,
                                   @RequestParam String content)
    {
        if (theme == null || theme.trim().length() == 0) return onBadResp("主题名称不能为空");
        if (subject_id == null) return onBadResp("课题名称不能为空");
        if (author == null || author.trim().length() == 0) return onBadResp("作者名称不能为空");
        if (date == null) return onBadResp("时间不能为空");
        if (content == null || content.trim().length() == 0) return onBadResp("内容不能为空");
        Activity activit = new Activity();
        activit.setTheme(theme.trim());
        activit.setSubjectId(subject_id);
        activit.setAuthor(author.trim());
        activit.setDate(date);
        activit.setContent(content.trim());
        activit.setState(ActivityState.PRESUBMIT);
        activit.setClick(0);
        activityService.insert(activit); return onSuccessRep("添加成功");
    }

    @GetMapping("/list")
    public Map<String, Object> list(String keyword, Long subject_id, @RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request)
    {
        User currentUser = UserManager.getUser(request);
        switch (currentUser.getRoleType()) {
            case CITY_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp( new MyPageInfo<Activity>(activityService.list(keyword, null, null, null, new ActivityState[]{ActivityState.COMPLETE})));
            case AREA_RESEARCH:
                Long[] unitIds = unitTreeService.listUnitIdByParentId(currentUser.getUnit().getId());
                if (unitIds.length == 0) return onDataResp(new MyPageInfo<>(new ArrayList<>()));
                PageHelper.startPage(page_num,page_size);
                return onDataResp( new MyPageInfo<Activity>(activityService.list(keyword, null, null, unitIds, new ActivityState[]{ActivityState.IN_AREA_CHECK})));
            case SCHOOL_RESEARCH:
                PageHelper.startPage(page_num,page_size);
                return onDataResp( new MyPageInfo<Activity>(activityService.list(keyword, null, null, new Long[]{currentUser.getUnit().getId()}, new ActivityState[]{ActivityState.IN_SCHOOL_CHECK})));
            case SUBJECT_HOST:
                PageHelper.startPage(page_num,page_size);
                return onDataResp( new MyPageInfo<Activity>(activityService.list(keyword, subject_id, currentUser.getId(), null, null)));
        }

        return onDataResp(new MyPageInfo<>(new ArrayList<>()));
    }

    @GetMapping("/list/official")
    public Map<String, Object> listOfficial(String keyword, @RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size, HttpServletRequest request)
    {
        PageHelper.startPage(page_num,page_size);
        return onDataResp( new MyPageInfo<Activity>(activityService.list(keyword, null, null, null, new ActivityState[]{ActivityState.COMPLETE})));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> select(@PathVariable Long id)
    {
        return onDataResp(activityService.selectById(id));
    }


    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long id, String theme, Long subject_id, String author, @DateTimeFormat(pattern="yyyy-MM-dd") Date date, String content, HttpServletRequest request) {

        if (theme != null && theme.trim().length() == 0) return onBadResp("主题名称不能为空");
        if (author != null &&  author.trim().length() == 0) return onBadResp("作者名称不能为空");
        if (content != null &&  content.trim().length() == 0) return onBadResp("内容不能为空");

        Activity activit = new Activity();
        activit.setId(id);
        if (theme != null) activit.setTheme(theme.trim());
        if (subject_id != null) activit.setSubjectId(subject_id);
        if (author != null) activit.setAuthor(author.trim());
        if (date != null) activit.setDate(date);
        if (content != null) activit.setContent(content);

        if (activityService.updateById(activit, new ActivityState[]{ActivityState.RETURN_BACk, ActivityState.PRESUBMIT}) > 0) return onSuccessRep("修改成功");
        return onBadResp("修改失败");

    }

    @PostMapping("/check")
    public Map<String, Object> check(@RequestParam Long id, @RequestParam(defaultValue = "true") Boolean check, HttpServletRequest request) {
        Activity activit = new Activity();
        activit.setId(id);
        activit.setState(ActivityState.RETURN_BACk);

        User user = UserManager.getUser(request);
        switch (user.getRoleType()) {
            case CITY_RESEARCH:
                if (check) activit.setState(ActivityState.COMPLETE);
                if (activityService.updateById(activit, new ActivityState[]{ActivityState.IN_CITY_CHECK}) > 0) return onSuccessRep("提交成功");
                break;
            case AREA_RESEARCH:
                if (check) activit.setState(ActivityState.IN_CITY_CHECK);
                if (activityService.updateById(activit, new ActivityState[]{ActivityState.IN_AREA_CHECK}) > 0) return onSuccessRep("提交成功");
                break;
            case SCHOOL_RESEARCH:
                if (check) activit.setState(ActivityState.IN_AREA_CHECK);
                if (activityService.updateById(activit, new ActivityState[]{ActivityState.IN_SCHOOL_CHECK}) > 0) return onSuccessRep("提交成功");
                break;
            case SUBJECT_HOST:
                activit.setState(ActivityState.IN_SCHOOL_CHECK);
                if (activityService.updateById(activit, new ActivityState[]{ActivityState.RETURN_BACk, ActivityState.PRESUBMIT}) > 0) {
                    return onSuccessRep("提交成功");
                }
                break;
        }
        return onBadResp("无法提交");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long id) {
        activityService.deleteById(id);
        return onSuccessRep("删除成功");
    }
}
