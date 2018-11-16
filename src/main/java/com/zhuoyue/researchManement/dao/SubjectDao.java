package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.enums.SubjectState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao {

    int insert(Subject subject);

    List<Subject> list(@Param("userId") Long userId, @Param("keyword") String keyword, @Param("states") SubjectState[] states, @Param("unitIds") Long[] unitIds);

    List<Subject> listWithSpecialists(@Param("userId") Long userId, @Param("keyword") String keyword, @Param("states") SubjectState[] states, @Param("unitIds") Long[] unitIds);

    List<Subject> listChange(@Param("keyword") String keyword, @Param("userId") Long userId, @Param("unitIds") Long[] unitIds);

    List<Subject> listASubjectWithName(Long userId);

    Subject selectById(Long id);

    int updateById(@Param("subject") Subject subject, @Param("userId") Long userId, @Param("states") SubjectState[] states, @Param("unitIds") Long[] unitIds);

    int deleteById(Long... id);
}
