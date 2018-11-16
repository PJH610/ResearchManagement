package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class SubjectResult {
    private Long id;
    private Long subjectId;
    private Date completetime;
    private String finalresultName;
    private String finalresultShape;
    private String principal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    public Date getCompletetime() {
        return completetime;
    }

    public void setCompletetime(Date completetime) {
        this.completetime = completetime;
    }

    public String getFinalresultName() {
        return finalresultName;
    }

    public void setFinalresultName(String finalresultName) {
        this.finalresultName = finalresultName;
    }

    public String getFinalresultShape() {
        return finalresultShape;
    }

    public void setFinalresultShape(String finalresultShape) {
        this.finalresultShape = finalresultShape;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
