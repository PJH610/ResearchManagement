package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectPersonnel;
import com.zhuoyue.researchManement.common.SubjectFinalManager;
import com.zhuoyue.researchManement.service.SubjectFinalService;
import com.zhuoyue.researchManement.service.SubjectPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/final/personnel")
public class SubjectFinalPersonnelController extends BaseApiController {

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private SubjectPersonnelService subjectPersonnelService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long subject_id, @RequestParam String name, @RequestParam Integer age, @RequestParam String unit,
                                   @RequestParam String task, @RequestParam String position, @RequestParam String education,
                                   HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");

        if (name == null || name.trim().length() == 0) return onBadResp("姓名不能为空");
        if (age == null) return onBadResp("年龄不能为空");
        if (unit == null || unit.trim().length() == 0) return onBadResp("单位不能为空");
        if (task == null || task.trim().length() == 0) return onBadResp("课题所承担工作不能为空");
        if (position == null || position.trim().length() == 0) return onBadResp("职务不能为空");
        if (education == null || education.trim().length() == 0) return onBadResp("学历不能为空");

        SubjectPersonnel personnel = new SubjectPersonnel();
        personnel.setSubjectId(manager.getSubjectFinal().getSubjectId());
        personnel.setName(name.trim());
        personnel.setAge(age);
        personnel.setUnit(unit.trim());
        personnel.setTask(task.trim());
        personnel.setPosition(position.trim());
        personnel.setEducation(education.trim());

        subjectPersonnelService.insert(personnel);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        List<SubjectPersonnel> list = null;
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (manager.getSubjectFinal() != null) subjectPersonnelService.listBySubjectId(manager.getSubjectFinal().getSubjectId());
        return onDataResp(list);
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectPersonnelService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, String name, String task,
                                      Integer age, String unit, String position, String education, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (name != null && name.trim().length() == 0) return onBadResp("姓名不能为空");
        if (unit != null && unit.trim().length() == 0) return onBadResp("单位不能为空");
        if (task != null && task.trim().length() == 0) return onBadResp("课题所承担工作不能为空");
        if (position != null && position.trim().length() == 0) return onBadResp("职务不能为空");
        if (education != null && education.trim().length() == 0) return onBadResp("学历不能为空");

        SubjectPersonnel personnel = new SubjectPersonnel();
        personnel.setId(id);
        personnel.setSubjectId(manager.getSubjectFinal().getSubjectId());
        if (name != null) personnel.setName(name.trim());
        if (age != null) personnel.setAge(age);
        if (unit != null) personnel.setUnit(unit.trim());
        if (task != null) personnel.setTask(task.trim());
        if (position != null) personnel.setPosition(position.trim());
        if (education != null) personnel.setEducation(education.trim());

        if (personnel.getName() == null && personnel.getAge() == null && personnel.getUnit() == null
                && personnel.getTask() == null && personnel.getPosition() == null && personnel.getEducation() == null) {
            return onBadResp("参数不能为空");
        }

        subjectPersonnelService.updateById(personnel);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request) {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectPersonnelService.deleteById(manager.getSubjectFinal().getSubjectId(), id);
        return onSuccessRep("删除成功");
    }
}
