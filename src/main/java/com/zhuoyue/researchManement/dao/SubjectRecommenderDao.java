package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectRecommender;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectRecommenderDao {

    int insert(SubjectRecommender subjectRecommender);

    List<SubjectRecommender> list();

    SubjectRecommender selectById(Long id);

    SubjectRecommender selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectRecommender subjectRecommender);

    int deleteById(Long... id);
}
