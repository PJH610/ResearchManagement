package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectProof;

import java.util.List;

public interface SubjectProofService {

    int insert(SubjectProof subjectProof);

    List<SubjectProof> list();

    SubjectProof selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectProof subjectProof);

    int deleteById(Long... id);
}
