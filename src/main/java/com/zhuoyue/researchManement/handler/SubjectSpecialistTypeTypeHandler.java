package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.SubjectSpecialistType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectSpecialistTypeTypeHandler implements TypeHandler<SubjectSpecialistType> {

    @Override
    public void setParameter(PreparedStatement ps, int i, SubjectSpecialistType parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public SubjectSpecialistType getResult(ResultSet rs, String columnName) throws SQLException {
        return SubjectSpecialistType.valueOf(rs.getInt(columnName));
    }

    @Override
    public SubjectSpecialistType getResult(ResultSet rs, int columnIndex) throws SQLException {
        return SubjectSpecialistType.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public SubjectSpecialistType getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SubjectSpecialistType.valueOf(cs.getInt(columnIndex));
    }
}
