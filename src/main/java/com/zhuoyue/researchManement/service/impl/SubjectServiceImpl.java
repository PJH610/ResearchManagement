package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.dao.SubjectDao;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public int insert(Subject subject) {
        return subjectDao.insert(subject);
    }

    @Override
    public List<Subject> list(Long userId, String keyword, SubjectState[] states, Long[] unitIds) {
        return subjectDao.list(userId, keyword, states, unitIds);
    }

    @Override
    public List<Subject> listWithSpecialists(Long userId, String keyword, SubjectState[] states, Long[] unitIds) {
        List<Subject> list = subjectDao.listWithSpecialists(userId, keyword, states, unitIds);
        for (Subject subject : list) {
            subject.setCanApproval(SubjectManager.canApproval(subject.getSpecialists()));
        }
        return list;
    }

    @Override
    public List<Subject> listChange(String keyword, Long userId, Long[] unitIds) {
        return subjectDao.listChange(keyword, userId, unitIds);
    }

    @Override
    public List<Subject> listASubjectWithName(Long userId) {
        return subjectDao.listASubjectWithName(userId);
    }

    @Override
    public Subject selectById(Long id) {
        return subjectDao.selectById(id);
    }

    @Override
    public int updateById(Subject subject, Long userId, SubjectState[] states, Long[] unitIds) {
        return subjectDao.updateById(subject, userId, states, unitIds);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectDao.deleteById(id);
    }
}
