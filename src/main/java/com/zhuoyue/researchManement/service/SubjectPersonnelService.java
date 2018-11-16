package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectPersonnel;
import java.util.List;

public interface SubjectPersonnelService {

    int insert(SubjectPersonnel personnel);

    List<SubjectPersonnel> listBySubjectId(Long subejctId);

    SubjectPersonnel selectById(Long id);

    int updateById(SubjectPersonnel personnel);

    int deleteById(Long subjectId, Long... id);
}
