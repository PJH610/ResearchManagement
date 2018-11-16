package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.Notice;
import com.zhuoyue.researchManement.dao.NoticeDao;
import com.zhuoyue.researchManement.service.NoticeService;
import com.zhuoyue.researchManement.util.CounterUtils;
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
public class NoticeServiceImpl implements NoticeService
{
	@Autowired
	NoticeDao noticeDao;
	
	public int insert(Notice notice)
	{
		return noticeDao.insert(notice);
	}
	
	public List<Notice> list()
	{
		return noticeDao.list();
	}

	public Notice selectById(Long id) {
		CounterUtils.addCounter(id);
		return noticeDao.selectById(id);
	}

	@Override
	public int updatebyId(Notice notice, Long... id) {
		return noticeDao.updateById(notice,id);
	}

	@Override
	public int deleteById(Long... id) {
		if (id == null || id.length == 0) return 0;
		return noticeDao.deleteById(id);
	}

	@Override
	public int setNoticCourread(Map<Long, AtomicInteger> map) {
		if (map.size() == 0) return 0;
		Map<Long, Integer> param = new HashMap<>();
		for (Map.Entry<Long, AtomicInteger> entry : map.entrySet()) {
			param.put(entry.getKey(), entry.getValue().intValue());
		}
		return noticeDao.setNoticCourread(param);
	}
}
