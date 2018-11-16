package com.zhuoyue.researchManement.util;

import com.zhuoyue.researchManement.service.ActivityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ActivitCounterUtils {

    @Autowired
    private ActivityService activityService;

    public static ActivitCounterUtils utils;

    private static volatile Boolean inFlush = false;
    private static Object waitObject = new Object();

    private static ConcurrentHashMap<Long, AtomicInteger> increaseCounteMap = new ConcurrentHashMap<Long, AtomicInteger>();

    @PostConstruct
    public void init() {
        utils = this;
    }

    public static void addCounter(Long id) {
        if (!inFlush) {
            increaseMap(id);
        } else {
            synchronized (inFlush) {
                increaseMap(id);
            }
        }

    }

    private static void increaseMap(Long id) {
        AtomicInteger counter = increaseCounteMap.get(id);
        if (counter != null) {
            counter.incrementAndGet();
        } else {
            synchronized (waitObject) {
                counter = increaseCounteMap.get(id);
                if (counter != null) {
                    counter.incrementAndGet();
                } else {
                    increaseCounteMap.put(id, new AtomicInteger(1));
                }
            }
        }
    }

    public static void flush() {
        if (inFlush) return;
        synchronized (inFlush) {
            if (inFlush)return;
            inFlush = true;
        }
        ConcurrentHashMap<Long, AtomicInteger> saveMap = increaseCounteMap;
        increaseCounteMap = new ConcurrentHashMap<Long, AtomicInteger>();
        try {
            Thread.sleep(5000);

            // 将点击量统计写入数据库

            utils.activityService.setActivityCourread(saveMap);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            saveMap.clear();
            inFlush = false;
        }
    }

}
