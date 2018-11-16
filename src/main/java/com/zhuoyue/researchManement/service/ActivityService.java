package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.Activity;
import com.zhuoyue.researchManement.enums.ActivityState;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 12413 on 2018/5/19.
 */
public interface ActivityService {
    int insert(Activity activity);

    List<Activity> list(String keyword, Long subjectId, Long userId,Long[] unitIds, ActivityState[] states);

    List<Activity> mylist();

    Activity selectById(Long id);

    int updateById(Activity activity, ActivityState[] states);

    int setActivityCourread(Map<Long, AtomicInteger> map);

    int deleteById(Long...id);
}
