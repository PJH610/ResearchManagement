package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFeasibility;

import java.util.List;

public interface SubjectFeasibilityService {

    int insert(SubjectFeasibility subjectFeasibility);

    List<SubjectFeasibility> list();

    SubjectFeasibility selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFeasibility subjectFeasibility);

    int deleteById(Long... id);
}
