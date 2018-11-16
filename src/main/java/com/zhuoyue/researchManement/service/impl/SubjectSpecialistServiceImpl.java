package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectSpecialist;
import com.zhuoyue.researchManement.common.SubjectSpecialistManager;
import com.zhuoyue.researchManement.dao.SubjectSpecialistDao;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import com.zhuoyue.researchManement.enums.SubjectState;
import com.zhuoyue.researchManement.service.SubjectSpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectSpecialistServiceImpl implements SubjectSpecialistService {

    @Autowired
    private SubjectSpecialistDao subjectSpecialistDao;

    @Override
    public int insert(List<SubjectSpecialist> specialist) {
        return subjectSpecialistDao.insert(specialist);
    }

    @Override
    public List<SubjectSpecialist> list(Long userId, SubjectSpecialistType type, boolean checked, SubjectState[] states) {
        List<SubjectSpecialist> list = subjectSpecialistDao.list(userId, type, checked, states);
        for (SubjectSpecialist specialist : list) {
            specialist.setTotalScore(SubjectSpecialistManager.countScore(specialist));
        }
        return list;
    }

    @Override
    public List<SubjectSpecialist> listApproval(Long subjectId) {
        return subjectSpecialistDao.listApproval(subjectId);
    }

    @Override
    public List<SubjectSpecialist> listMedium(Long subjectId) {
        return subjectSpecialistDao.listMedium(subjectId);
    }

    @Override
    public SubjectSpecialist selectById(Long id) {
        return subjectSpecialistDao.selectById(id);
    }

    @Override
    public int updateById(SubjectSpecialist specialist, Long userId, SubjectSpecialistType type, SubjectState[] states) {
        if (specialist == null
                || (specialist.getNote() == null && specialist.getCheckTime() == null && specialist.getValueScore() == null
                && specialist.getAchievementScore() == null && specialist.getValidityScore() == null && specialist.getScientificScore() == null
                && specialist.getConditionScore() == null && specialist.getCheckState() == null))
        {
            return 0;
        }
        return subjectSpecialistDao.updateById(specialist, userId, type, states);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectSpecialistDao.deleteById(id);
    }

    @Override
    public int deleteBySubjectId(Long subjectId, SubjectSpecialistType type) {
        return subjectSpecialistDao.deleteBySubjectId(subjectId, type);
    }
}
