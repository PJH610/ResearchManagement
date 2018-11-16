package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectApproval;
import com.zhuoyue.researchManement.dao.SubjectApprovalDao;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectApprovalServiceImpl implements SubjectApprovalService {

    @Autowired
    private SubjectApprovalDao subjectApprovalDao;

    @Override
    public int insert(SubjectApproval approval) {
        return subjectApprovalDao.insert(approval);
    }

    @Override
    public List<SubjectApproval> list() {
        return subjectApprovalDao.list();
    }

    @Override
    public SubjectApproval selectBySubjectId(Long subjectId) {
        return subjectApprovalDao.selectBySubjectId(subjectId);
    }

    @Override
    public SubjectApproval selectBySubjectIdWithSpecialists(Long subjectId) {
        return subjectApprovalDao.selectBySubjectIdWithSpecialists(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectApproval approval, Subject subject, SubjectState[] states, Long[] unitIds) {
        return subjectApprovalDao.updateBySubjectId(approval, subject, states, unitIds);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectApprovalDao.deleteById(id);
    }
}
