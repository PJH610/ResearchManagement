package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectBudget;
import com.zhuoyue.researchManement.bean.SubjectProof;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectProofDao {

    int insert(SubjectProof subjectProof);

    List<SubjectProof> list();

    SubjectProof selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectProof subjectProof);

    int deleteById(Long... id);
}
