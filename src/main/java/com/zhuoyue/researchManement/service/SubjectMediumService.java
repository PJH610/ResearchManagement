package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectMedium;
import com.zhuoyue.researchManement.enums.SubjectMediumState;

import java.util.List;

public interface SubjectMediumService {

    int insert(SubjectMedium medium);

    List<SubjectMedium> list(Long userId, String keyword, SubjectMediumState[] states, Long[] unitIds);

    SubjectMedium selectById(Long id);

    SubjectMedium selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectMedium medium, Long userId, SubjectMediumState[] states, Long[] unitIds);

    int deleteById(Long... id);
}
