package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectFinalAchievement;
import com.zhuoyue.researchManement.common.SubjectFinalManager;
import com.zhuoyue.researchManement.service.SubjectFinalAchievementService;
import com.zhuoyue.researchManement.service.SubjectFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/final/achievement")
public class SubjectFinalAchievementController extends BaseApiController {

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private SubjectFinalAchievementService subjectFinalAchievementService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long subject_id, @RequestParam String name, @RequestParam String author, @RequestParam String form,
                                   @RequestParam Integer word_number, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time,
                                   @RequestParam String journal, @RequestParam String situation, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");

        if (name == null || name.trim().length() == 0) return onBadResp("成果名称不能为空");
        if (author == null || author.trim().length() == 0) return onBadResp("作者不能为空");
        if (form == null || form.trim().length() == 0) return onBadResp("形式不能为空");
        if (word_number == null) return onBadResp("字数不能为空");
        if (complete_time == null) return onBadResp("完成年月不能为空");
        if (journal == null || journal.trim().length() == 0) return onBadResp("出版单位或者发表刊物、刊号不能为空");
        if (situation == null || situation.trim().length() == 0) return onBadResp("获奖、转载、引用和应用情况不能为空");


        SubjectFinalAchievement finalAchievement = new SubjectFinalAchievement();
        finalAchievement.setSubjectId(subject_id);
        finalAchievement.setName(name.trim());
        finalAchievement.setAuthor(author.trim());
        finalAchievement.setForm(form.trim());
        finalAchievement.setWordNumber(word_number);
        finalAchievement.setCompleteTime(complete_time);
        finalAchievement.setJournal(journal);
        finalAchievement.setSituation(situation);

        subjectFinalAchievementService.insert(finalAchievement);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        return onDataResp(subjectFinalAchievementService.listBySubjectId(subject_id));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectFinalAchievementService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, String name, String author, String form,
                                      Integer word_number, @DateTimeFormat(pattern="yyyy-MM-dd") Date complete_time,
                                      String journal, String situation, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (name != null && name.trim().length() == 0) return onBadResp("成果名称不能为空");
        if (author != null && author.trim().length() == 0) return onBadResp("作者不能为空");
        if (form != null && form.trim().length() == 0) return onBadResp("形式不能为空");
        if (journal != null && journal.trim().length() == 0) return onBadResp("出版单位或者发表刊物、刊号不能为空");
        if (situation == null || situation.trim().length() == 0) return onBadResp("获奖、转载、引用和应用情况不能为空");

        SubjectFinalAchievement finalAchievement = new SubjectFinalAchievement();
        finalAchievement.setId(id);
        finalAchievement.setSubjectId(subject_id);
        if (name != null) finalAchievement.setName(name.trim());
        if (author != null) finalAchievement.setAuthor(author.trim());
        if (form != null) finalAchievement.setForm(form.trim());
        if (journal != null) finalAchievement.setWordNumber(word_number);
        if (complete_time != null) finalAchievement.setCompleteTime(complete_time);
        if (journal != null) finalAchievement.setJournal(journal);
        if (situation != null) finalAchievement.setSituation(situation);

        if (finalAchievement.getName() == null && finalAchievement.getAuthor() == null && finalAchievement.getForm() == null
                && finalAchievement.getJournal() == null && finalAchievement.getCompleteTime() == null
                && finalAchievement.getJournal() == null && finalAchievement.getSituation() == null)
        {
            return onBadResp("参数不能为空");
        }

        subjectFinalAchievementService.updateById(finalAchievement);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request) {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectFinalAchievementService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}
