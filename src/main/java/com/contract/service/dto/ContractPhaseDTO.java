package com.contract.service.dto;

import com.contract.domain.enumeration.PhaseStatus;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.ContractPhase} entity.
 */
//@Schema(description = "فاز قرارداد\nبخش‌بندی کاری قرارداد به فازهای قابل مدیریت")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractPhaseDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    private BigDecimal startDate;

    private BigDecimal endDate;

    @NotNull
    private BigDecimal budget;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double plannedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double reportedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double verifiedProgress;

    @NotNull
    private PhaseStatus status;

    @Min(value = 1)
    @Max(value = 100)
    private Integer weight;

    @Lob
    private String notes;

    private Integer priority;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private Integer totalLaborHours;

    private BigDecimal laborCost;

    private BigDecimal equipmentCost;

    private BigDecimal outsourcingCost;

    private BigDecimal overheadCost;

    private BigDecimal laborBudget;

    private BigDecimal equipmentBudget;

    private BigDecimal outsourcingBudget;

    private BigDecimal overheadBudget;



    private Double costPerformanceIndex;

    private ContractDTO contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartDate() {
        return startDate;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEndDate() {
        return endDate;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Double getPlannedProgress() {
        return plannedProgress;
    }

    public void setPlannedProgress(Double plannedProgress) {
        this.plannedProgress = plannedProgress;
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

    public PhaseStatus getStatus() {
        return status;
    }

    public void setStatus(PhaseStatus status) {
        this.status = status;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getTotalLaborHours() {
        return totalLaborHours;
    }

    public void setTotalLaborHours(Integer totalLaborHours) {
        this.totalLaborHours = totalLaborHours;
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


    public BigDecimal getLaborBudget() {
        return laborBudget;
    }

    public void setLaborBudget(BigDecimal laborBudget) {
        this.laborBudget = laborBudget;
    }

    public BigDecimal getEquipmentBudget() {
        return equipmentBudget;
    }

    public void setEquipmentBudget(BigDecimal equipmentBudget) {
        this.equipmentBudget = equipmentBudget;
    }

    public BigDecimal getOutsourcingBudget() {
        return outsourcingBudget;
    }

    public void setOutsourcingBudget(BigDecimal outsourcingBudget) {
        this.outsourcingBudget = outsourcingBudget;
    }

    public BigDecimal getOverheadBudget() {
        return overheadBudget;
    }

    public void setOverheadBudget(BigDecimal overheadBudget) {
        this.overheadBudget = overheadBudget;
    }


    public Double getCostPerformanceIndex() {
        return costPerformanceIndex;
    }

    public void setCostPerformanceIndex(Double costPerformanceIndex) {
        this.costPerformanceIndex = costPerformanceIndex;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractPhaseDTO)) {
            return false;
        }

        ContractPhaseDTO contractPhaseDTO = (ContractPhaseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractPhaseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractPhaseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", budget=" + getBudget() +
            ", plannedProgress=" + getPlannedProgress() +
            ", reportedProgress=" + getReportedProgress() +
            ", verifiedProgress=" + getVerifiedProgress() +
            ", status='" + getStatus() + "'" +
            ", weight=" + getWeight() +
            ", notes='" + getNotes() + "'" +
            ", priority=" + getPriority() +
            ", qualityScore=" + getQualityScore() +
            ", totalLaborHours=" + getTotalLaborHours() +
            ", laborCost=" + getLaborCost() +
            ", equipmentCost=" + getEquipmentCost() +
            ", outsourcingCost=" + getOutsourcingCost() +
            ", overheadCost=" + getOverheadCost() +
            ", costPerformanceIndex=" + getCostPerformanceIndex() +
            ", contract=" + getContract() +
            "}";
    }
}
