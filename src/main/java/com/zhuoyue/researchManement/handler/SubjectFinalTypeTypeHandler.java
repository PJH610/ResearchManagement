package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectFinalType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectFinalTypeTypeHandler implements TypeHandler<SubjectFinalType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectFinalType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectFinalType getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectFinalType.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectFinalType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectFinalType.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectFinalType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectFinalType.valueOf(cs.getInt(columnIndex));
    }
}
