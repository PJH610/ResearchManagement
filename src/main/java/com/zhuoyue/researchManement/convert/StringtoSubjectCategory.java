package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectCategory;
import org.springframework.core.convert.converter.Converter;

public class StringtoSubjectCategory implements Converter<String, SubjectCategory> {

    @Override
    public SubjectCategory convert(String source) {
        return SubjectCategory.valueOf(Integer.valueOf(source));
    }
}
