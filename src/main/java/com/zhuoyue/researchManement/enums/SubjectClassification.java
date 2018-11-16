package com.zhuoyue.researchManement.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties(value = {"declaring_class"})
public enum SubjectClassification {

    UNKNOWN(0, "未知"),
    SYNTHETIC_RESEARCH(1, "综合研究"),
    MORAL_RESAERCH(2, "德育研究"),
    TEACHING_RESEARCH(3, "学校教学研究"),
    CURRICULUM_DEVELOPMENT_RESEARCH(4, "课程开发研究"),
    MENTAL_HEALTH_RESEARCH(5, "心里健康教育研究"),
    BOOK_MANGEMENT_RESEARCH(6, "图书管理研究"),
    PERSCHOOL_EDUCATION_RESEARCH(7, "学前教育研究"),
    SPECIAL_EDUCATION_RESEARCH(7, "特殊教育研究"),
    TEAHCER_EDUCATION_RESEARCH(7, "教师教育研究"),
    OTHER_RESEARCH(0, "其它研究");

    private final int value;
    private final String desc;

    SubjectClassification(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static SubjectClassification valueOf(int value){
        for (SubjectClassification classification : SubjectClassification.values()){
            if (classification.value == value)
                return classification;
        }
        return null;
    }
}
