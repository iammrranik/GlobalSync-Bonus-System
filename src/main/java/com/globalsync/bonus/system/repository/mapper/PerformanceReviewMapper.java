package com.globalsync.bonus.system.repository.mapper;

import com.globalsync.bonus.system.domain.PerformanceReview;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerformanceReviewMapper implements RowMapper<PerformanceReview> {
    @Override
    public PerformanceReview mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new PerformanceReview(
                rs.getInt("id"),
                rs.getInt("employee_id"),
                rs.getInt("review_year"),
                rs.getInt("task_completion"),
                rs.getInt("attendance"),
                rs.getInt("team_collaboration"),
                rs.getInt("problem_solving"),
                rs.getInt("communication"),
                rs.getInt("leadership"),
                rs.getInt("client_satisfaction")
        );
    }
}