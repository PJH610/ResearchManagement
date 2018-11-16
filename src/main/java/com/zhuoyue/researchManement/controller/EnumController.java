package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.enums.SubjectCategory;
import com.zhuoyue.researchManement.enums.SubjectClassification;
import com.zhuoyue.researchManement.enums.SubjectFinancialcategory;
import com.zhuoyue.researchManement.enums.SubjectState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/enum")
public class EnumController extends BaseApiController {

    @GetMapping("/subject/classificaiton")
    public Map<String, Object> subjectClassficition() {
        return onDataResp(SubjectClassification.values());
    }

    @GetMapping("/subject/category")
    public Map<String, Object> subjectcategory() {
        return onDataResp(SubjectCategory.values());
    }

    @GetMapping("/subject/financialcategory")
    public Map<String, Object> financialcategory() {
        return onDataResp(SubjectFinancialcategory.values());
    }

    @GetMapping("/subject/state")
    public Map<String, Object> subjectState() {
        return onDataResp(SubjectState.values());
    }
}
