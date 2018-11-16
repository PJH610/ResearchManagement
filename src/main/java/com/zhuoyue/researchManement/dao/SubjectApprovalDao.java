package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectApproval;
import com.zhuoyue.researchManement.enums.SubjectState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectApprovalDao {

    int insert(SubjectApproval approval);

    List<SubjectApproval> list();

    SubjectApproval selectBySubjectId(Long subjectId);

    SubjectApproval selectBySubjectIdWithSpecialists(Long subjectId);

    int updateBySubjectId(@Param("approval") SubjectApproval approval, @Param("subject") Subject subject, @Param("states") SubjectState[] states, @Param("unitIds") Long[] unitIds);

    int deleteById(Long... id);
}
