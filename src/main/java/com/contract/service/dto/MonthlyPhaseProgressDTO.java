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
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPhaseProgressDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double reportedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double verifiedProgress;

    @Lob
    private String notes;

    private BigDecimal submissionDate;

    private BigDecimal approvalDate;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private Integer laborHours;

    private BigDecimal laborCost;

    private BigDecimal equipmentCost;

    private BigDecimal outsourcingCost;

    private BigDecimal overheadCost;

    private ContractPhaseDTO contractPhase;

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

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getLaborHours() {
        return laborHours;
    }

    public void setLaborHours(Integer laborHours) {
        this.laborHours = laborHours;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getEquipmentCost() {
        return equipmentCost;
    }

    public void setEquipmentCost(BigDecimal equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public BigDecimal getOutsourcingCost() {
        return outsourcingCost;
    }

    public void setOutsourcingCost(BigDecimal outsourcingCost) {
        this.outsourcingCost = outsourcingCost;
    }

    public BigDecimal getOverheadCost() {
        return overheadCost;
    }

    public void setOverheadCost(BigDecimal overheadCost) {
        this.overheadCost = overheadCost;
    }

    public ContractPhaseDTO getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhaseDTO contractPhase) {
        this.contractPhase = contractPhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPhaseProgressDTO)) {
            return false;
        }

        MonthlyPhaseProgressDTO monthlyPhaseProgressDTO = (MonthlyPhaseProgressDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, monthlyPhaseProgressDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPhaseProgressDTO{" +
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
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
