package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectState;
import org.springframework.core.convert.converter.Converter;

public class StringToSubjectState implements Converter<String, SubjectState> {

    @Override
    public SubjectState convert(String source) {
        return SubjectState.valueOf(Integer.valueOf(source));
    }
}
