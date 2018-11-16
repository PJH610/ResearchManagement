package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectResult;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.SubjectResultService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/result")
public class SubjectResultController extends  BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectResultService subjectResultService;

    @PostMapping("/add")
    public Map<String,Object> add(@RequestParam Long subject_id, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date completetime,
                                  @RequestParam String finalresult_name, @RequestParam String finalresult_shape, @RequestParam String principal,
                                  HttpServletRequest request) {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");

        if (principal == null || principal.trim().length() == 0) return onBadResp("负责人不能为空");
        if (completetime == null) return onBadResp("完成时间不能为空");
        if (finalresult_name == null || finalresult_name.trim().length() == 0) return onBadResp("最终成果名称不能为空");
        if (finalresult_shape == null || finalresult_shape.trim().length() == 0) return onBadResp("最终成果形式不能为空");

        SubjectResult subjectResult = new SubjectResult();
        subjectResult.setSubjectId(subject_id);
        subjectResult.setCompletetime(completetime);
        subjectResult.setFinalresultName(finalresult_name.trim());
        subjectResult.setFinalresultShape(finalresult_shape.trim());
        subjectResult.setPrincipal(principal.trim());

        subjectResultService.insert(subjectResult);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        return onDataResp(subjectResultService.list(subject_id));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectResultService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, @DateTimeFormat(pattern = "yyyy-MM-dd") Date completetime,
                                      String finalresult_name, String finalresult_shape, String principal,
                                      HttpServletRequest request)
    {
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        if (principal == null || principal.trim().length() == 0) return onBadResp("负责人不能为空");
        if (completetime == null) return onBadResp("完成时间不能为空");
        if (finalresult_name == null || finalresult_name.trim().length() == 0) return onBadResp("最终成果名称不能为空");
        if (finalresult_shape == null || finalresult_shape.trim().length() == 0) return onBadResp("最终成果形式不能为空");

        SubjectResult subjectResult = new SubjectResult();
        subjectResult.setId(id);
        subjectResult.setSubjectId(subject_id);
        if (completetime != null) subjectResult.setCompletetime(completetime);
        if(finalresult_name != null) subjectResult.setFinalresultName(finalresult_name.trim());
        if (finalresult_shape != null) subjectResult.setFinalresultShape(finalresult_shape.trim());
        if (principal != null) subjectResult.setPrincipal(principal.trim());

        if (subjectResult.getCompletetime() == null && subjectResult.getFinalresultName() == null
                && subjectResult.getFinalresultShape() == null && subjectResult.getPrincipal() == null) {
            return onBadResp("参数不能为空");
        }

        subjectResultService.updateById(subjectResult);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request){
        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectResultService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}



















