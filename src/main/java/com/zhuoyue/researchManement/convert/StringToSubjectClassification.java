package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectClassification;
import org.springframework.core.convert.converter.Converter;

public class StringToSubjectClassification implements Converter<String, SubjectClassification> {

    @Override
    public SubjectClassification convert(String source) {
        return SubjectClassification.valueOf(Integer.valueOf(source));
    }
}
