package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectChange;
import com.zhuoyue.researchManement.enums.SubjectChangeState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectChangeDao {

    int insert(SubjectChange change);

    List<SubjectChange> list(@Param("subjectId") Long subjectId, @Param("states") SubjectChangeState[] states, @Param("unitIds") Long[] unitIds);

    SubjectChange selectById(Long id);

    int updateById(@Param("change") SubjectChange change, @Param("states") SubjectChangeState[] states);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);
}
