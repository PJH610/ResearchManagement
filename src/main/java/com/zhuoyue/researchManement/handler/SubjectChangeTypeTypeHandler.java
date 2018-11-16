package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectChangeType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectChangeTypeTypeHandler implements TypeHandler<SubjectChangeType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectChangeType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectChangeType getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectChangeType.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectChangeType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectChangeType.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectChangeType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectChangeType.valueOf(cs.getInt(columnIndex));
    }
}
