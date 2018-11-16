package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectBudget;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
@Repository
public interface SubjectBudgetDao {

    int insert(SubjectBudget subjectBudget);

    List<SubjectBudget> list();

    SubjectBudget selectById(Long id);

    int updateById(SubjectBudget subjectBudget);

    int deleteById(Long... id);
}
