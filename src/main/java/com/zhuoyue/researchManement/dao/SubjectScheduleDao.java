package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectSchedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectScheduleDao {

    int insert(SubjectSchedule subjectSchedule);

    List<SubjectSchedule> listBySubjectId(Long subjectId);

    SubjectSchedule selectById(Long id);

    int updateById(SubjectSchedule subjectSchedule);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);
}
