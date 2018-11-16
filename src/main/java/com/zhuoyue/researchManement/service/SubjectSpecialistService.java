package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectSpecialist;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import com.zhuoyue.researchManement.enums.SubjectState;

import java.util.List;

public interface SubjectSpecialistService {

    int insert(List<SubjectSpecialist> specialists);

    List<SubjectSpecialist> list(Long userId, SubjectSpecialistType type, boolean checked, SubjectState[] states);

    List<SubjectSpecialist> listApproval(Long subjectId);

    List<SubjectSpecialist> listMedium(Long subjectId);

    SubjectSpecialist selectById(Long id);

    int updateById(SubjectSpecialist specialist, Long userId, SubjectSpecialistType type, SubjectState[] states);

    int deleteById(Long... id);

    int deleteBySubjectId(Long subjectId, SubjectSpecialistType type);
}
