package com.zhuoyue.researchManement.bean;

import java.math.BigDecimal;
import java.util.Date;

public class SubjectFinalFund {

    private Long id;
    private Long subjectId;
    private Date year;
    private BigDecimal fund;
    private BigDecimal otherFund;
    private BigDecimal totalFund;

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

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public BigDecimal getOtherFund() {
        return otherFund;
    }

    public void setOtherFund(BigDecimal otherFund) {
        this.otherFund = otherFund;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }
}
