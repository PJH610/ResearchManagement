package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectMedium;
import com.zhuoyue.researchManement.enums.SubjectMediumState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMediumDao {

    int insert(SubjectMedium medium);

    List<SubjectMedium> list(@Param("userId") Long userId, @Param("keyword") String keyword, @Param("states") SubjectMediumState[] states, @Param("unitIds") Long[] unitIds);

    SubjectMedium selectById(Long id);

    SubjectMedium selectBySubjectId(Long subjectId);

    SubjectMediumState selectState(Long subjectId);

    int updateBySubjectId(@Param("medium") SubjectMedium medium, @Param("userId") Long userId, @Param("states") SubjectMediumState[] states, @Param("unitIds") Long[] unitIds);

    int deleteById(Long... id);
}
