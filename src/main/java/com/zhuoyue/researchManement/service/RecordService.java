package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.Record;

import java.util.List;

/**
 * Created by 杨 on 2018/5/23.
 */
public interface RecordService {

    int insert(Record record);

    List<Record> listByName(String name);

    Record selectById(Long id);

    int updateById(Record record);

    int deleteById(Long... id);
}
