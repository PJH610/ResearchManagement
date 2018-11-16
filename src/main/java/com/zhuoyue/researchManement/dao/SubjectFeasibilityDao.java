package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectFeasibility;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFeasibilityDao {

    int insert(SubjectFeasibility subjectFeasibility);

    List<SubjectFeasibility> list();

    SubjectFeasibility selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFeasibility subjectFeasibility);

    int deleteById(Long... id);
}
