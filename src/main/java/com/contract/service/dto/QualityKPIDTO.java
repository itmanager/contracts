package com.contract.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.QualityKPI} entity.
 */
//@Schema(description = "شاخص‌های کلیدی عملکرد (KPI)\nشاخص‌های اندازه‌گیری عملکرد پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityKPIDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String formula;

    private Double targetValue;

    private Double currentValue;

    private String unit;

    private String frequency;

    private String trend;

    @Min(value = 1)
    @Max(value = 10)
    private Integer importance;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
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
        if (!(o instanceof QualityKPIDTO)) {
            return false;
        }

        QualityKPIDTO qualityKPIDTO = (QualityKPIDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, qualityKPIDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityKPIDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", formula='" + getFormula() + "'" +
            ", targetValue=" + getTargetValue() +
            ", currentValue=" + getCurrentValue() +
            ", unit='" + getUnit() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", trend='" + getTrend() + "'" +
            ", importance=" + getImportance() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
