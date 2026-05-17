package com.globalsync.bonus.system.repository;

import com.globalsync.bonus.system.domain.BonusRecord;
import java.util.List;
import java.util.Optional;

public interface IBonusRepository {
    int save(BonusRecord record);
    Optional<BonusRecord> findById(Integer id);
    List<BonusRecord> findAll();
    List<BonusRecord> findAll(int page, int size);
    int count();
    int update(BonusRecord record);
    int delete(int id);
    boolean existsByEmployeeIdAndReviewYear(Integer employeeId, Integer reviewYear);
}

