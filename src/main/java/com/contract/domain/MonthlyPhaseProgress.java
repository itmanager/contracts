package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "monthly_phase_progress")
public class MonthlyPhaseProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "program_progress")
    private Double programProgress;

    @Column(name = "reported_progress")
    private Double reportedProgress;

    @Column(name = "verified_progress")
    private Double verifiedProgress;

    @Column(name = "actual_hours")
    private Double actualHours;

    @Column(name = "estimated_hours")
    private Double estimatedHours;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "budget")
    private Double budget;

    @Column(name = "budget_allocated")
    private Double budgetAllocated;

    @Column(name = "notes", length = 1000)
    private String notes;

    @Column(name = "submission_date")
    private BigDecimal submissionDate;

    @Column(name = "approval_date")
    private BigDecimal approvalDate;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_phase_name")
    private String contractPhaseName;

    @Column(name = "gantt_name")
    private String ganttName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract-phase"}, allowSetters = true)
    private ContractPhase contractPhase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contracts"}, allowSetters = true)
    private Contract contract;

    // Constructors
    public MonthlyPhaseProgress() {}

    public MonthlyPhaseProgress(Long id) {
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractPhase getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MonthlyPhaseProgress)) return false;
        return id != null && id.equals(((MonthlyPhaseProgress) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // toString
    @Override
    public String toString() {
        return "MonthlyPhaseProgress{" +
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