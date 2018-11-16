package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.dao.TestDao;
import com.zhuoyue.researchManement.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService
{
	@Autowired
	private TestDao testDao;
	
	public Integer[] selectTest()
	{
		return testDao.selectTest();
	}
}
