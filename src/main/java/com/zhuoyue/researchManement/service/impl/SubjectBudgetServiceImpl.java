package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectBudget;
import com.zhuoyue.researchManement.dao.SubjectBudgetDao;
import com.zhuoyue.researchManement.service.SubjectBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
@Service
public class SubjectBudgetServiceImpl implements SubjectBudgetService {

    @Autowired
    SubjectBudgetDao subjectBudgetDao;

    @Override
    public int insert(SubjectBudget subjectBudget) {
        return subjectBudgetDao.insert(subjectBudget);
    }

    @Override
    public List<SubjectBudget> list() {
        return subjectBudgetDao.list();
    }

    @Override
    public SubjectBudget selectById(Long id) {
        return subjectBudgetDao.selectById(id);
    }

    @Override
    public int updateById(SubjectBudget subjectBudget) {
        return subjectBudgetDao.updateById(subjectBudget);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectBudgetDao.deleteById(id);
    }
}
