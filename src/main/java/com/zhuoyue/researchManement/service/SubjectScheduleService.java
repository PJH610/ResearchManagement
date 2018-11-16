package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectSchedule;

import java.util.List;

public interface SubjectScheduleService {

    int insert(SubjectSchedule subjectSchedule);

    List<SubjectSchedule> listBySubjectId(Long subjectId);

    SubjectSchedule selectById(Long id);

    int updateById(SubjectSchedule subjectSchedule);

    int deleteById(Long subjectId, Long... id);
}
