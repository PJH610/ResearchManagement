package com.zhuoyue.researchManement.handler;

import com.zhuoyue.researchManement.enums.RoleTypeEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleTypeEnumTypeHandler implements TypeHandler<RoleTypeEnum> {

    @Override
    public void setParameter(PreparedStatement ps, int i, RoleTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) ps.setInt(i, parameter.getValue());
    }

    @Override
    public RoleTypeEnum getResult(ResultSet rs, String columnName) throws SQLException {
        return RoleTypeEnum.valueOf(rs.getInt(columnName));
    }

    @Override
    public RoleTypeEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
        return RoleTypeEnum.valueOf(rs.getInt(columnIndex));
    }

    @Override
    public RoleTypeEnum getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return RoleTypeEnum.valueOf(cs.getInt(columnIndex));
    }
}
