package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFund;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
public interface SubjectFundService {

    int insert(SubjectFund subjectFund);

    List<SubjectFund> list();

    SubjectFund selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFund subjectFund);

    int deleteById(Long... id);
}
