package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * فعالیت گانت چارت
 * ریز فعالیت‌های قابل برنامه‌ریزی در قالب گانت چارت
 */
@Entity
@Table(name = "gantt_activity")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GanttActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private BigDecimal startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private BigDecimal endDate;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "progress")
    private Double progress;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "dependencies")
    private String dependencies;

    @Column(name = "is_milestone")
    private Boolean isMilestone;

    @Column(name = "critical_path")
    private Boolean criticalPath;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Column(name = "estimated_hours")
    private Integer estimatedHours;

    @Column(name = "actual_hours")
    private Integer actualHours;

    @Column(name = "hourly_rate", precision = 21, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "labor_cost", precision = 21, scale = 2)
    private BigDecimal laborCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GanttActivity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public GanttActivity name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public GanttActivity description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartDate() {
        return this.startDate;
    }

    public GanttActivity startDate(BigDecimal startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEndDate() {
        return this.endDate;
    }

    public GanttActivity endDate(BigDecimal endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public Double getProgress() {
        return this.progress;
    }

    public GanttActivity progress(Double progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public GanttActivity weight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getDependencies() {
        return this.dependencies;
    }

    public GanttActivity dependencies(String dependencies) {
        this.setDependencies(dependencies);
        return this;
    }

    public void setDependencies(String dependencies) {
        this.dependencies = dependencies;
    }

    public Boolean getIsMilestone() {
        return this.isMilestone;
    }

    public GanttActivity isMilestone(Boolean isMilestone) {
        this.setIsMilestone(isMilestone);
        return this;
    }

    public void setIsMilestone(Boolean isMilestone) {
        this.isMilestone = isMilestone;
    }

    public Boolean getCriticalPath() {
        return this.criticalPath;
    }

    public GanttActivity criticalPath(Boolean criticalPath) {
        this.setCriticalPath(criticalPath);
        return this;
    }

    public void setCriticalPath(Boolean criticalPath) {
        this.criticalPath = criticalPath;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public GanttActivity qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getEstimatedHours() {
        return this.estimatedHours;
    }

    public GanttActivity estimatedHours(Integer estimatedHours) {
        this.setEstimatedHours(estimatedHours);
        return this;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Integer getActualHours() {
        return this.actualHours;
    }

    public GanttActivity actualHours(Integer actualHours) {
        this.setActualHours(actualHours);
        return this;
    }

    public void setActualHours(Integer actualHours) {
        this.actualHours = actualHours;
    }

    public BigDecimal getHourlyRate() {
        return this.hourlyRate;
    }

    public GanttActivity hourlyRate(BigDecimal hourlyRate) {
        this.setHourlyRate(hourlyRate);
        return this;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getLaborCost() {
        return this.laborCost;
    }

    public GanttActivity laborCost(BigDecimal laborCost) {
        this.setLaborCost(laborCost);
        return this;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public GanttActivity contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GanttActivity)) {
            return false;
        }
        return getId() != null && getId().equals(((GanttActivity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GanttActivity{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", progress=" + getProgress() +
            ", weight=" + getWeight() +
            ", dependencies='" + getDependencies() + "'" +
            ", isMilestone='" + getIsMilestone() + "'" +
            ", criticalPath='" + getCriticalPath() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", estimatedHours=" + getEstimatedHours() +
            ", actualHours=" + getActualHours() +
            ", hourlyRate=" + getHourlyRate() +
            ", laborCost=" + getLaborCost() +
            "}";
    }
}
