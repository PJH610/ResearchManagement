package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectMedium;
import com.zhuoyue.researchManement.dao.SubjectMediumDao;
import com.zhuoyue.researchManement.enums.SubjectMediumState;
import com.zhuoyue.researchManement.service.SubjectMediumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectMediumServiceImpl implements SubjectMediumService {

    @Autowired
    private SubjectMediumDao subjectMediumDao;

    @Override
    public int insert(SubjectMedium medium) {
        return subjectMediumDao.insert(medium);
    }

    @Override
    public List<SubjectMedium> list(Long userId, String keyword, SubjectMediumState[] states, Long[] unitIds) {
        return subjectMediumDao.list(userId, keyword, states, unitIds);
    }

    @Override
    public SubjectMedium selectById(Long id) {
        return subjectMediumDao.selectById(id);
    }

    @Override
    public SubjectMedium selectBySubjectId(Long subjectId) {
        return subjectMediumDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectMedium medium, Long userId, SubjectMediumState[] states, Long[] unitIds) {
        return subjectMediumDao.updateBySubjectId(medium, userId, states, unitIds);
    }

    @Override
    public int deleteById(Long... id) {
        return subjectMediumDao.deleteById(id);
    }
}

