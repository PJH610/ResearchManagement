package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.Activity;
import com.zhuoyue.researchManement.dao.ActivityDao;
import com.zhuoyue.researchManement.enums.ActivityState;
import com.zhuoyue.researchManement.service.ActivityService;
import com.zhuoyue.researchManement.util.ActivitCounterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 12413 on 2018/5/8.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;

    public int insert(Activity activity)
    {
        return activityDao.insert(activity);
    }

    public List<Activity> list(String keyword, Long subjectId, Long userId, Long[] unitIds, ActivityState[] states)
    {
        return activityDao.list(keyword, subjectId, userId, unitIds, states);
    }

    @Override
    public List<Activity> mylist() {
        return activityDao.mylist();
    }

    public Activity selectById(Long id)
    {
        ActivitCounterUtils.addCounter(id);
        return activityDao.selectById(id);
    }

    @Override
    public int updateById(Activity activity, ActivityState... states) {
        return activityDao.updateById(activity, states);
    }

    @Override
    public int setActivityCourread(Map<Long, AtomicInteger> map) {
        if (map.size() == 0) return 0;
        Map<Long, Integer> param = new HashMap<>();
        for (Map.Entry<Long, AtomicInteger> entry : map.entrySet()) {
            param.put(entry.getKey(), entry.getValue().intValue());
        }
        return activityDao.setActivityCourread(param);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return activityDao.deleteById(id);
    }
}
