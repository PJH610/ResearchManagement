package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectFinancialcategory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialcategoryTypeHandler implements TypeHandler<SubjectFinancialcategory> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectFinancialcategory parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectFinancialcategory getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectFinancialcategory.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectFinancialcategory getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectFinancialcategory.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectFinancialcategory getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectFinancialcategory.valueOf(cs.getInt(columnIndex));
    }
}























