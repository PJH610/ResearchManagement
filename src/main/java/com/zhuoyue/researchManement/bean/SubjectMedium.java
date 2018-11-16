package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuoyue.researchManement.enums.SubjectMediumState;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(value = {"handler"})
public class SubjectMedium {

    private Long id;
    private Long subjectId;
    private SubjectMediumState state;
    private String changes;
    private Boolean adjust;
    private String adjustReason;
    private String situation;
    private BigDecimal funds;
    private BigDecimal supportingFunds;
    private BigDecimal usedFunds;
    private String fundsDetail;
    private DownloadFile file;
    private String schoolNote;
    private String schoolLeaderName;
    private Date schoolCheckTime;
    private String cityNote;
    private String cityLeaderName;
    private Date cityCheckTime;
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

    public SubjectMediumState getState() {
        return state;
    }

    public void setState(SubjectMediumState state) {
        this.state = state;
    }

    public String getChanges() {
        return changes;
    }

    public void setChanges(String changes) {
        this.changes = changes;
    }

    public Boolean getAdjust() {
        return adjust;
    }

    public void setAdjust(Boolean adjust) {
        this.adjust = adjust;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public BigDecimal getSupportingFunds() {
        return supportingFunds;
    }

    public void setSupportingFunds(BigDecimal supportingFunds) {
        this.supportingFunds = supportingFunds;
    }

    public BigDecimal getUsedFunds() {
        return usedFunds;
    }

    public void setUsedFunds(BigDecimal usedFunds) {
        this.usedFunds = usedFunds;
    }

    public String getFundsDetail() {
        return fundsDetail;
    }

    public void setFundsDetail(String fundsDetail) {
        this.fundsDetail = fundsDetail;
    }

    public DownloadFile getFile() {
        return file;
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

    public Date getSchoolCheckTime() {
        return schoolCheckTime;
    }

    public void setSchoolCheckTime(Date schoolCheckTime) {
        this.schoolCheckTime = schoolCheckTime;
    }

    public String getCityNote() {
        return cityNote;
    }

    public void setCityNote(String cityNote) {
        this.cityNote = cityNote;
    }

    public String getCityLeaderName() {
        return cityLeaderName;
    }

    public void setCityLeaderName(String cityLeaderName) {
        this.cityLeaderName = cityLeaderName;
    }

    public Date getCityCheckTime() {
        return cityCheckTime;
    }

    public void setCityCheckTime(Date cityCheckTime) {
        this.cityCheckTime = cityCheckTime;
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
