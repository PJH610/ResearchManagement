package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhuoyue.researchManement.serializer.BigDecimalJsonSerializer;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by SX-503 on 2018/5/23.
 */
public class SubjectFund {

    private Long id;
    private Long subjectId;
    private BigDecimal data;
    private BigDecimal travel;
    private BigDecimal meeting;
    private BigDecimal equipment;
    private BigDecimal service;
    private BigDecimal print;
    private BigDecimal identification;
    private BigDecimal other;
    private BigDecimal funding;
    private BigDecimal selfraised;
    private BigDecimal total;
    private List<SubjectBudget> budgets;

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

    public BigDecimal getData() {
        return data;
    }

    public void setData(BigDecimal data) {
        this.data = data;
    }

    public BigDecimal getTravel() {
        return travel;
    }

    public void setTravel(BigDecimal travel) {
        this.travel = travel;
    }

    public BigDecimal getMeeting() {
        return meeting;
    }

    public void setMeeting(BigDecimal meeting) {
        this.meeting = meeting;
    }

    public BigDecimal getEquipment() {
        return equipment;
    }

    public void setEquipment(BigDecimal equipment) {
        this.equipment = equipment;
    }

    public BigDecimal getService() {
        return service;
    }

    public void setService(BigDecimal service) {
        this.service = service;
    }

    public BigDecimal getPrint() {
        return print;
    }

    public void setPrint(BigDecimal print) {
        this.print = print;
    }

    public BigDecimal getIdentification() {
        return identification;
    }

    public void setIdentification(BigDecimal identification) {
        this.identification = identification;
    }

    public BigDecimal getOther() {
        return other;
    }

    public void setOther(BigDecimal other) {
        this.other = other;
    }

    public BigDecimal getFunding() {
        return funding;
    }

    public void setFunding(BigDecimal funding) {
        this.funding = funding;
    }

    public BigDecimal getSelfraised() {
        return selfraised;
    }

    public void setSelfraised(BigDecimal selfraised) {
        this.selfraised = selfraised;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<SubjectBudget> getBudgets() {
        return budgets;
    }

    public void setBudgets(List<SubjectBudget> budgets) {
        this.budgets = budgets;
    }
}
