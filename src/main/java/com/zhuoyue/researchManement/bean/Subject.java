package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuoyue.researchManement.enums.*;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = {"handler"})
public class Subject {
    private Long id;
    private String name;
    private SubjectState state;
    private Long userId;
    private Date year;
    private SubjectClassification classification;
    private SubjectCategory category;
    private String finalresult;
    private Integer grants;
    private SubjectFinancialcategory financialcategory;
    @JsonFormat(pattern="yyyy年MM月dd日",timezone = "GMT+8")
    private Date completeTime;
    private String bankcard;
    private DownloadFile thesisProposal;
    private SubjectMediumState mediumReportState;
    private SubjectFinalState finalReportState;
    private String projectNumber; // 立项编号
    private String projectReference; // 立项文号
    private String conclusionNumber; // 结题编号
    private String subjectClassification;
    private User user;
    private List<SubjectPersonnel> personnel;
    private SubjectApproval approval;
    private SubjectRecommender recommender;
    private SubjectProof proof;
    private SubjectFeasibility feasibility;
    private SubjectFund fund;
    private List<SubjectSchedule> schedule;
    @JsonIgnore
    private List<SubjectSpecialist> specialists;
    private Boolean canApproval;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectState getState() {
        return state;
    }

    public void setState(SubjectState state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonFormat(pattern="yyyy",timezone = "GMT+8")
    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public SubjectClassification getClassification() {
        return classification;
    }

    public void setClassification(SubjectClassification classification) {
        this.classification = classification;
    }

    public SubjectCategory getCategory() {
        return category;
    }

    public void setCategory(SubjectCategory category) {
        this.category = category;
    }

    public String getFinalresult() {
        return finalresult;
    }

    public void setFinalresult(String finalresult) {
        this.finalresult = finalresult;
    }

    public Integer getGrants() {
        return grants;
    }

    public void setGrants(Integer grants) {
        this.grants = grants;
    }

    public SubjectFinancialcategory getFinancialcategory() {
        return financialcategory;
    }

    public void setFinancialcategory(SubjectFinancialcategory subjectFinancialcategory) {
        this.financialcategory = subjectFinancialcategory;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public DownloadFile getThesisProposal() {
        return thesisProposal;
    }

    public void setThesisProposal(DownloadFile thesisProposal) {
        this.thesisProposal = thesisProposal;
    }

    public SubjectMediumState getMediumReportState() {
        return mediumReportState;
    }

    public void setMediumReportState(SubjectMediumState mediumReportState) {
        this.mediumReportState = mediumReportState;
    }

    public SubjectFinalState getFinalReportState() {
        return finalReportState;
    }

    public void setFinalReportState(SubjectFinalState finalReportState) {
        this.finalReportState = finalReportState;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getProjectReference() {
        return projectReference;
    }

    public void setProjectReference(String projectReference) {
        this.projectReference = projectReference;
    }

    public String getConclusionNumber() {
        return conclusionNumber;
    }

    public void setConclusionNumber(String conclusionNumber) {
        this.conclusionNumber = conclusionNumber;
    }

    public String getSubjectClassification() {
        return subjectClassification;
    }

    public void setSubjectClassification(String subjectClassification) {
        this.subjectClassification = subjectClassification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SubjectPersonnel> getPersonnel() {
        return personnel;
    }

    public void setPersonnel(List<SubjectPersonnel> personnel) {
        this.personnel = personnel;
    }

    public SubjectApproval getApproval() {
        return approval;
    }

    public void setApproval(SubjectApproval approval) {
        this.approval = approval;
    }


    public SubjectRecommender getRecommender() {
        return recommender;
    }

    public void setRecommender(SubjectRecommender recommender) {
        this.recommender = recommender;
    }

    public SubjectProof getProof() {
        return proof;
    }

    public void setProof(SubjectProof proof) {
        this.proof = proof;
    }

    public SubjectFeasibility getFeasibility() {
        return feasibility;
    }

    public void setFeasibility(SubjectFeasibility feasibility) {
        this.feasibility = feasibility;
    }

    public SubjectFund getFund() {
        return fund;
    }

    public void setFund(SubjectFund fund) {
        this.fund = fund;
    }

    public List<SubjectSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<SubjectSchedule> schedule) {
        this.schedule = schedule;
    }

    public List<SubjectSpecialist> getSpecialists() {
        return specialists;
    }

    public void setSpecialists(List<SubjectSpecialist> specialists) {
        this.specialists = specialists;
    }

    public Boolean getCanApproval() {
        return canApproval;
    }

    public void setCanApproval(Boolean canApproval) {
        this.canApproval = canApproval;
    }

}
