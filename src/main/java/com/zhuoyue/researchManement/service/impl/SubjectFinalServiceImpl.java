package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.dao.SubjectFinalDao;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.service.SubjectFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectFinalServiceImpl implements SubjectFinalService {

    @Autowired
    private SubjectFinalDao subjectFinalDao;

    @Override
    public int insert(SubjectFinal subjectFinal) {
        return subjectFinalDao.insert(subjectFinal);
    }

    @Override
    public List<SubjectFinal> list() {
        return subjectFinalDao.list();
    }

    @Override
    public SubjectFinal selectById(Long id) {
        return subjectFinalDao.selectById(id);
    }

    @Override
    public SubjectFinal selectBySubjectId(Long subjectId) {
        return subjectFinalDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectFinal subjectFinal, SubjectFinalState[] states, Long userId, Long[] unitIds) {
        return subjectFinalDao.updateBySubjectId(subjectFinal, states, userId, unitIds);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectFinalDao.deleteById(id);
    }
}
