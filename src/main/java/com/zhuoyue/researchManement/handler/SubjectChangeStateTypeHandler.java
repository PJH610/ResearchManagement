package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectChangeState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectChangeStateTypeHandler implements TypeHandler<SubjectChangeState> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectChangeState parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectChangeState getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectChangeState.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectChangeState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectChangeState.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectChangeState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectChangeState.valueOf(cs.getInt(columnIndex));
    }
}
