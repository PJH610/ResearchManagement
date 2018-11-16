package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.bean.SubjectFinalApproval;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFinalApprovalDao {

    int insert(SubjectFinalApproval finalApproval);

    List<SubjectFinalApproval> list();

    SubjectFinalApproval selectBySubjectId(Long subjectId);

    int updateBySubjectId(@Param("finalApproval") SubjectFinalApproval finalApproval, @Param("subjectFinal") SubjectFinal subjectFinal, @Param("states") SubjectFinalState[] states, @Param("unitIds") Long[] unitIds);

    int deleteById(Long... id);
}
