package com.globalsync.bonus.system.repository.implementation;

import com.globalsync.bonus.system.domain.PerformanceReview;
import com.globalsync.bonus.system.repository.IPerformanceRepository;
import com.globalsync.bonus.system.repository.mapper.PerformanceReviewMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerformanceReviewRepository implements IPerformanceRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public PerformanceReviewRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(PerformanceReview review) {
        String sql = "INSERT INTO performance_reviews (employee_id, review_year, task_completion, attendance, " +
                "team_collaboration, problem_solving, communication, leadership, client_satisfaction) " +
                "VALUES (:empId, :year, :tc, :at, :tcb, :ps, :com, :ld, :cs)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("empId", review.getEmployeeId())
                .addValue("year", review.getReviewYear())
                .addValue("tc", review.getTaskCompletionRate())
                .addValue("at", review.getAttendanceAndPunctuality())
                .addValue("tcb", review.getTeamCollaboration())
                .addValue("ps", review.getProblemSolvingSkill())
                .addValue("com", review.getCommunicationSkill())
                .addValue("ld", review.getLeadershipAndInitiative())
                .addValue("cs", review.getClientSatisfaction());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<PerformanceReview> findById(Integer id) {
        String sql = "SELECT * FROM performance_reviews WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        try {
            PerformanceReview review = namedParameterJdbcTemplate.queryForObject(sql, params, new PerformanceReviewMapper());
            return Optional.ofNullable(review);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PerformanceReview> findAll() {
        String sql = "SELECT * FROM performance_reviews";
        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new PerformanceReviewMapper());
    }

    @Override
    public List<PerformanceReview> findAll(int page, int size) {
        int offset = (page - 1) * size;
        String sql = "SELECT * FROM performance_reviews LIMIT :size OFFSET :offset";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("size", size)
                .addValue("offset", offset);
        return namedParameterJdbcTemplate.query(sql, params, new PerformanceReviewMapper());
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM performance_reviews";
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource(), Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public int update(PerformanceReview review) {
        String sql = "UPDATE performance_reviews SET employee_id = :empId, review_year = :year, " +
                "task_completion = :tc, attendance = :at, team_collaboration = :tcb, " +
                "problem_solving = :ps, communication = :com, leadership = :ld, client_satisfaction = :cs " +
                "WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", review.getId())
                .addValue("empId", review.getEmployeeId())
                .addValue("year", review.getReviewYear())
                .addValue("tc", review.getTaskCompletionRate())
                .addValue("at", review.getAttendanceAndPunctuality())
                .addValue("tcb", review.getTeamCollaboration())
                .addValue("ps", review.getProblemSolvingSkill())
                .addValue("com", review.getCommunicationSkill())
                .addValue("ld", review.getLeadershipAndInitiative())
                .addValue("cs", review.getClientSatisfaction());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM performance_reviews WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return namedParameterJdbcTemplate.update(sql, params);
    }
}