package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class SubjectApproval {

    private Long id;
    private Long subjectId;
    private String schoolNote;
    private String schoolLeaderName;
    private Date schoolCheckTime;
    private String areaNote;
    private String areaLeaderName;
    private Date areaCheckTime;
    private String cityNote;
    private String cityLeaderName;
    private Date cityCheckTime;
    private List<SubjectSpecialist> specialists;
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

    public String getAreaNote() {
        return areaNote;
    }

    public void setAreaNote(String areaNote) {
        this.areaNote = areaNote;
    }

    public String getAreaLeaderName() {
        return areaLeaderName;
    }

    public void setAreaLeaderName(String areaLeaderName) {
        this.areaLeaderName = areaLeaderName;
    }

    public Date getAreaCheckTime() {
        return areaCheckTime;
    }

    public void setAreaCheckTime(Date areaCheckTime) {
        this.areaCheckTime = areaCheckTime;
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

    public List<SubjectSpecialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(List<SubjectSpecialist> specialists) {
        this.specialists = specialists;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
