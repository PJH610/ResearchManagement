package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.enums.SubjectFinalState;

import java.util.List;

public interface SubjectFinalService {

    int insert(SubjectFinal subjectFinal);

    List<SubjectFinal> list();

    SubjectFinal selectById(Long id);

    SubjectFinal selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFinal subjectFinal, SubjectFinalState[] states, Long userId, Long[] unitIds);

    int deleteById(Long... id);
}
