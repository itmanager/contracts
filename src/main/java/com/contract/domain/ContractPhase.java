package com.contract.domain;

import com.contract.domain.enumeration.PhaseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * فاز قرارداد
 * بخش‌بندی کاری قرارداد به فازهای قابل مدیریت
 */
@Entity
@Table(name = "contract_phase")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractPhase implements Serializable {

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

    @Column(name = "start_date")
    private BigDecimal startDate;

    @Column(name = "end_date")
    private BigDecimal endDate;

    @NotNull
    @Column(name = "budget", precision = 21, scale = 2, nullable = false)
    private BigDecimal budget;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "planned_progress")
    private Double plannedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "reported_progress")
    private Double reportedProgress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "verified_progress")
    private Double verifiedProgress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PhaseStatus status;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "weight")
    private Integer weight;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "priority")
    private Integer priority;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Column(name = "total_labor_hours")
    private Integer totalLaborHours;

    @Column(name = "labor_cost", precision = 21, scale = 2)
    private BigDecimal laborCost;

    @Column(name = "equipment_cost", precision = 21, scale = 2)
    private BigDecimal equipmentCost;

    @Column(name = "outsourcing_cost", precision = 21, scale = 2)
    private BigDecimal outsourcingCost;

    @Column(name = "overhead_cost", precision = 21, scale = 2)
    private BigDecimal overheadCost;



    @Column(name = "labor_Budget", precision = 21, scale = 2)
    private BigDecimal laborBudget;

    @Column(name = "equipment_Budget", precision = 21, scale = 2)
    private BigDecimal equipmentBudget;

    @Column(name = "outsourcing_Budget", precision = 21, scale = 2)
    private BigDecimal outsourcingBudget;

    @Column(name = "overhead_Budget", precision = 21, scale = 2)
    private BigDecimal overheadBudget;


    @Column(name = "cost_performance_index")
    private Double costPerformanceIndex;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "supervisor" }, allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractPhase id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ContractPhase name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public ContractPhase description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartDate() {
        return this.startDate;
    }

    public ContractPhase startDate(BigDecimal startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEndDate() {
        return this.endDate;
    }

    public ContractPhase endDate(BigDecimal endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBudget() {
        return this.budget;
    }

    public ContractPhase budget(BigDecimal budget) {
        this.setBudget(budget);
        return this;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public Double getPlannedProgress() {
        return this.plannedProgress;
    }

    public ContractPhase plannedProgress(Double plannedProgress) {
        this.setPlannedProgress(plannedProgress);
        return this;
    }

    public void setPlannedProgress(Double plannedProgress) {
        this.plannedProgress = plannedProgress;
    }

    public Double getReportedProgress() {
        return this.reportedProgress;
    }

    public ContractPhase reportedProgress(Double reportedProgress) {
        this.setReportedProgress(reportedProgress);
        return this;
    }

    public void setReportedProgress(Double reportedProgress) {
        this.reportedProgress = reportedProgress;
    }

    public Double getVerifiedProgress() {
        return this.verifiedProgress;
    }

    public ContractPhase verifiedProgress(Double verifiedProgress) {
        this.setVerifiedProgress(verifiedProgress);
        return this;
    }

    public void setVerifiedProgress(Double verifiedProgress) {
        this.verifiedProgress = verifiedProgress;
    }

    public PhaseStatus getStatus() {
        return this.status;
    }

    public ContractPhase status(PhaseStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(PhaseStatus status) {
        this.status = status;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public ContractPhase weight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return this.notes;
    }

    public ContractPhase notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public ContractPhase priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public ContractPhase qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Integer getTotalLaborHours() {
        return this.totalLaborHours;
    }

    public ContractPhase totalLaborHours(Integer totalLaborHours) {
        this.setTotalLaborHours(totalLaborHours);
        return this;
    }

    public void setTotalLaborHours(Integer totalLaborHours) {
        this.totalLaborHours = totalLaborHours;
    }

    public BigDecimal getLaborCost() {
        return this.laborCost;
    }

    public ContractPhase laborCost(BigDecimal laborCost) {
        this.setLaborCost(laborCost);
        return this;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getEquipmentCost() {
        return this.equipmentCost;
    }

    public ContractPhase equipmentCost(BigDecimal equipmentCost) {
        this.setEquipmentCost(equipmentCost);
        return this;
    }

    public void setEquipmentCost(BigDecimal equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public BigDecimal getOutsourcingCost() {
        return this.outsourcingCost;
    }

    public ContractPhase outsourcingCost(BigDecimal outsourcingCost) {
        this.setOutsourcingCost(outsourcingCost);
        return this;
    }

    public void setOutsourcingCost(BigDecimal outsourcingCost) {
        this.outsourcingCost = outsourcingCost;
    }

    public BigDecimal getOverheadCost() {
        return this.overheadCost;
    }

    public ContractPhase overheadCost(BigDecimal overheadCost) {
        this.setOverheadCost(overheadCost);
        return this;
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
        return this.costPerformanceIndex;
    }

    public ContractPhase costPerformanceIndex(Double costPerformanceIndex) {
        this.setCostPerformanceIndex(costPerformanceIndex);
        return this;
    }

    public void setCostPerformanceIndex(Double costPerformanceIndex) {
        this.costPerformanceIndex = costPerformanceIndex;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractPhase contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractPhase)) {
            return false;
        }
        return getId() != null && getId().equals(((ContractPhase) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractPhase{" +
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
            "}";
    }
}
