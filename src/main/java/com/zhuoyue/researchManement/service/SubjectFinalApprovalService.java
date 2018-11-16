package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.bean.SubjectFinalApproval;
import com.zhuoyue.researchManement.enums.SubjectFinalState;

import java.util.List;

public interface SubjectFinalApprovalService {

    int insert(SubjectFinalApproval finalApproval);

    List<SubjectFinalApproval> list();

    SubjectFinalApproval selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFinalApproval finalApproval, SubjectFinal subjectFinal, SubjectFinalState[] states, Long[] unitIds);

    int deleteById(Long... id);
}
