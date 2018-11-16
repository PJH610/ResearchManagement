package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.SubjectSpecialist;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import com.zhuoyue.researchManement.enums.SubjectState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubjectSpecialistDao {

    int insert(List<SubjectSpecialist> specialist);

    List<SubjectSpecialist> list(@Param("userId") Long userId, @Param("type") SubjectSpecialistType type, @Param("checked") boolean checked, @Param("states")SubjectState[] states);

    List<SubjectSpecialist> listApproval(Long subjectId);

    List<SubjectSpecialist> listMedium(Long subjectId);

    SubjectSpecialist selectById(Long id);

    int updateById(@Param("specialist") SubjectSpecialist specialist, @Param("userId") Long userId, @Param("type") SubjectSpecialistType type, @Param("states") SubjectState[] states);

    int deleteById(Long... id);

    int deleteBySubjectId(@Param("subjectId") Long subjectId, @Param("type") SubjectSpecialistType type);
}
