package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectMediumState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectMediumStateTypeHandler implements TypeHandler<SubjectMediumState> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectMediumState parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectMediumState getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectMediumState.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectMediumState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectMediumState.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectMediumState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectMediumState.valueOf(cs.getInt(columnIndex));
    }
}
