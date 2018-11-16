package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectProof;
import com.zhuoyue.researchManement.dao.SubjectProofDao;
import com.zhuoyue.researchManement.service.SubjectProofService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectProofServiceImpl implements SubjectProofService {

    @Autowired
    private SubjectProofDao subjectProofDao;

    @Override
    public int insert(SubjectProof subjectProof) {
        return subjectProofDao.insert(subjectProof);
    }

    @Override
    public List<SubjectProof> list() {
        return subjectProofDao.list();
    }

    @Override
    public SubjectProof selectBySubjectId(Long subjectId) {
        return subjectProofDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectProof subjectProof) {
        return subjectProofDao.updateBySubjectId(subjectProof);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectProofDao.deleteById(id);
    }
}
