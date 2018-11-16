package com.zhuoyue.researchManement.task;

import com.zhuoyue.researchManement.util.ActivitCounterUtils;
import com.zhuoyue.researchManement.util.CounterUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CounterTask {

    @Scheduled(cron = "0 0/1 * * * ? ") // 间隔1分钟执行
    public void taskCycle() {
        CounterUtils.flush();
        ActivitCounterUtils.flush();
    }

}
