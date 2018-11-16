package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectPersonnel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectPersonnelDao {

    int insert(SubjectPersonnel personnel);

    List<SubjectPersonnel> listBySubjectId(Long subejctId);

    SubjectPersonnel selectById(Long id);

    int updateById(SubjectPersonnel personnel);

    int deleteById(@Param("subjectId") Long subjectId, @Param("id") Long... id);

}
