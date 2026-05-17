package com.globalsync.bonus.system.domain;

import com.globalsync.bonus.system.domain.enums.EmployeeRole;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Employee {
    @Id
    private Integer id;
    private String name;
    private String designation;
    private Double baseSalary;
    private EmployeeRole role;
    private LocalDate lastPromotionDate;

    public Employee(Integer id, String name, String designation, Double baseSalary, EmployeeRole role, LocalDate lastPromotionDate) {
        this.setId(id);
        this.setName(name);
        this.setDesignation(designation);
        this.setBaseSalary(baseSalary);
        this.setRole(role);
        this.setLastPromotionDate(lastPromotionDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public EmployeeRole getRole() {
        return role;
    }

    public void setRole(EmployeeRole role) {
        this.role = role;
    }

    public LocalDate getLastPromotionDate() {
        return lastPromotionDate;
    }

    public void setLastPromotionDate(LocalDate lastPromotionDate) {
        this.lastPromotionDate = lastPromotionDate;
    }

    @Override
    public String toString() {
        return "Employee [id=" + getId() + ", name=" + getName() + ", designation=" + getDesignation()
                + ", baseSalary=" + getBaseSalary() + ", role=" + getRole() + ", lastPromotionDate="
                + getLastPromotionDate() + "]";
    }



}
