package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFeasibility;
import com.zhuoyue.researchManement.dao.SubjectFeasibilityDao;
import com.zhuoyue.researchManement.service.SubjectFeasibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectFeasibilityServiceImpl implements SubjectFeasibilityService {

    @Autowired
    private SubjectFeasibilityDao subjectFeasibilityDao;

    @Override
    public int insert(SubjectFeasibility subjectFeasibility) {
        return subjectFeasibilityDao.insert(subjectFeasibility);
    }

    @Override
    public List<SubjectFeasibility> list() {
        return subjectFeasibilityDao.list();
    }

    @Override
    public SubjectFeasibility selectBySubjectId(Long subjectId) {
        return subjectFeasibilityDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectFeasibility subjectFeasibility) {
        return subjectFeasibilityDao.updateBySubjectId(subjectFeasibility);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectFeasibilityDao.deleteById(id);
    }
}
