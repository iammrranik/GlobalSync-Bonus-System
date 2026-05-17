package com.globalsync.bonus.system.service;

import com.globalsync.bonus.system.domain.BonusRecord;
import com.globalsync.bonus.system.domain.PerformanceReview;
import java.util.List;
import java.util.Optional;

public interface IBonusService {
    BonusRecord calculateAndSaveBonus(PerformanceReview review);
    Optional<BonusRecord> getBonusRecordById(Integer id);
    List<BonusRecord> getAllBonusRecords();
    List<BonusRecord> getAllBonusRecords(int page, int size);
    int getBonusRecordCount();
    int updateBonusRecord(BonusRecord record);
    int removeBonusRecord(int id);
}