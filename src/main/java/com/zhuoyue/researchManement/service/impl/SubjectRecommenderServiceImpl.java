package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectRecommender;
import com.zhuoyue.researchManement.dao.SubjectRecommenderDao;
import com.zhuoyue.researchManement.service.SubjectRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectRecommenderServiceImpl implements SubjectRecommenderService {

    @Autowired
    private SubjectRecommenderDao subjectRecommenderDao;

    @Override
    public int insert(SubjectRecommender subjectRecommender) {
        return subjectRecommenderDao.insert(subjectRecommender);
    }

    @Override
    public List<SubjectRecommender> list() {
        return subjectRecommenderDao.list();
    }

    @Override
    public SubjectRecommender selectById(Long id) {
        return subjectRecommenderDao.selectById(id);
    }

    @Override
    public SubjectRecommender selectBySubjectId(Long subjectId) {
        return subjectRecommenderDao.selectBySubjectId(subjectId);
    }

    @Override
    public int updateBySubjectId(SubjectRecommender subjectRecommender) {
        return subjectRecommenderDao.updateBySubjectId(subjectRecommender);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectRecommenderDao.deleteById(id);
    }
}
