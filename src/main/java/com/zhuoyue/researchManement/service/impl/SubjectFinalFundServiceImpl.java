package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFinalFund;
import com.zhuoyue.researchManement.bean.SubjectFinalFunds;
import com.zhuoyue.researchManement.common.SubjectFinalManager;
import com.zhuoyue.researchManement.dao.SubjectFinalFundDao;
import com.zhuoyue.researchManement.service.SubjectFinalFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectFinalFundServiceImpl implements SubjectFinalFundService {

    @Autowired
    private SubjectFinalFundDao subjectFinalFundDao;

    @Override
    public int insert(SubjectFinalFund finalFund) {
        return subjectFinalFundDao.insert(finalFund);
    }

    @Override
    public List<SubjectFinalFund> listBySubjectId(Long subjectId) {
        List<SubjectFinalFund> list = subjectFinalFundDao.listBySubjectId(subjectId);
        for (SubjectFinalFund finalFund : list) {
            SubjectFinalManager.initFundTotal(finalFund);
        }
        return list;
    }

    @Override
    public SubjectFinalFunds selectFunds(Long finalId) {
        List<SubjectFinalFund> finalFunds = listBySubjectId(finalId);
        return new SubjectFinalFunds(SubjectFinalManager.countFundsTotal(finalFunds), finalFunds);
    }

    @Override
    public SubjectFinalFund selectById(Long id) {
        return subjectFinalFundDao.selectById(id);
    }

    @Override
    public int updateById(SubjectFinalFund finalFund) {
        return subjectFinalFundDao.updateById(finalFund);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        return subjectFinalFundDao.deleteById(subjectId, id);
    }
}
