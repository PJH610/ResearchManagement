package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class UnitTree {

    private Long id;
    private Long parentId;
    private Long unitId;
    private Integer level;
    private String sort;
    private List<Unit> child;

    public UnitTree() {
    }

    public UnitTree(Long parentId, Long unitId, Integer level, String sort) {
        this.parentId = parentId;
        this.unitId = unitId;
        this.level = level;
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Unit> getChild() {
        return child;
    }

    public void setChild(List<Unit> child) {
        this.child = child;
    }
}
