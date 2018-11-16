package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectSchedule;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.SubjectScheduleService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/schedule")
public class SubjectScheduleController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectScheduleService subjectScheduleService;

    @PostMapping("/add")
    public Map<String,Object> add(@RequestParam Long subject_id, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
                                  @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")Date end_time, @RequestParam String content,
                                  @RequestParam String name, @RequestParam String host, HttpServletRequest request) {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");

        if (subject_id == null) return onBadResp("课题不能为空");
        if (start_time == null) return onBadResp("开始时间不能为空");
        if (end_time == null) return onBadResp("结束时间不能为空");
        if (content == null || content.trim().length() == 0) return onBadResp("工作内容不能为空");
        if (name == null || name.trim().length() == 0) return onBadResp("名称不能为空");
        if (host == null || host.trim().length() == 0) return onBadResp("负责人不能为空");

        SubjectSchedule schedule = new SubjectSchedule();
        schedule.setSubjectId(subject_id);
        schedule.setStartTime(start_time);
        schedule.setEndTime(end_time);
        schedule.setContent(content.trim());
        schedule.setName(name.trim());
        schedule.setHost(host.trim());

        subjectScheduleService.insert(schedule);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        return onDataResp(subjectScheduleService.listBySubjectId(subject_id));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectScheduleService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestParam Long subject_id, @RequestParam Long id, @DateTimeFormat(pattern="yyyy-MM-dd") Date start_time,
                                     @DateTimeFormat(pattern="yyyy-MM-dd")Date end_time, String content, String name,String host, HttpServletRequest request) {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (start_time == null) return onBadResp("开始时间不能为空");
        if (end_time == null) return onBadResp("结束时间不能为空");
        if (content == null || content.trim().length() == 0) return onBadResp("工作内容不能为空");
        if (name == null || name.trim().length() == 0) return onBadResp("名称不能为空");
        if (host == null || host.trim().length() == 0) return onBadResp("负责人不能为空");

        SubjectSchedule schedule = new SubjectSchedule();
        schedule.setId(id);
        schedule.setSubjectId(subject_id);
        if (start_time != null) schedule.setStartTime(start_time);
        if (end_time != null) schedule.setEndTime(end_time);
        if (content != null) schedule.setContent(content.trim());
        if (name != null) schedule.setName(name.trim());
        if (host != null) schedule.setHost(host.trim());

        if (schedule.getStartTime() == null && schedule.getEndTime() == null
                && schedule.getContent() == null && schedule.getName() == null && schedule.getHost() == null)
        {
            return onBadResp("参数不能为空");
        }

        subjectScheduleService.updateById(schedule);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request){
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectScheduleService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}
