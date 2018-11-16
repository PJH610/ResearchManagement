package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectCategory;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectCategoryTypeHandler implements TypeHandler<SubjectCategory> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectCategory parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectCategory getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectCategory.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectCategory getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectCategory.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectCategory getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectCategory.valueOf(cs.getInt(columnIndex));
    }
}
