package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectFinalType;
import com.zhuoyue.researchManement.enums.SubjectState;
import org.springframework.core.convert.converter.Converter;

public class StringToSubjectFinalType implements Converter<String, SubjectFinalType> {

    @Override
    public SubjectFinalType convert(String source) {
        return SubjectFinalType.valueOf(Integer.valueOf(source));
    }
}
