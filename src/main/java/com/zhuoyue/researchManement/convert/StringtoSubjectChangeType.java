package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectChangeType;
import org.springframework.core.convert.converter.Converter;

public class StringtoSubjectChangeType implements Converter<String, SubjectChangeType> {

    @Override
    public SubjectChangeType convert(String source) {
        return SubjectChangeType.valueOf(Integer.valueOf(source));
    }
}
