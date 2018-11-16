package com.zhuoyue.researchManement.controller;

import com.zhuoyue.researchManement.bean.UnitTree;
import com.zhuoyue.researchManement.service.UnitTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

// 数据库中必须有 {parent_id:null,unit_id:null,level:0} 的默认父节点
@RestController
@RequestMapping("/api/unit")
public class UnitController extends BaseApiController {

    //限制每个节点下的子元素数量
    private static final int LIMIT_TREE_COUNT = 500;

    @Autowired
    private UnitTreeService unitTreeService;

    @PostMapping("/tree/add")
    public Map<String, Object> treeAdd(Long parent_id, @RequestParam Long unit_id) {
        UnitTree unitTree = unitTreeService.selectByUnitId(parent_id);
        if (unitTree == null) return onBadResp("父节点不正确");
        if (unitTree.getChild().size() >= LIMIT_TREE_COUNT) return onBadResp("无法继续添加");

        unitTreeService.insert(new UnitTree(parent_id, unit_id, unitTree.getLevel() + 1, ""));
        return onSuccessRep("添加成功");
    }

    @GetMapping("/tree/show")
    public Map<String, Object> treeShow() {
        return onDataResp(unitTreeService.selectByUnitId(null));
    }

    @GetMapping("/area/list")
    public Map<String, Object> areaList() {
        return onDataResp(unitTreeService.listArea());
    }

    @GetMapping("/tree/show/{unit_id}")
    public Map<String, Object> treeShowUnit(@PathVariable Long unit_id) {
        return onDataResp(unitTreeService.selectByUnitId(unit_id));
    }
}
