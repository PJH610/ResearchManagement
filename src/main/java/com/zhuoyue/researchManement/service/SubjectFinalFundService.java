package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFinalFund;
import com.zhuoyue.researchManement.bean.SubjectFinalFunds;

import java.util.List;

public interface SubjectFinalFundService {

    int insert(SubjectFinalFund finalFund);

    List<SubjectFinalFund> listBySubjectId(Long subjectId);

    SubjectFinalFunds selectFunds(Long subjectId);

    SubjectFinalFund selectById(Long id);

    int updateById(SubjectFinalFund finalFund);

    int deleteById(Long subjectId, Long... id);
}
