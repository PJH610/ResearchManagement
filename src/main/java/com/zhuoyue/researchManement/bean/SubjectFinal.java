package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuoyue.researchManement.enums.SubjectFinalState;
import com.zhuoyue.researchManement.enums.SubjectFinalType;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class SubjectFinal {

    private Long id;
    private Long subjectId;
    private SubjectFinalState state;
    private Date planningTime;
    private String planningForm;
    private Date endTime;
    private Date acceptanceTime;
    private SubjectFinalType type;
    private String achievement;
    private String content;
    private String situation;
    private String changes;
    private DownloadFile file;
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

    public SubjectFinalState getState() {
        return state;
    }

    public void setState(SubjectFinalState state) {
        this.state = state;
    }

    public Date getPlanningTime() {
        return planningTime;
    }

    public void setPlanningTime(Date planningTime) {
        this.planningTime = planningTime;
    }

    public String getPlanningForm() {
        return planningForm;
    }

    public void setPlanningForm(String planningForm) {
        this.planningForm = planningForm;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Date acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    public SubjectFinalType getType() {
        return type;
    }

    public void setType(SubjectFinalType type) {
        this.type = type;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public DownloadFile getFile() {
        return file;
    }

    public void setFile(DownloadFile file) {
        this.file = file;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
