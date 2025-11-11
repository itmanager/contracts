package com.contract.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.GanttActivity} entity.
 */
//@Schema(description = "فعالیت گانت چارت\nریز فعالیت‌های قابل برنامه‌ریزی در قالب گانت چارت")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GanttActivityDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String code;


    private String description;

    @NotNull
    private BigDecimal startDate;

    @NotNull
    private BigDecimal endDate;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double reportedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double verifiedProgress;

    @Min(value = 1)
    @Max(value = 100)
    private Integer weight;

    private String dependencies;

    private Boolean isMilestone;

    private Boolean criticalPath;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private Integer estimatedHours;

    private Integer actualHours;

    private BigDecimal hourlyRate;

    private BigDecimal laborCost;

    private ContractPhaseDTO contractPhase;

    private ContractDTO contract;

    private GanttActivityDTO  milestone;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDependencies() {
        return dependencies;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public Boolean getIsMilestone() {
        return isMilestone;
    }

    public void setIsMilestone(Boolean isMilestone) {
        this.isMilestone = isMilestone;
    }

    public Boolean getCriticalPath() {
        return criticalPath;
    }

    public void setCriticalPath(Boolean criticalPath) {
        this.criticalPath = criticalPath;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Integer getActualHours() {
        return actualHours;
    }

    public void setActualHours(Integer actualHours) {
        this.actualHours = actualHours;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
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
        if (!(o instanceof GanttActivityDTO)) {
            return false;
        }

        GanttActivityDTO ganttActivityDTO = (GanttActivityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ganttActivityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GanttActivityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", progress=" + getReportedProgress() +
            ", weight=" + getWeight() +
            ", dependencies='" + getDependencies() + "'" +
            ", isMilestone='" + getIsMilestone() + "'" +
            ", criticalPath='" + getCriticalPath() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", estimatedHours=" + getEstimatedHours() +
            ", actualHours=" + getActualHours() +
            ", hourlyRate=" + getHourlyRate() +
            ", laborCost=" + getLaborCost() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public GanttActivityDTO getMilestone() {
        return milestone;
    }

    public void setMilestone(GanttActivityDTO milestone) {
        this.milestone = milestone;
    }
}
