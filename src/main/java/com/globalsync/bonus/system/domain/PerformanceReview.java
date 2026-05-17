package com.globalsync.bonus.system.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

public class PerformanceReview {
    @Id
    private Integer id;

    @NotNull(message = "Employee ID is required")
    private Integer employeeId;

    @NotNull(message = "Review year is required")
    @Min(value = 2000, message = "Review year must be valid")
    @Max(value = 3000, message = "Review year must be valid")
    private Integer reviewYear;

    @Min(0) @Max(25)
    private Integer taskCompletionRate;

    @Min(0) @Max(15)
    private Integer attendanceAndPunctuality;

    @Min(0) @Max(15)
    private Integer teamCollaboration;

    @Min(0) @Max(15)
    private Integer problemSolvingSkill;

    @Min(0) @Max(10)
    private Integer communicationSkill;

    @Min(0) @Max(10)
    private Integer leadershipAndInitiative;

    @Min(0) @Max(10)
    private Integer clientSatisfaction;

    public PerformanceReview(Integer id, Integer employeeId, Integer reviewYear,
                             Integer taskCompletionRate, Integer attendanceAndPunctuality, Integer teamCollaboration,
                             Integer problemSolvingSkill, Integer communicationSkill, Integer leadershipAndInitiative,
                             Integer clientSatisfaction) {
        this.setId(id);
        this.setEmployeeId(employeeId);
        this.setReviewYear(reviewYear);
        this.setTaskCompletionRate(taskCompletionRate);
        this.setAttendanceAndPunctuality(attendanceAndPunctuality);
        this.setTeamCollaboration(teamCollaboration);
        this.setProblemSolvingSkill(problemSolvingSkill);
        this.setCommunicationSkill(communicationSkill);
        this.setLeadershipAndInitiative(leadershipAndInitiative);
        this.setClientSatisfaction(clientSatisfaction);
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

    public Integer getTaskCompletionRate() {
        return taskCompletionRate;
    }

    public void setTaskCompletionRate(Integer taskCompletionRate) {
        this.taskCompletionRate = taskCompletionRate;
    }

    public Integer getAttendanceAndPunctuality() {
        return attendanceAndPunctuality;
    }

    public void setAttendanceAndPunctuality(Integer attendanceAndPunctuality) {
        this.attendanceAndPunctuality = attendanceAndPunctuality;
    }

    public Integer getTeamCollaboration() {
        return teamCollaboration;
    }

    public void setTeamCollaboration(Integer teamCollaboration) {
        this.teamCollaboration = teamCollaboration;
    }

    public Integer getProblemSolvingSkill() {
        return problemSolvingSkill;
    }

    public void setProblemSolvingSkill(Integer problemSolvingSkill) {
        this.problemSolvingSkill = problemSolvingSkill;
    }

    public Integer getCommunicationSkill() {
        return communicationSkill;
    }

    public void setCommunicationSkill(Integer communicationSkill) {
        this.communicationSkill = communicationSkill;
    }

    public Integer getLeadershipAndInitiative() {
        return leadershipAndInitiative;
    }

    public void setLeadershipAndInitiative(Integer leadershipAndInitiative) {
        this.leadershipAndInitiative = leadershipAndInitiative;
    }

    public Integer getClientSatisfaction() {
        return clientSatisfaction;
    }

    public void setClientSatisfaction(Integer clientSatisfaction) {
        this.clientSatisfaction = clientSatisfaction;
    }

    @Override
    public String toString() {
        return "PerformanceReview [id=" + getId() + ", employeeId=" + getEmployeeId() + ", reviewYear=" + getReviewYear()
                + ", taskCompletionRate=" + getTaskCompletionRate() + ", attendanceAndPunctuality=" + getAttendanceAndPunctuality()
                + ", teamCollaboration=" + getTeamCollaboration() + ", problemSolvingSkill=" +  getProblemSolvingSkill()
                + ", communicationSkill=" + getCommunicationSkill() + ", leadershipAndInitiative="  + getLeadershipAndInitiative()
                + ", clientSatisfaction=" + getClientSatisfaction() + "]";
    }

}
