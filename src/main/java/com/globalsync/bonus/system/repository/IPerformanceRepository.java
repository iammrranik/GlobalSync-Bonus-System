package com.globalsync.bonus.system.repository;

import com.globalsync.bonus.system.domain.PerformanceReview;
import java.util.List;
import java.util.Optional;

public interface IPerformanceRepository {
    int save(PerformanceReview review);
    Optional<PerformanceReview> findById(Integer id);
    List<PerformanceReview> findAll();
    List<PerformanceReview> findAll(int page, int size);
    int count();
    int update(PerformanceReview review);
    int delete(int id);
}