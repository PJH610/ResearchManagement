package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Activity;
import com.zhuoyue.researchManement.enums.ActivityState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by 12413 on 2018/5/19.
 */
@Repository
public interface ActivityDao {

    int insert(Activity activit);

    List<Activity> list(@Param("keyword") String keyword, @Param("subjectId") Long subjectId, @Param("userId") Long userId, @Param("unitIds") Long[] unitIds, @Param("states") ActivityState[] states);

    List<Activity> mylist();

    Activity selectById(Long id);

    int updateById(@Param("activity") Activity activity, @Param("states") ActivityState[] states);

    int setActivityCourread(@Param("map") Map<Long, Integer> map);

    int deleteById(Long... id);

}
