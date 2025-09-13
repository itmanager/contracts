package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * شاخص‌های کلیدی عملکرد (KPI)
 * شاخص‌های اندازه‌گیری عملکرد پروژه
 */
@Entity
@Table(name = "quality_kpi")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityKPI implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "formula")
    private String formula;

    @Column(name = "target_value")
    private Double targetValue;

    @Column(name = "current_value")
    private Double currentValue;

    @Column(name = "unit")
    private String unit;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "trend")
    private String trend;

    @Min(value = 1)
    @Max(value = 10)
    @Column(name = "importance")
    private Integer importance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "supervisor" }, allowSetters = true)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QualityKPI id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public QualityKPI name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public QualityKPI description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormula() {
        return this.formula;
    }

    public QualityKPI formula(String formula) {
        this.setFormula(formula);
        return this;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Double getTargetValue() {
        return this.targetValue;
    }

    public QualityKPI targetValue(Double targetValue) {
        this.setTargetValue(targetValue);
        return this;
    }

    public void setTargetValue(Double targetValue) {
        this.targetValue = targetValue;
    }

    public Double getCurrentValue() {
        return this.currentValue;
    }

    public QualityKPI currentValue(Double currentValue) {
        this.setCurrentValue(currentValue);
        return this;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public String getUnit() {
        return this.unit;
    }

    public QualityKPI unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public QualityKPI frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTrend() {
        return this.trend;
    }

    public QualityKPI trend(String trend) {
        this.setTrend(trend);
        return this;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public Integer getImportance() {
        return this.importance;
    }

    public QualityKPI importance(Integer importance) {
        this.setImportance(importance);
        return this;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public QualityKPI contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public QualityKPI contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityKPI)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityKPI) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityKPI{" +
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
            "}";
    }
}
