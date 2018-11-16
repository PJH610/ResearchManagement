package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectSchedule;
import com.zhuoyue.researchManement.dao.SubjectScheduleDao;
import com.zhuoyue.researchManement.service.SubjectScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectScheduleServiceImpl implements SubjectScheduleService {

    @Autowired
    private SubjectScheduleDao subjectScheduleDao;

    @Override
    public int insert(SubjectSchedule subjectSchedule) {
        return subjectScheduleDao.insert(subjectSchedule);
    }

    @Override
    public List<SubjectSchedule> listBySubjectId(Long subjectId) {
        return subjectScheduleDao.listBySubjectId(subjectId);
    }

    @Override
    public SubjectSchedule selectById(Long id) {
        return subjectScheduleDao.selectById(id);
    }

    @Override
    public int updateById(SubjectSchedule subjectSchedule) {
        return subjectScheduleDao.updateById(subjectSchedule);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectScheduleDao.deleteById(subjectId, id);
    }

}
