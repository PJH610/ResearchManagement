package com.zhuoyue.researchManement.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhuoyue.researchManement.serializer.BigDecimalJsonSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by SX-503 on 2018/5/22.
 */
public class SubjectBudget {

    private Date year;
    private BigDecimal money;

    public SubjectBudget() {
    }

    public SubjectBudget(Date year, BigDecimal money) {
        this.year = year;
        this.money = money;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
