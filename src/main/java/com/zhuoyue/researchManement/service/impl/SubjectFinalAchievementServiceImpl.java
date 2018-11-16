package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFinalAchievement;
import com.zhuoyue.researchManement.dao.SubjectFinalAchievementDao;
import com.zhuoyue.researchManement.service.SubjectFinalAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectFinalAchievementServiceImpl implements SubjectFinalAchievementService {

    @Autowired
    private SubjectFinalAchievementDao subjectFinalAchievementDao;

    @Override
    public int insert(SubjectFinalAchievement finalAchievement) {
        return subjectFinalAchievementDao.insert(finalAchievement);
    }

    @Override
    public List<SubjectFinalAchievement> listBySubjectId(Long subjectId) {
        return subjectFinalAchievementDao.listBySubjectId(subjectId);
    }

    @Override
    public SubjectFinalAchievement selectById(Long id) {
        return subjectFinalAchievementDao.selectById(id);
    }

    @Override
    public int updateById(SubjectFinalAchievement finalAchievement) {
        return subjectFinalAchievementDao.updateById(finalAchievement);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        return subjectFinalAchievementDao.deleteById(subjectId, id);
    }
}
