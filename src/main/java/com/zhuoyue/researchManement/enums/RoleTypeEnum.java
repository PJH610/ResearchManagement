package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum RoleTypeEnum {

    ADMIN(1, RoleType.ADMIN, -1), // 管理员
    EXPERT(2, RoleType.EXPERT, -1), // 专家
    CITY_RESEARCH(3, RoleType.CITY_RESEARCH, 1), // 市科研办管理员
    AREA_RESEARCH(4, RoleType.AREA_RESEARCH, 2), // 区科研办管理员
    SCHOOL_RESEARCH(5, RoleType.SCHOOL_RESEARCH, 3), // 学校管理员
    SUBJECT_HOST(6, RoleType.SUBJECT_HOST, 4); // 课题支持人

    private final int value;
    private final String desc;
    private final int unitLevel;

    RoleTypeEnum(int value, String desc, int unitLevel) {
        this.value = value;
        this.desc = desc;
        this.unitLevel = unitLevel;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public int getUnitLevel() {
        return unitLevel;
    }

    public static RoleTypeEnum valueOf(int value) {
        for (RoleTypeEnum roleTypeEnum : RoleTypeEnum.values()) {
            if (roleTypeEnum.value == value) return roleTypeEnum;
        }
        return null;
    }

}
