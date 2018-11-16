package com.zhuoyue.researchManement.dao;

import com.zhuoyue.researchManement.bean.Area;
import com.zhuoyue.researchManement.bean.Unit;
import com.zhuoyue.researchManement.bean.UnitTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitTreeDao {

    int insert(UnitTree unitTree);

    UnitTree selectByUnitId(@Param("unitId") Long unitId);

    Long[] listUnitIdByParentId(@Param("parentId") Long... parentId);

    List<Area> listArea();

    int updateByUnitId(UnitTree unitTree);

    int deleteByUnitId(Long... unitId);
}
