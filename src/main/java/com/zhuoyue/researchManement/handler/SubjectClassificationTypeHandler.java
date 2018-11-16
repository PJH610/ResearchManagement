package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectClassification;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectClassificationTypeHandler implements TypeHandler<SubjectClassification> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectClassification parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectClassification getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectClassification.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectClassification getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectClassification.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectClassification getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectClassification.valueOf(cs.getInt(columnIndex));
    }
}
