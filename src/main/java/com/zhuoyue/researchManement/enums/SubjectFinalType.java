package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum  SubjectFinalType {

    COMMUNICATION(1, "通信"),
    MEETING(2, "会议"),
    ;

    private final int value;
    private final String desc;

    SubjectFinalType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectFinalType valueOf(int value){
        for (SubjectFinalType type : values()){
            if (type.value == value)
                return type;
        }
        return null;
    }
}
