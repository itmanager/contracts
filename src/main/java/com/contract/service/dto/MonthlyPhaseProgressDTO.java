package com.contract.service.dto;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;

import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;



public class MonthlyPhaseProgressDTO implements Serializable {

    private Long id;
    private Integer year;
    private Integer month;
    private Double programProgress;
    private Double reportedProgress;
    private Double verifiedProgress;
    private Double actualHours;
    private Double estimatedHours;
    private Double cost;
    private Double budget;
    private Double budgetAllocated;
    private String notes;
    private BigDecimal submissionDate;
    private BigDecimal approvalDate;
    private String contractName;
    private String contractPhaseName;
    private String ganttName;

    // References by ID only
    private ContractPhaseDTO contractPhase;

    private ContractDTO contract;

    // Constructors
    public MonthlyPhaseProgressDTO() {}

    public MonthlyPhaseProgressDTO(Long id) {
        this.id = id;
    }

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

    public Double getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Double estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Double getBudgetAllocated() {
        return budgetAllocated;
    }

    public void setBudgetAllocated(Double budgetAllocated) {
        this.budgetAllocated = budgetAllocated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(BigDecimal submissionDate) {
        this.submissionDate = submissionDate;
    }

    public BigDecimal getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(BigDecimal approvalDate) {
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

    public ContractPhaseDTO getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhaseDTO contractPhase) {
        this.contractPhase = contractPhase;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyPhaseProgressDTO that = (MonthlyPhaseProgressDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "MonthlyPhaseProgressDTO{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", programProgress=" + programProgress +
                ", reportedProgress=" + reportedProgress +
                ", verifiedProgress=" + verifiedProgress +
                ", actualHours=" + actualHours +
                ", estimatedHours=" + estimatedHours +
                ", cost=" + cost +
                ", budget=" + budget +
                ", budgetAllocated=" + budgetAllocated +
                '}';
    }
}