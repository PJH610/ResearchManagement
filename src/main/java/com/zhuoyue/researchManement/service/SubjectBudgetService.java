package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectBudget;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
public interface SubjectBudgetService {

    int insert(SubjectBudget subjectBudget);

    List<SubjectBudget> list();

    SubjectBudget selectById(Long id);

    int updateById(SubjectBudget subjectBudget);

    int deleteById(Long... id);
}
