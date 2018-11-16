package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectChange;
import com.zhuoyue.researchManement.dao.SubjectChangeDao;
import com.zhuoyue.researchManement.enums.SubjectChangeState;
import com.zhuoyue.researchManement.service.SubjectChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectChangeServiceImpl implements SubjectChangeService {

    @Autowired
    private SubjectChangeDao subjectChangeDao;

    @Override
    public int insert(SubjectChange change) {
        return subjectChangeDao.insert(change);
    }

    @Override
    public List<SubjectChange> list(Long subjectId, SubjectChangeState[] states, Long[] unitIds) {
        return subjectChangeDao.list(subjectId, states, unitIds);
    }

    @Override
    public SubjectChange selectById(Long id) {
        return subjectChangeDao.selectById(id);
    }

    @Override
    public int updateById(SubjectChange change, SubjectChangeState[] states) {
        return subjectChangeDao.updateById(change, states);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectChangeDao.deleteById(subjectId, id);
    }
}
