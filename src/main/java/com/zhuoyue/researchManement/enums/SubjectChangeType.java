package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectChangeType {

    UNKNOWN(0, "未知"),
    CHANGE_HOST(1, "课题主持人"),
    CHANGE_COMPLETE_TIME(2, "预计完成时间"),
    CHANGE_FINALRESULT(3, "预期最终成果"),
    ;

    private final int value;
    private final String desc;

    SubjectChangeType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectChangeType valueOf(int value) {
        for (SubjectChangeType state : SubjectChangeType.values()) {
            if (state.value == value) return state;
        }
        return null;
    }
}
