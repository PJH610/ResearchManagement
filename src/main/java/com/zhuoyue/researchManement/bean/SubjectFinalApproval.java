package com.zhuoyue.researchManement.bean;

import java.util.Date;

public class SubjectFinalApproval {

    private Long id;
    private Long subjectId;
    private String schoolNote;
    private String schoolLeaderName;
    private Boolean schoolCheck;
    private Date schoolCheckTime;
    private DownloadFile expertOpinion;
    private String firstTrialOpinion;
    private String firstTrialLeaderName;
    private Boolean firstTrialCheck;
    private Date firstTrialCheckTime;
    private String finalTrialOpinion;
    private String finalTrialLeaderName;
    private Boolean finalTrialCheck;
    private Date finalTrialCheckTime;

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

    public String getSchoolNote() {
        return schoolNote;
    }

    public void setSchoolNote(String schoolNote) {
        this.schoolNote = schoolNote;
    }

    public String getSchoolLeaderName() {
        return schoolLeaderName;
    }

    public void setSchoolLeaderName(String schoolLeaderName) {
        this.schoolLeaderName = schoolLeaderName;
    }

    public Boolean getSchoolCheck() {
        return schoolCheck;
    }

    public void setSchoolCheck(Boolean schoolCheck) {
        this.schoolCheck = schoolCheck;
    }

    public Date getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(Date schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
    }

    public DownloadFile getExpertOpinion() {
        return expertOpinion;
    }

    public void setExpertOpinion(DownloadFile expertOpinion) {
        this.expertOpinion = expertOpinion;
    }

    public String getFirstTrialOpinion() {
        return firstTrialOpinion;
    }

    public void setFirstTrialOpinion(String firstTrialOpinion) {
        this.firstTrialOpinion = firstTrialOpinion;
    }

    public String getFirstTrialLeaderName() {
        return firstTrialLeaderName;
    }

    public void setFirstTrialLeaderName(String firstTrialLeaderName) {
        this.firstTrialLeaderName = firstTrialLeaderName;
    }

    public Boolean getFirstTrialCheck() {
        return firstTrialCheck;
    }

    public void setFirstTrialCheck(Boolean firstTrialCheck) {
        this.firstTrialCheck = firstTrialCheck;
    }

    public Date getFirstTrialCheckTime() {
        return firstTrialCheckTime;
    }

    public void setFirstTrialCheckTime(Date firstTrialCheckTime) {
        this.firstTrialCheckTime = firstTrialCheckTime;
    }

    public String getFinalTrialOpinion() {
        return finalTrialOpinion;
    }

    public void setFinalTrialOpinion(String finalTrialOpinion) {
        this.finalTrialOpinion = finalTrialOpinion;
    }

    public String getFinalTrialLeaderName() {
        return finalTrialLeaderName;
    }

    public void setFinalTrialLeaderName(String finalTrialLeaderName) {
        this.finalTrialLeaderName = finalTrialLeaderName;
    }

    public Boolean getFinalTrialCheck() {
        return finalTrialCheck;
    }

    public void setFinalTrialCheck(Boolean finalTrialCheck) {
        this.finalTrialCheck = finalTrialCheck;
    }

    public Date getFinalTrialCheckTime() {
        return finalTrialCheckTime;
    }

    public void setFinalTrialCheckTime(Date finalTrialCheckTime) {
        this.finalTrialCheckTime = finalTrialCheckTime;
    }
}
