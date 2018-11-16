package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectFinalAchievement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectFinalAchievementDao {

    int insert(SubjectFinalAchievement finalAchievement);

    List<SubjectFinalAchievement> listBySubjectId(Long subjectId);

    SubjectFinalAchievement selectById(Long id);

    int updateById(SubjectFinalAchievement finalAchievement);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);
}
