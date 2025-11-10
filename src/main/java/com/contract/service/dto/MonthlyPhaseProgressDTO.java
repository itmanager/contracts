package com.contract.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.MonthlyPhaseProgress} entity.
 */
//@Schema(description = "پیشرفت ماهانه فاز\nگزارش پیشرفت ماهانه هر فاز از پروژه")
// DTO
public class MonthlyPhaseProgressDTO {

    private Long id;
    private Integer year;
    private Integer month;
    private Double programProgress;
    private Double reportedProgress;
    private Double verifiedProgress;
    private Double actualHours;
    private String notes;
    private Long submissionDate;
    private Long approvalDate;
    private String contractName;
    private String contractPhaseName;
    private String ganttName;
    private GanttActivityIdDTO milestone;
    private ContractIdDTO contract;
    private ContractPhaseIdDTO contractPhase;

    // Constructors
    public MonthlyPhaseProgressDTO() {}


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getProgramProgress() {
        return programProgress;
    }

    public void setProgramProgress(Double programProgress) {
        this.programProgress = programProgress;
    }

    public Double getReportedProgress() {
        return reportedProgress;
    }

    public void setReportedProgress(Double reportedProgress) {
        this.reportedProgress = reportedProgress;
    }

    public Double getVerifiedProgress() {
        return verifiedProgress;
    }

    public void setVerifiedProgress(Double verifiedProgress) {
        this.verifiedProgress = verifiedProgress;
    }

    public Double getActualHours() {
        return actualHours;
    }

    public void setActualHours(Double actualHours) {
        this.actualHours = actualHours;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Long submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Long getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Long approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractPhaseName() {
        return contractPhaseName;
    }

    public void setContractPhaseName(String contractPhaseName) {
        this.contractPhaseName = contractPhaseName;
    }

    public String getGanttName() {
        return ganttName;
    }

    public void setGanttName(String ganttName) {
        this.ganttName = ganttName;
    }

    public GanttActivityIdDTO getMilestone() {
        return milestone;
    }

    public void setMilestone(GanttActivityIdDTO milestone) {
        this.milestone = milestone;
    }

    public ContractIdDTO getContract() {
        return contract;
    }

    public void setContract(ContractIdDTO contract) {
        this.contract = contract;
    }

    public ContractPhaseIdDTO getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhaseIdDTO contractPhase) {
        this.contractPhase = contractPhase;
    }

    // Inner DTO classes for Pick types
    public static class GanttActivityIdDTO {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public static class ContractIdDTO {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    public static class ContractPhaseIdDTO {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}