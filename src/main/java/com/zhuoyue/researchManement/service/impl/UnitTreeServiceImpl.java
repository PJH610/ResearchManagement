package com.zhuoyue.researchManement.service.impl;

import com.zhuoyue.researchManement.bean.Area;
import com.zhuoyue.researchManement.bean.Unit;
import com.zhuoyue.researchManement.bean.UnitTree;
import com.zhuoyue.researchManement.dao.UnitTreeDao;
import com.zhuoyue.researchManement.service.UnitTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitTreeServiceImpl implements UnitTreeService {

    @Autowired
    private UnitTreeDao unitTreeDao;

    @Override
    public int insert(UnitTree unitTree) {
        return unitTreeDao.insert(unitTree);
    }

    @Override
    public UnitTree selectByUnitId(Long unitId) {
        return unitTreeDao.selectByUnitId(unitId);
    }

    @Override
    public Long[] listUnitIdByParentId(Long... parentId) {
        return unitTreeDao.listUnitIdByParentId(parentId);
    }

    @Override
    public List<Area> listArea() {
        return unitTreeDao.listArea();
    }

    @Override
    public int updateByUnitId(UnitTree unitTree) {
        return unitTreeDao.updateByUnitId(unitTree);
    }

    @Override
    public int deleteByUnitId(Long... unitId) {
        return unitTreeDao.deleteByUnitId(unitId);
    }
}
