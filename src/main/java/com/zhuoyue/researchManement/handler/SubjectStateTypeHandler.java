package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectStateTypeHandler implements TypeHandler<SubjectState> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectState parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectState getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectState.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectState.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectState.valueOf(cs.getInt(columnIndex));
    }
}
