package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectRecommender;

import java.util.List;

public interface SubjectRecommenderService {

    int insert(SubjectRecommender subjectRecommender);

    List<SubjectRecommender> list();

    SubjectRecommender selectById(Long id);

    SubjectRecommender selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectRecommender subjectRecommender);

    int deleteById(Long... id);
}
