package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectFinalFund;
import com.zhuoyue.researchManement.common.SubjectFinalManager;
import com.zhuoyue.researchManement.service.SubjectFinalFundService;
import com.zhuoyue.researchManement.service.SubjectFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/subject/final/fund")
public class SubjectFinalFundController extends BaseApiController {

    @Autowired
    private SubjectFinalService subjectFinalService;

    @Autowired
    private SubjectFinalFundService subjectFinalFundService;

    @PostMapping("/add")
    public Map<String, Object> add(@RequestParam Long subject_id, @RequestParam @DateTimeFormat(pattern="yyyy") Date year,
                                   @RequestParam BigDecimal fund, @RequestParam BigDecimal other_fund, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法添加");

        if (year == null) return onBadResp("年度不能为空");
        if (fund == null) return onBadResp("市教育局资助经费不能为空");
        if (other_fund == null) return onBadResp("其他经费不能为空");

        SubjectFinalFund finalFund = new SubjectFinalFund();
        finalFund.setSubjectId(subject_id);
        finalFund.setYear(year);
        finalFund.setFund(fund);
        finalFund.setOtherFund(other_fund);

        subjectFinalFundService.insert(finalFund);
        return onSuccessRep("添加成功");
    }

    @GetMapping("/list/{subject_id}")
    public Map<String, Object> list(@PathVariable Long subject_id) {
        return onDataResp(subjectFinalFundService.selectFunds(subject_id));
    }

    @GetMapping("/show/{id}")
    public Map<String, Object> show(@PathVariable Long id) {
        return onDataResp(subjectFinalFundService.selectById(id));
    }

    @PostMapping("/update")
    public Map<String, Object> update(@RequestParam Long subject_id, @RequestParam Long id, @DateTimeFormat(pattern="yyyy") Date year,
                                      BigDecimal fund, BigDecimal other_fund, HttpServletRequest request)
    {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectFinalFund finalFund = new SubjectFinalFund();
        finalFund.setId(id);
        finalFund.setSubjectId(subject_id);
        if (year != null) finalFund.setYear(year);
        if (fund != null) finalFund.setFund(fund);
        if (other_fund != null) finalFund.setOtherFund(other_fund);

        if (finalFund.getYear() == null && finalFund.getFund() == null && finalFund.getOtherFund() == null) {
            return onBadResp("参数不能为空");
        }

        subjectFinalFundService.updateById(finalFund);
        return onSuccessRep("修改成功");
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long subject_id, @RequestParam Long[] id, HttpServletRequest request) {
        SubjectFinalManager manager = new SubjectFinalManager(subject_id, subjectFinalService);
        if (!manager.isEditable(request)) return onBadResp("无法删除");

        subjectFinalFundService.deleteById(subject_id, id);
        return onSuccessRep("删除成功");
    }
}
