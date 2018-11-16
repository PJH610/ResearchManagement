package com.zhuoyue.researchManement.convert;

import com.zhuoyue.researchManement.enums.SubjectFinancialcategory;
import org.springframework.core.convert.converter.Converter;

public class StringToFinancialcategoryConvert implements Converter<String, SubjectFinancialcategory> {

    @Override
    public SubjectFinancialcategory convert(String source) {
        return SubjectFinancialcategory.valueOf(Integer.valueOf(source));
    }
}
