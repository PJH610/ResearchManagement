package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectFinalState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectFinalStateTypeHandler implements TypeHandler<SubjectFinalState> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectFinalState parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectFinalState getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectFinalState.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectFinalState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectFinalState.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectFinalState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectFinalState.valueOf(cs.getInt(columnIndex));
    }
}
