package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectFinal;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFinalDao {

    int insert(SubjectFinal subjectFinal);

    List<SubjectFinal> list();

    SubjectFinal selectById(Long id);

    SubjectFinal selectBySubjectId(Long subjectId);

    SubjectFinalState selectState(Long subjectId);

    int updateBySubjectId(@Param("subjectFinal") SubjectFinal subjectFinal, @Param("states") SubjectFinalState[] states, @Param("userId") Long userId, @Param("unitIds") Long[] unitIds);

    int deleteById(Long... id);
}
