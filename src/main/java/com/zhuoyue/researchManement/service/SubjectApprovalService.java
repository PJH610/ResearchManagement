package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.Subject;
import com.zhuoyue.researchManement.bean.SubjectApproval;
import com.zhuoyue.researchManement.enums.SubjectState;

import java.util.List;

public interface SubjectApprovalService {

    int insert(SubjectApproval approval);

    List<SubjectApproval> list();

    SubjectApproval selectBySubjectId(Long subjectId);

    SubjectApproval selectBySubjectIdWithSpecialists(Long subjectId);

    int updateBySubjectId(SubjectApproval approval, Subject subject, SubjectState[] states, Long[] unitIds);

    int deleteById(Long... id);
}
