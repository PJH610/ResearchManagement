package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectResultDao {

    int insert(SubjectResult subjectResult);

    List<SubjectResult> list(Long subjectId);

    SubjectResult selectById(Long id);

    int updateById(SubjectResult subjectResult);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);
}
