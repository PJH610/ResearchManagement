package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.*;
import com.zhuoyue.researchManement.enums.SubjectState;

import java.util.List;

public interface SubjectService {

    int insert(Subject subject);

    List<Subject> list(Long userId, String keyword, SubjectState[] states, Long[] unitIds);

    List<Subject> listWithSpecialists(Long userId, String keyword, SubjectState[] states, Long[] unitIds);

    List<Subject> listChange(String keyword, Long userId, Long[] unitIds);

    List<Subject> listASubjectWithName(Long userId);
    
    Subject selectById(Long id);

    int updateById(Subject subject, Long userId, SubjectState[] states, Long[] unitIds);

    int deleteById(Long... id);


}
