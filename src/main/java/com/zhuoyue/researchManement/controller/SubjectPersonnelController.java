package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectPersonnel;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.SubjectPersonnelService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/personnel")
public class SubjectPersonnelController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectPersonnelService subjectPersonnelService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long subject_id, @RequestParam String name,
                                   @RequestParam Integer age, @RequestParam String unit,
                                   @RequestParam String task, @RequestParam String position,
                                   @RequestParam String education, HttpServletRequest request)
    {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");


        if (subject_id == null) return onBadResp("课题不能为空");
        if (name == null || name.trim().length() == 0) return onBadResp("参与者姓名不能为空");
        if (age == null) return onBadResp("年龄不能为空");
        if (unit == null || unit.trim().length() == 0) return onBadResp("工作单位不能为空");
        if (task == null || task.trim().length() == 0) return onBadResp("任务分工不能为空");
        if (position == null || position.trim().length() == 0) return onBadResp("职务不能为空");
        if (education == null || education.trim().length() == 0) return onBadResp("学历不能为空");

        SubjectPersonnel subjectPersonnel = new SubjectPersonnel();
        subjectPersonnel.setSubjectId(subject_id);
        subjectPersonnel.setName(name.trim());
        subjectPersonnel.setAge(age);
        subjectPersonnel.setUnit(unit.trim());
        subjectPersonnel.setTask(task.trim());
        subjectPersonnel.setPosition(position.trim());
        subjectPersonnel.setEducation(education.trim());

        subjectPersonnelService.insert(subjectPersonnel);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        return onDataResp(subjectPersonnelService.listBySubjectId(subject_id));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectPersonnelService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, String name, String task,
                                      Integer age, String unit, String position, String education, HttpServletRequest request)
    {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (name != null && name.trim().length() == 0) return onBadResp("参与者姓名不能为空");
        if (task != null && task.trim().length() == 0) return onBadResp("任务分工不能为空");
        if (position != null && position.trim().length() == 0) return onBadResp("职务不能为空");
        if (education != null && education.trim().length() == 0) return onBadResp("学历不能为空");

        SubjectPersonnel personnel = new SubjectPersonnel();
        personnel.setId(id);
        personnel.setSubjectId(subject_id);
        if (name != null) personnel.setName(name.trim());
        if (age != null) personnel.setAge(age);
        if (unit != null) personnel.setUnit(unit.trim());
        if (task != null) personnel.setTask(task.trim());
        if (position != null) personnel.setPosition(position.trim());
        if (education != null) personnel.setEducation(education.trim());

        if (personnel.getName() == null && personnel.getAge() == null && personnel.getUnit() == null
                && personnel.getTask() == null && personnel.getPosition() == personnel.getEducation()) {
            return onBadResp("参数不能为空");
        }

        subjectPersonnelService.updateById(personnel);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request) {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectPersonnelService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}
