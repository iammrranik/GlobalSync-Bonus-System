package com.globalsync.bonus.system.service.implementation;

import com.globalsync.bonus.system.domain.BonusRecord;
import com.globalsync.bonus.system.domain.Employee;
import com.globalsync.bonus.system.domain.PerformanceReview;
import com.globalsync.bonus.system.domain.enums.BonusCategory;
import com.globalsync.bonus.system.repository.IBonusRepository;
import com.globalsync.bonus.system.repository.IEmployeeRepository;
import com.globalsync.bonus.system.repository.IPerformanceRepository;
import com.globalsync.bonus.system.service.IBonusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BonusService implements IBonusService {

    private final IEmployeeRepository employeeRepository;
    private final IPerformanceRepository performanceRepository;
    private final IBonusRepository bonusRepository;

    public BonusService(IEmployeeRepository employeeRepository,
                        IPerformanceRepository performanceRepository,
                        IBonusRepository bonusRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceRepository = performanceRepository;
        this.bonusRepository = bonusRepository;
    }

    @Override
    @Transactional
    public BonusRecord calculateAndSaveBonus(PerformanceReview review) {

        if (review.getReviewYear() == null || review.getReviewYear() < 2000) {
            throw new IllegalArgumentException("Review year must be valid.");
        }
        if (review.getTaskCompletionRate() < 0 || review.getTaskCompletionRate() > 25) {
            throw new IllegalArgumentException("Task Completion Rate must be between 0 and 25.");
        }
        if (review.getAttendanceAndPunctuality() < 0 || review.getAttendanceAndPunctuality() > 15) {
            throw new IllegalArgumentException("Attendance & Punctuality must be between 0 and 15.");
        }
        if (review.getTeamCollaboration() < 0 || review.getTeamCollaboration() > 15) {
            throw new IllegalArgumentException("Team Collaboration must be between 0 and 15.");
        }
        if (review.getProblemSolvingSkill() < 0 || review.getProblemSolvingSkill() > 15) {
            throw new IllegalArgumentException("Problem Solving Skill must be between 0 and 15.");
        }
        if (review.getCommunicationSkill() < 0 || review.getCommunicationSkill() > 10) {
            throw new IllegalArgumentException("Communication Skill must be between 0 and 10.");
        }
        if (review.getLeadershipAndInitiative() < 0 || review.getLeadershipAndInitiative() > 10) {
            throw new IllegalArgumentException("Leadership & Initiative must be between 0 and 10.");
        }
        if (review.getClientSatisfaction() < 0 || review.getClientSatisfaction() > 10) {
            throw new IllegalArgumentException("Client Satisfaction must be between 0 and 10.");
        }

        Employee employee = employeeRepository.findById(review.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + review.getEmployeeId()));

        if (bonusRepository.existsByEmployeeIdAndReviewYear(review.getEmployeeId(), review.getReviewYear())) {
            throw new IllegalArgumentException("Duplicate review error: Performance bonus already processed for this year.");
        }

        int totalKpiScore = review.getTaskCompletionRate() +
                review.getAttendanceAndPunctuality() +
                review.getTeamCollaboration() +
                review.getProblemSolvingSkill() +
                review.getCommunicationSkill() +
                review.getLeadershipAndInitiative() +
                review.getClientSatisfaction();

        BonusCategory category;
        float bonusPercentage;

        if (totalKpiScore >= 90 && totalKpiScore <= 100) {
            category = BonusCategory.GOLD;
            bonusPercentage = 20.0f;
        } else if (totalKpiScore >= 75 && totalKpiScore <= 89) {
            category = BonusCategory.SILVER;
            bonusPercentage = 12.0f;
        } else if (totalKpiScore >= 60 && totalKpiScore <= 74) {
            category = BonusCategory.BRONZE;
            bonusPercentage = 5.0f;
        } else {
            category = BonusCategory.NONE;
            bonusPercentage = 0.0f;
        }

        float baseSalary = employee.getBaseSalary();
        float bonusAmount = (baseSalary * bonusPercentage) / 100.0f;
        float totalCompensation = baseSalary + bonusAmount;

        performanceRepository.save(review);

        BonusRecord bonusRecord = new BonusRecord(
                null,
                review.getEmployeeId(),
                review.getReviewYear(),
                totalKpiScore,
                category,
                bonusPercentage,
                bonusAmount,
                totalCompensation
        );

        bonusRepository.save(bonusRecord);

        return bonusRecord;
    }

    @Override
    public Optional<BonusRecord> getBonusRecordById(Integer id) {
        return bonusRepository.findById(id);
    }

    @Override
    public List<BonusRecord> getAllBonusRecords() {
        return bonusRepository.findAll();
    }

    @Override
    public List<BonusRecord> getAllBonusRecords(int page, int size) {
        return bonusRepository.findAll(page, size);
    }

    @Override
    public int getBonusRecordCount() {
        return bonusRepository.count();
    }

    @Override
    public int updateBonusRecord(BonusRecord record) {
        return bonusRepository.update(record);
    }

    @Override
    public int removeBonusRecord(int id) {
        return bonusRepository.delete(id);
    }
}