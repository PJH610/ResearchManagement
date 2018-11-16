package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectFinalFund;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFinalFundDao {

    int insert(SubjectFinalFund finalFund);

    List<SubjectFinalFund> listBySubjectId(Long subjectId);

    SubjectFinalFund selectById(Long id);

    int updateById(SubjectFinalFund finalFund);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);
}
