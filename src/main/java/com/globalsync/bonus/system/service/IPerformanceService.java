package com.globalsync.bonus.system.service;

import com.globalsync.bonus.system.domain.PerformanceReview;
import java.util.List;
import java.util.Optional;

public interface IPerformanceService {
    int addPerformanceReview(PerformanceReview review);
    Optional<PerformanceReview> getPerformanceReviewById(Integer id);
    List<PerformanceReview> getAllPerformanceReviews();
    List<PerformanceReview> getAllPerformanceReviews(int page, int size);
    int getPerformanceReviewCount();
    int updatePerformanceReview(PerformanceReview review);
    int removePerformanceReview(int id);
}