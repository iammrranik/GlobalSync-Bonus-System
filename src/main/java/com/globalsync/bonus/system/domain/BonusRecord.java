package com.globalsync.bonus.system.domain;

import com.globalsync.bonus.system.domain.enums.BonusCategory;
import org.springframework.data.annotation.Id;

public class BonusRecord {
    @Id
    private Integer id;
    private Integer employeeId;
    private Integer reviewYear;
    private Integer totalKpiScore;
    private BonusCategory category;
    private Float bonusPercentage;
    private Float bonusAmount;
    private Float totalCompensation;

    public BonusRecord(Integer id, Integer employeeId, Integer reviewYear,
                       Integer totalKpiScore, BonusCategory category, Float bonusPercentage,
                       Float bonusAmount, Float totalCompensation) {
        this.setId(id);
        this.setEmployeeId(employeeId);
        this.setReviewYear(reviewYear);
        this.setTotalKpiScore(totalKpiScore);
        this.setCategory(category);
        this.setBonusPercentage(bonusPercentage);
        this.setBonusAmount(bonusAmount);
        this.setTotalCompensation(totalCompensation);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getReviewYear() {
        return reviewYear;
    }

    public void setReviewYear(Integer reviewYear) {
        this.reviewYear = reviewYear;
    }

    public Integer getTotalKpiScore() {
        return totalKpiScore;
    }

    public void setTotalKpiScore(Integer totalKpiScore) {
        this.totalKpiScore = totalKpiScore;
    }

    public BonusCategory getCategory() {
        return category;
    }

    public void setCategory(BonusCategory category) {
        this.category = category;
    }

    public Float getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(Float bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public Float getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Float bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Float getTotalCompensation() {
        return totalCompensation;
    }

    public void setTotalCompensation(Float totalCompensation) {
        this.totalCompensation = totalCompensation;
    }

    @Override
    public String toString() {
        return "BonusRecord [id=" + getId() + ", employeeId=" + getEmployeeId() + ", reviewYear=" + getReviewYear()
                + ", totalKpiScore=" + getTotalKpiScore() + ", category=" + getCategory() + ", bonusPercentage="
                + getBonusPercentage() + ", bonusAmount=" + getBonusAmount() + ", totalCompensation="
                + getTotalCompensation() + "]";
    }



}
