package com.globalsync.bonus.system.repository.mapper;

import com.globalsync.bonus.system.domain.BonusRecord;
import com.globalsync.bonus.system.domain.enums.BonusCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BonusRecordMapper implements RowMapper<BonusRecord> {
    @Override
    public BonusRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BonusRecord(
                rs.getInt("id"),
                rs.getInt("employee_id"),
                rs.getInt("review_year"),
                rs.getInt("total_kpi_score"),
                BonusCategory.valueOf(rs.getString("category")),
                rs.getFloat("bonus_percentage"),
                rs.getFloat("bonus_amount"),
                rs.getFloat("total_compensation")
        );
    }
}