package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectSpecialistType {

    UNKNOWN(0, "未知"),
    SUBJECT_CHECK(1, "课题申报"),
    MEDIUM_CHECK(2, "中期自评报告"),
    ;

    private final int value;
    private final String desc;

    SubjectSpecialistType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectSpecialistType valueOf(int value){
        for (SubjectSpecialistType type : values()){
            if (type.value == value)
                return type;
        }
        return null;
    }
}
