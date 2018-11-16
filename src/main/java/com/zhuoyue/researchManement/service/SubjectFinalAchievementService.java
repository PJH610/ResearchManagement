package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.SubjectFinalAchievement;

import java.util.List;

public interface SubjectFinalAchievementService {

    int insert(SubjectFinalAchievement finalAchievement);

    List<SubjectFinalAchievement> listBySubjectId(Long subjectId);

    SubjectFinalAchievement selectById(Long id);

    int updateById(SubjectFinalAchievement finalAchievement);

    int deleteById(Long subjectId, Long... id);
}
