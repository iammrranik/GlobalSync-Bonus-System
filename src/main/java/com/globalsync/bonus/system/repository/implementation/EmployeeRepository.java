package com.globalsync.bonus.system.repository.implementation;

import com.globalsync.bonus.system.domain.Employee;
import com.globalsync.bonus.system.repository.IEmployeeRepository;
import com.globalsync.bonus.system.repository.mapper.EmployeeMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Employee employee) {
        String sql = "INSERT INTO employees (name, designation, base_salary, role, last_promotion_date) " +
                "VALUES (:name, :designation, :baseSalary, :role, :lastPromotionDate)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", employee.getName())
                .addValue("designation", employee.getDesignation())
                .addValue("baseSalary", employee.getBaseSalary())
                .addValue("role", employee.getRole().name())
                .addValue("lastPromotionDate", employee.getLastPromotionDate());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        String sql = "SELECT * FROM employees WHERE employee_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            Employee employee = namedParameterJdbcTemplate.queryForObject(sql, params, new EmployeeMapper());
            return Optional.ofNullable(employee);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM employees LIMIT :size OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("size", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, new EmployeeMapper());
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM employees";
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public int update(Employee employee) {
        String sql = "UPDATE employees SET name = :name, designation = :designation, " +
                "base_salary = :baseSalary, role = :role, last_promotion_date = :lastPromotionDate " +
                "WHERE employee_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", employee.getId())
                .addValue("name", employee.getName())
                .addValue("designation", employee.getDesignation())
                .addValue("baseSalary", employee.getBaseSalary())
                .addValue("role", employee.getRole().name())
                .addValue("lastPromotionDate", employee.getLastPromotionDate());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM employees WHERE employee_id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}
