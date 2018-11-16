package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.SubjectBudget;
import com.zhuoyue.researchManement.bean.SubjectFund;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.service.SubjectFundService;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SX-503 on 2018/5/23.
 */
@RestController
@RequestMapping("/api/subject/fund")
public class SubjectFundController extends BaseApiController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    SubjectFundService subjectFundService;

    @GetMapping("/show/{subject_id}")
    public Map<String, Object> show(@PathVariable Long subject_id) {
        return onDataResp(subjectFundService.selectBySubjectId(subject_id));
    }

    @PostMapping("/update")
    public Map<String, Object> update (@RequestParam Long subject_id, BigDecimal data, BigDecimal travel, BigDecimal meeting,
                                       BigDecimal equipment, BigDecimal service, BigDecimal print, BigDecimal identification,
                                       BigDecimal other, BigDecimal funding, BigDecimal selfraised,
                                       @DateTimeFormat(pattern="yyyy") Date[] budgets_year, BigDecimal[] budgets_money,
                                       HttpServletRequest request)
    {
        List<SubjectBudget> budgets = null;
        if (budgets_year!= null && budgets_money != null && budgets_year.length == budgets_money.length) {
            budgets = new ArrayList<>();
            for (int i = 0; i < budgets_year.length; i++) {
                if (budgets_year[i] != null && budgets_money[i] != null) budgets.add(new SubjectBudget(budgets_year[i], budgets_money[i]));
            }
        }

        SubjectManager manager = new SubjectManager(subject_id, subjectService);
        if (!manager.isEditable(request)) return onBadResp("无法修改");

        SubjectFund fund = new SubjectFund();
        fund.setSubjectId(subject_id);
        if (data != null) fund.setData(data);
        if (travel != null) fund.setTravel(travel);
        if (meeting != null) fund.setMeeting(meeting);
        if (equipment != null) fund.setEquipment(equipment);
        if (service != null) fund.setService(service);
        if (print != null) fund.setPrint(print);
        if (identification != null) fund.setIdentification(identification);
        if (other != null) fund.setOther(other);
        if (funding != null) fund.setFunding(funding);
        if (selfraised != null) fund.setSelfraised(selfraised);
        if (budgets != null) fund.setBudgets(budgets);

        if (fund.getData() == null && fund.getTravel() == null && fund.getMeeting() == null && fund.getEquipment() == null
                && fund.getService() == null && fund.getPrint() == null && fund.getIdentification() == null && fund.getOther() == null
                && fund.getSelfraised() == null && fund.getBudgets() == null)
        {
            return onBadResp("参数不能为空");
        }

        if (subjectService.selectById(subject_id) != null && subjectFundService.updateBySubjectId(fund) == 0) {
            subjectFundService.insert(SubjectManager.initFund(fund));
        }
        return onSuccessRep("修改成功");
    }

}
