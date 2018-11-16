package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectCategory;
import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import org.springframework.core.convert.converter.Converter;

public class StringtoSubjectSpecialistType implements Converter<String, SubjectSpecialistType> {

    @Override
    public SubjectSpecialistType convert(String source) {
        return SubjectSpecialistType.valueOf(Integer.valueOf(source));
    }
}
