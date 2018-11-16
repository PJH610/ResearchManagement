package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.Notice;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 12413 on 2018/5/8.
 */
public interface NoticeService
{
	int insert(Notice notice);

	List<Notice> list();
	
	Notice selectById(Long id);

	int updatebyId(Notice notice, Long... id);

	int deleteById(Long... id);

	int setNoticCourread(Map<Long, AtomicInteger> map);
}
