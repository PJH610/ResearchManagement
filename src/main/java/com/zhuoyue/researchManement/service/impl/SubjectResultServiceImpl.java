package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectResult;
import com.zhuoyue.researchManement.dao.SubjectResultDao;
import com.zhuoyue.researchManement.service.SubjectResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectResultServiceImpl implements SubjectResultService {

    @Autowired
    private SubjectResultDao subjectResultDao;

    @Override
    public int insert(SubjectResult subjectResult) {
        return subjectResultDao.insert(subjectResult);
    }

    @Override
    public List<SubjectResult> list(Long subjectId) {
        return subjectResultDao.list(subjectId);
    }

    @Override
    public SubjectResult selectById(Long id) {
        return subjectResultDao.selectById(id);
    }

    @Override
    public int updateById(SubjectResult subjectResult) {
        if (subjectResult.getCompletetime() == null || subjectResult.getFinalresultName() == null ||
            subjectResult.getFinalresultShape() == null || subjectResult.getPrincipal() == null) return 0;
        return subjectResultDao.updateById(subjectResult);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectResultDao.deleteById(subjectId, id);
    }
}
