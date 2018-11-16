package com.zhuoyue.researchManement.service;

import com.zhuoyue.researchManement.bean.Area;
import com.zhuoyue.researchManement.bean.Unit;
import com.zhuoyue.researchManement.bean.UnitTree;

import java.util.List;

public interface UnitTreeService {

    int insert(UnitTree unitTree);

    UnitTree selectByUnitId(Long unitId);

    Long[] listUnitIdByParentId(Long... parentId);

    List<Area> listArea();

    int updateByUnitId(UnitTree unitTree);

    int deleteByUnitId(Long... unitId);

}
