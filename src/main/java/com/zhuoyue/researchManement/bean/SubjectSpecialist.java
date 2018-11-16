package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;

import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class SubjectSpecialist {

    private Long id;
    private Long userId;
    private String realname;
    private Long subjectId;
    private SubjectSpecialistType type;
    private String note;
    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    private Date checkTime;
    private Integer valueScore;
    private Integer achievementScore;
    private Integer validityScore;
    private Integer scientificScore;
    private Integer conditionScore;
    private Integer totalScore;
    private Boolean checkState;
    private Subject subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public SubjectSpecialistType getType() {
        return type;
    }

    public void setType(SubjectSpecialistType type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getValueScore() {
        return valueScore;
    }

    public void setValueScore(Integer valueScore) {
        this.valueScore = valueScore;
    }

    public Integer getAchievementScore() {
        return achievementScore;
    }

    public void setAchievementScore(Integer achievementScore) {
        this.achievementScore = achievementScore;
    }

    public Integer getValidityScore() {
        return validityScore;
    }

    public void setValidityScore(Integer validityScore) {
        this.validityScore = validityScore;
    }

    public Integer getScientificScore() {
        return scientificScore;
    }

    public void setScientificScore(Integer scientificScore) {
        this.scientificScore = scientificScore;
    }

    public Integer getConditionScore() {
        return conditionScore;
    }

    public void setConditionScore(Integer conditionScore) {
        this.conditionScore = conditionScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Boolean getCheckState() {
        return checkState;
    }

    public void setCheckState(Boolean checkState) {
        this.checkState = checkState;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
