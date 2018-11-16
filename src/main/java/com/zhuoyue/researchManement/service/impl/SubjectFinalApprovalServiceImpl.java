package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.bean.SubjectFinalApproval;
import com.zhuoyue.researchManement.dao.SubjectFinalApprovalDao;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.service.SubjectFinalApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectFinalApprovalServiceImpl implements SubjectFinalApprovalService {

    @Autowired
    private SubjectFinalApprovalDao subjectFinalApprovalDao;

    @Override
    public int insert(SubjectFinalApproval finalApproval) {
        return subjectFinalApprovalDao.insert(finalApproval);
    }

    @Override
    public List<SubjectFinalApproval> list() {
        return subjectFinalApprovalDao.list();
    }

    @Override
    public SubjectFinalApproval selectBySubjectId(Long subjectId) {
        return subjectFinalApprovalDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectFinalApproval finalApproval, SubjectFinal subjectFinal, SubjectFinalState[] states, Long[] unitIds) {
        return subjectFinalApprovalDao.updateBySubjectId(finalApproval, subjectFinal, states, unitIds);
    }

    @Override
    public int deleteById(Long... id) {
        return subjectFinalApprovalDao.deleteById(id);
    }
}
