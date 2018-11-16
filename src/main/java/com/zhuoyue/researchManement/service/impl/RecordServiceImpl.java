package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.Record;
import com.zhuoyue.researchManement.dao.RecordDao;
import com.zhuoyue.researchManement.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 杨 on 2018/5/23.
 */
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordDao recordDao;

    public int insert(Record record) {
        return recordDao.insert(record);
    }

    public List<Record> listByName(String name) {
        return recordDao.listByName(name);
    }

    public Record selectById(Long id) {
        return recordDao.selectById(id);
    }

    public int updateById (Record record) {
        return recordDao.updateById(record);
    }

    @Override
    public int deleteById(Long... id) {
        if (id == null || id.length == 0) return 0;
        return recordDao.deleteById(id);
    }
}
