package com.zhuoyue.researchManement.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhuoyue.researchManement.bean.SubjectBudget;
import com.zhuoyue.researchManement.enums.SubjectClassification;
import org.apache.ibatis.type.*;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectBudgetsTypeHandler extends BaseTypeHandler<List<SubjectBudget>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<SubjectBudget> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, new Gson().toJson(parameter));
    }

    @Override
    public List<SubjectBudget> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Gson().fromJson(rs.getString(columnName), new TypeToken<ArrayList<SubjectBudget>>(){}.getType());
    }

    @Override
    public List<SubjectBudget> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Gson().fromJson(rs.getString(columnIndex), new TypeToken<ArrayList<SubjectBudget>>(){}.getType());
    }

    @Override
    public List<SubjectBudget> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Gson().fromJson(cs.getString(columnIndex), new TypeToken<ArrayList<SubjectBudget>>(){}.getType());
    }

}
