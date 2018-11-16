package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectCategory {

    UNKNOWN(0, "未知"),
    MAIN(1, "重点课题"),
    PLANNING(2, "规划课题"),
    SELF_FINANCING(3, "自筹经费课题");

    private final int value;
    private final String desc;

    SubjectCategory(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectCategory valueOf(int value){
        for (SubjectCategory category : SubjectCategory.values()){
            if (category.value == value)
                return category;
        }
        return null;
    }
}
