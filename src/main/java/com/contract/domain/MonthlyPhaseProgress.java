package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * پیشرفت ماهانه فاز
 * گزارش پیشرفت ماهانه هر فاز از پروژه
 */
@Entity
@Table(name = "monthly_phase_progress")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPhaseProgress {

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

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "submission_date")
    private Long submissionDate;

    @Column(name = "approval_date")
    private Long approvalDate;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "contract_phase_name")
    private String contractPhaseName;

    @Column(name = "gantt_name")
    private String ganttName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "milestone_id")
    private GanttActivity milestone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_phase_id")
    private ContractPhase contractPhase;

    // Constructors
    public MonthlyPhaseProgress() {}

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

    public GanttActivity getMilestone() {
        return milestone;
    }

    public void setMilestone(GanttActivity milestone) {
        this.milestone = milestone;
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

}
