package com.globalsync.bonus.system.repository.implementation;

import com.globalsync.bonus.system.domain.BonusRecord;
import com.globalsync.bonus.system.repository.IBonusRepository;
import com.globalsync.bonus.system.repository.mapper.BonusRecordMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BonusRecordRepository implements IBonusRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public BonusRecordRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(BonusRecord record) {
        String sql = "INSERT INTO bonus_records (employee_id, review_year, total_kpi_score, category, " +
                "bonus_percentage, bonus_amount, total_compensation) " +
                "VALUES (:empId, :year, :score, :cat, :pct, :amt, :comp)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId", record.getEmployeeId())
                .addValue("year", record.getReviewYear())
                .addValue("score", record.getTotalKpiScore())
                .addValue("cat", record.getCategory().name())
                .addValue("pct", record.getBonusPercentage())
                .addValue("amt", record.getBonusAmount())
                .addValue("comp", record.getTotalCompensation());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<BonusRecord> findById(Integer id) {
        String sql = "SELECT * FROM bonus_records WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            BonusRecord record = namedParameterJdbcTemplate.queryForObject(sql, params, new BonusRecordMapper());
            return Optional.ofNullable(record);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<BonusRecord> findAll() {
        String sql = "SELECT * FROM bonus_records";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new BonusRecordMapper());
    }

    @Override
    public List<BonusRecord> findAll(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM bonus_records LIMIT :size OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("size", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, new BonusRecordMapper());
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM bonus_records";
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public int update(BonusRecord record) {
        String sql = "UPDATE bonus_records SET employee_id = :empId, review_year = :year, " +
                "total_kpi_score = :score, category = :cat, bonus_percentage = :pct, " +
                "bonus_amount = :amt, total_compensation = :comp WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", record.getId())
                .addValue("empId", record.getEmployeeId())
                .addValue("year", record.getReviewYear())
                .addValue("score", record.getTotalKpiScore())
                .addValue("cat", record.getCategory().name())
                .addValue("pct", record.getBonusPercentage())
                .addValue("amt", record.getBonusAmount())
                .addValue("comp", record.getTotalCompensation());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM bonus_records WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public boolean existsByEmployeeIdAndReviewYear(Integer employeeId, Integer reviewYear) {
        String sql = "SELECT COUNT(*) FROM bonus_records WHERE employee_id = :empId AND review_year = :year";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId", employeeId)
                .addValue("year", reviewYear);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }
}

