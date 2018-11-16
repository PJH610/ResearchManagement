package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Record;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 杨 on 2018/5/23.
 */
@Repository
public interface RecordDao {

    int insert(Record record);

    List<Record> listByName(@Param("name") String name); // 通过标题模糊查询

    Record selectById(Long id); // 通过id查询

    int updateById(Record record); // 通过id修改

    int deleteById(Long... id);
}
