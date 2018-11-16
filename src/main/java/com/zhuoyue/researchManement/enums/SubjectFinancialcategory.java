package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectFinancialcategory {

    UNKNOWN(0, "未知"),
    CATEGORY_A(1, "资助"),
    CATEGORY_B(2, "自筹");

    private final int value;
    private final String desc;

    SubjectFinancialcategory(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectFinancialcategory valueOf(int value){
        for (SubjectFinancialcategory subjectFinancialcategory : SubjectFinancialcategory.values()){
            if (subjectFinancialcategory.value == value)
                return subjectFinancialcategory;
        }
        return null;
    }
}
