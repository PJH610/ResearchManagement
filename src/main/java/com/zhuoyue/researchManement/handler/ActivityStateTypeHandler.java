package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.ActivityState;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityStateTypeHandler implements TypeHandler<ActivityState> {
    @Override
    public void setParameter(PreparedStatement ps, int i, ActivityState parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public ActivityState getResult(ResultSet rs, String columnName) throws SQLException {
        return ActivityState.valueOf(rs.getInt(columnName));
    }

    @Override
    public ActivityState getResult(ResultSet rs, int columnIndex) throws SQLException {
        return ActivityState.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public ActivityState getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ActivityState.valueOf(cs.getInt(columnIndex));
    }
}












