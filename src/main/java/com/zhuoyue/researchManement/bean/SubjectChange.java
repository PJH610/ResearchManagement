package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuoyue.researchManement.enums.SubjectChangeState;
import com.zhuoyue.researchManement.enums.SubjectChangeType;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class SubjectChange {

    private Long id;
    private Long subjectId;
    private SubjectChangeType type;
    private SubjectChangeState state;
    private Long userId;
    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    private Date completeTime;
    private String finalresult;
    private Subject subject;

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

    public SubjectChangeType getType() {
        return type;
    }

    public void setType(SubjectChangeType type) {
        this.type = type;
    }

    public SubjectChangeState getState() {
        return state;
    }

    public void setState(SubjectChangeState state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(String finalresult) {
        this.finalresult = finalresult;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
