package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectFinalState {

    DISAPPROVAL(-2, "专家指导小组不予结题"),
    RETURN_BACk(-1, "材料被退回"),
    UNKNOWN(0, "未知"),
    PRESUBMIT(1, "材料修改中"),
    IN_SCHOOL_CHECK(2, "单位管理员审核中"),
    IN_AREA_CHECK(3, "区科研办管理员审核中"),
    IN_CITY_CHECK(4, "市科研办管理员审核中"),
    PASS_EXPERT_CHECK(5, "专家指导小组建议结题"),
    PASS_FIRSTTRIAL(6, "市教育局科研办初审通过"),
    COMPLETE(7, "完成结题"),
    ;

    private final int value;
    private final String desc;

    SubjectFinalState(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectFinalState valueOf(int value){
        for (SubjectFinalState state : values()){
            if (state.value == value)
                return state;
        }
        return null;
    }
}
