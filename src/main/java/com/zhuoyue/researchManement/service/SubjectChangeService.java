package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectChange;
import com.zhuoyue.researchManement.enums.SubjectChangeState;

import java.util.List;

public interface SubjectChangeService {

    int insert(SubjectChange change);

    List<SubjectChange> list(Long subjectId, SubjectChangeState[] states, Long[] unitIds);

    SubjectChange selectById(Long id);

    int updateById(SubjectChange change, SubjectChangeState[] states);

    int deleteById(Long subjectId, Long... id);
}
