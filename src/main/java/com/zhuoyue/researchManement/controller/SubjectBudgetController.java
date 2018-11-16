package com.zhuoyue.researchManement.controller;

import com.github.pagehelper.PageHelper;
import com.zhuoyue.researchManement.bean.MyPageInfo;
import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectBudget;
import com.zhuoyue.researchManement.exception.BadRequestException;
import com.zhuoyue.researchManement.service.SubjectBudgetService;
import com.zhuoyue.researchManement.util.CounterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by SX-503 on 2018/5/23.
 */
@RestController
@RequestMapping("/api/subject/budget")
public class SubjectBudgetController extends BaseApiController {

    @Autowired
    SubjectBudgetService subjectBudgetService;

//    @PostMapping("/add")
//    public Map<String, Object> add( @RequestParam Long subject,@RequestParam String year,@RequestParam BigDecimal money) {
//        if (subject == null) return onBadResp("subject 不能为空");
//        if (year == null || year.trim().length() == 0) return onBadResp("year 不能为空");
//        if (money == null) return onBadResp("money 不能为空");
//
//        SubjectBudget subjectBudget = new SubjectBudget();
//        subjectBudget.setSubject(subject);
//        subjectBudget.setYear(year.trim());
//        subjectBudget.setMoney(money);
//
//        if (subjectBudgetService.insert(subjectBudget) > 0) return onSuccessRep("添加成功");
//        throw new BadRequestException("添加失败");
//    }
//
//    @GetMapping("/list")
//    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page_num, @RequestParam(defaultValue = "10") Integer page_size) {
//        PageHelper.startPage(page_num,page_size);
//        return onDataResp(new MyPageInfo<SubjectBudget>(subjectBudgetService.list()));
//    }
//
//    @GetMapping("/show/{id}")
//    public Map<String, Object> show(@PathVariable Long id) {
//        return onDataResp(subjectBudgetService.selectById(id));
//    }
//
//    @PostMapping("/update")
//    public Map<String, Object> update(@RequestParam Long id, String year, BigDecimal money) {
//        if (year == null && year.trim().length() == 0) return onBadResp("year 不能为空");
//        if (money == null) return onBadResp("money 不能为空");
//
//        SubjectBudget subjectBudget = new SubjectBudget();
//        subjectBudget.setId(id);
//        subjectBudget.setYear(year.trim());
//        subjectBudget.setMoney(money);
//
//        if (subjectBudgetService.updateById(subjectBudget) > 0) return onSuccessRep("修改成功");
//        throw new BadRequestException("修改失败");
//    }
//
//    @GetMapping("/delete")
//    public Map<String, Object> delete(@RequestParam Long[] id ) {
//        if(subjectBudgetService.deleteById(id) > 0) return onSuccessRep("删除成功");
//        throw new BadRequestException("删除失败");
//    }

}
