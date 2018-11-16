package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.SubjectFund;
import com.zhuoyue.researchManement.common.SubjectManager;
import com.zhuoyue.researchManement.dao.SubjectFundDao;
import com.zhuoyue.researchManement.service.SubjectFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
@Service
public class SubjectFundServiceImpl implements SubjectFundService {

    @Autowired
    SubjectFundDao subjectFundDao;

    @Override
    public int insert(SubjectFund subjectFund) {
        return subjectFundDao.insert(subjectFund);
    }

    @Override
    public List<SubjectFund> list() {
        return subjectFundDao.list();
    }

    @Override
    public SubjectFund selectBySubjectId(Long id) {
        SubjectFund fund = subjectFundDao.selectBySubjectId(id);
        SubjectManager.initFundsTotal(fund);
        return fund;
    }

    @Override
    public int updateBySubjectId(SubjectFund subjectFund) {
        if (subjectFund == null || (subjectFund.getData() == null && subjectFund.getTravel() == null
                && subjectFund.getMeeting() == null && subjectFund.getEquipment() == null
                && subjectFund.getService() == null && subjectFund.getPrint() == null && subjectFund.getIdentification() == null
                && subjectFund.getOther() == null && subjectFund.getFunding() == null && subjectFund.getSelfraised() == null))
        {
            return 0;
        }
        return subjectFundDao.updateBySubjectId(subjectFund);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return subjectFundDao.deleteById(id);
    }
}
