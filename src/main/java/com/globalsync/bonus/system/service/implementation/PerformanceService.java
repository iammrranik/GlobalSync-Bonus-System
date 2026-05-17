package com.globalsync.bonus.system.service.implementation;

import com.globalsync.bonus.system.domain.PerformanceReview;
import com.globalsync.bonus.system.repository.IPerformanceRepository;
import com.globalsync.bonus.system.service.IPerformanceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService implements IPerformanceService {

    private final IPerformanceRepository performanceRepository;

    public PerformanceService(IPerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    @Override
    public int addPerformanceReview(PerformanceReview review) {
        return performanceRepository.save(review);
    }

    @Override
    public Optional<PerformanceReview> getPerformanceReviewById(Integer id) {
        return performanceRepository.findById(id);
    }

    @Override
    public List<PerformanceReview> getAllPerformanceReviews() {
        return performanceRepository.findAll();
    }

    @Override
    public List<PerformanceReview> getAllPerformanceReviews(int page, int size) {
        return performanceRepository.findAll(page, size);
    }

    @Override
    public int getPerformanceReviewCount() {
        return performanceRepository.count();
    }

    @Override
    public int updatePerformanceReview(PerformanceReview review) {
        return performanceRepository.update(review);
    }

    @Override
    public int removePerformanceReview(int id) {
        return performanceRepository.delete(id);
    }
}