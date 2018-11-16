package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectResult;

import java.util.List;

public interface SubjectResultService {

    int insert(SubjectResult subjectResult);

    List<SubjectResult> list(Long subjectId);

    SubjectResult selectById(Long id);

    int updateById(SubjectResult subjectResult);

    int deleteById(Long subjectId, Long... id);
}
