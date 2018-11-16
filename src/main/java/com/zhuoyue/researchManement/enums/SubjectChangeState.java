package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectChangeState {

    DISAPPROVAL(-2, "专家指导小组不予立项"),
    RETURN_BACk(-1, "此次变更未获得通过"),
    UNKNOWN(0, "未知"),
    PRESUBMIT(1, "材料修改中"),
    IN_SCHOOL_CHECK(2, "单位管理员审核中"),
    IN_AREA_CHECK(3, "区科研办管理员审核中"),
    IN_CITY_CHECK(4, "市科研办管理员审核中"),
    IN_EXPERT_CHECK(5, "专家指导小组评审中"),
    COMPLETE(6, "同意"),
    ;

    private final int value;
    private final String desc;

    SubjectChangeState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectChangeState valueOf(int value) {
        for (SubjectChangeState state : SubjectChangeState.values()) {
            if (state.value == value) return state;
        }
        return null;
    }
}
