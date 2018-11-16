package service;

import com.zhuoyue.researchManement.service.NoticeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ming on 2018/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring-mvc.xml"})
public class NoticeDaoTest {

    @Autowired
    private NoticeService noticeService;

    @Test
    public void setNoticCourread() {
        ConcurrentHashMap<Long, AtomicInteger> map = new ConcurrentHashMap<>();
        map.put(1L,new AtomicInteger());

        noticeService.setNoticCourread(map);
    }

}
