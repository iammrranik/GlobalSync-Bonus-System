package com.globalsync.bonus.system.repository.mapper;

import com.globalsync.bonus.system.domain.Employee;
import com.globalsync.bonus.system.domain.enums.EmployeeRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        LocalDate lastPromotionDate = rs.getDate("last_promotion_date") != null ? rs.getDate("last_promotion_date").toLocalDate() : null;
        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("name"),
                rs.getString("designation"),
                rs.getFloat("base_salary"),
                EmployeeRole.valueOf(rs.getString("role")),
                lastPromotionDate
        );
    }
}
