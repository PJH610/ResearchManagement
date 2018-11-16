package com.zhuoyue.researchManement.dao;


import com.zhuoyue.researchManement.bean.SubjectFund;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
@Repository
public interface SubjectFundDao {

    int insert(SubjectFund subjectFund);

    List<SubjectFund> list();

    SubjectFund selectBySubjectId(Long subjectId);

    int updateBySubjectId(SubjectFund subjectFund);

    int deleteById(Long... id);
}
