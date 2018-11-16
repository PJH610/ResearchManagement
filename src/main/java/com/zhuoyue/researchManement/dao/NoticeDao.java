package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 12413 on 2018/5/8.
 */
@Repository
public interface NoticeDao
{
	int insert(Notice notice);

	List<Notice> list();

	Notice selectById(Long id);

	int updateById(@Param("notice") Notice notice, @Param("id") Long... id);

	int deleteById(Long...id);

	int setNoticCourread(@Param("map")Map<Long, Integer> map);
}
