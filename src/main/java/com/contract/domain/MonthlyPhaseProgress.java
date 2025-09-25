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
public class MonthlyPhaseProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Integer month;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "reported_progress")
    private Double reportedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "verified_progress")
    private Double verifiedProgress;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "submission_date")
    private BigDecimal submissionDate;

    @Column(name = "approval_date")
    private BigDecimal approvalDate;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Column(name = "labor_hours")
    private Integer laborHours;

    @Column(name = "labor_cost", precision = 21, scale = 2)
    private BigDecimal laborCost;

    @Column(name = "equipment_cost", precision = 21, scale = 2)
    private BigDecimal equipmentCost;

    @Column(name = "outsourcing_cost", precision = 21, scale = 2)
    private BigDecimal outsourcingCost;

    @Column(name = "overhead_cost", precision = 21, scale = 2)
    private BigDecimal overheadCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MonthlyPhaseProgress id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return this.year;
    }

    public MonthlyPhaseProgress year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return this.month;
    }

    public MonthlyPhaseProgress month(Integer month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getReportedProgress() {
        return this.reportedProgress;
    }

    public MonthlyPhaseProgress reportedProgress(Double reportedProgress) {
        this.setReportedProgress(reportedProgress);
        return this;
    }

    public void setReportedProgress(Double reportedProgress) {
        this.reportedProgress = reportedProgress;
    }

    public Double getVerifiedProgress() {
        return this.verifiedProgress;
    }

    public MonthlyPhaseProgress verifiedProgress(Double verifiedProgress) {
        this.setVerifiedProgress(verifiedProgress);
        return this;
    }

    public void setVerifiedProgress(Double verifiedProgress) {
        this.verifiedProgress = verifiedProgress;
    }

    public String getNotes() {
        return this.notes;
    }

    public MonthlyPhaseProgress notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public BigDecimal getSubmissionDate() {
        return this.submissionDate;
    }

    public MonthlyPhaseProgress submissionDate(BigDecimal submissionDate) {
        this.setSubmissionDate(submissionDate);
        return this;
    }

    public void setSubmissionDate(BigDecimal submissionDate) {
        this.submissionDate = submissionDate;
    }

    public BigDecimal getApprovalDate() {
        return this.approvalDate;
    }

    public MonthlyPhaseProgress approvalDate(BigDecimal approvalDate) {
        this.setApprovalDate(approvalDate);
        return this;
    }

    public void setApprovalDate(BigDecimal approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public MonthlyPhaseProgress qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getLaborHours() {
        return this.laborHours;
    }

    public MonthlyPhaseProgress laborHours(Integer laborHours) {
        this.setLaborHours(laborHours);
        return this;
    }

    public void setLaborHours(Integer laborHours) {
        this.laborHours = laborHours;
    }

    public BigDecimal getLaborCost() {
        return this.laborCost;
    }

    public MonthlyPhaseProgress laborCost(BigDecimal laborCost) {
        this.setLaborCost(laborCost);
        return this;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getEquipmentCost() {
        return this.equipmentCost;
    }

    public MonthlyPhaseProgress equipmentCost(BigDecimal equipmentCost) {
        this.setEquipmentCost(equipmentCost);
        return this;
    }

    public void setEquipmentCost(BigDecimal equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public BigDecimal getOutsourcingCost() {
        return this.outsourcingCost;
    }

    public MonthlyPhaseProgress outsourcingCost(BigDecimal outsourcingCost) {
        this.setOutsourcingCost(outsourcingCost);
        return this;
    }

    public void setOutsourcingCost(BigDecimal outsourcingCost) {
        this.outsourcingCost = outsourcingCost;
    }

    public BigDecimal getOverheadCost() {
        return this.overheadCost;
    }

    public MonthlyPhaseProgress overheadCost(BigDecimal overheadCost) {
        this.setOverheadCost(overheadCost);
        return this;
    }

    public void setOverheadCost(BigDecimal overheadCost) {
        this.overheadCost = overheadCost;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public MonthlyPhaseProgress contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPhaseProgress)) {
            return false;
        }
        return getId() != null && getId().equals(((MonthlyPhaseProgress) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPhaseProgress{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", reportedProgress=" + getReportedProgress() +
            ", verifiedProgress=" + getVerifiedProgress() +
            ", notes='" + getNotes() + "'" +
            ", submissionDate='" + getSubmissionDate() + "'" +
            ", approvalDate='" + getApprovalDate() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", laborHours=" + getLaborHours() +
            ", laborCost=" + getLaborCost() +
            ", equipmentCost=" + getEquipmentCost() +
            ", outsourcingCost=" + getOutsourcingCost() +
            ", overheadCost=" + getOverheadCost() +
            "}";
    }
}
