package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectPersonnel;
import com.zhuoyue.researchManement.dao.SubjectPersonnelDao;
import com.zhuoyue.researchManement.service.SubjectPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubjectPersonnelImpl implements SubjectPersonnelService {

    @Autowired
    private SubjectPersonnelDao subjectPersonnelDao;

    @Override
    public int insert(SubjectPersonnel personnel) {
        return subjectPersonnelDao.insert(personnel);
    }

    @Override
    public List<SubjectPersonnel> listBySubjectId(Long subejctId) {
        return subjectPersonnelDao.listBySubjectId(subejctId);
    }

    @Override
    public SubjectPersonnel selectById(Long id) {
        return subjectPersonnelDao.selectById(id);
    }

    @Override
    public int updateById(SubjectPersonnel personnel) {
        return subjectPersonnelDao.updateById(personnel);
    }

    @Override
    public int deleteById(Long subjectId, Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectPersonnelDao.deleteById(subjectId, id);
    }
}
