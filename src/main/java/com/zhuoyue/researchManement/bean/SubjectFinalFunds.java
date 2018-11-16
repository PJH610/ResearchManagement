package com.zhuoyue.researchManement.bean;

import java.math.BigDecimal;
import java.util.List;

public class SubjectFinalFunds {

    private BigDecimal totalFund;
    private List<SubjectFinalFund> list;

    public SubjectFinalFunds() {
    }

    public SubjectFinalFunds(BigDecimal totalFund, List<SubjectFinalFund> list) {
        this.totalFund = totalFund;
        this.list = list;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public List<SubjectFinalFund> getList() {
        return list;
    }

    public void setList(List<SubjectFinalFund> list) {
        this.list = list;
    }
}
