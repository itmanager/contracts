package com.contract.domain;

import com.contract.domain.enumeration.ContractStatus;
import com.contract.domain.enumeration.QualityStatus;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * قرارداد
 * اصلی‌ترین موجودیت سیستم مدیریت قراردادها
 */
@Entity
@Table(name = "contract")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private BigDecimal startDate;

    @Column(name = "end_date")
    private BigDecimal endDate;

    @NotNull
    @Column(name = "total_budget", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalBudget;

    @Column(name = "current_budget", precision = 21, scale = 2)
    private BigDecimal currentBudget;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ContractStatus status;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "physical_address")
    private String physicalAddress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Enumerated(EnumType.STRING)
    @Column(name = "quality_status")
    private QualityStatus qualityStatus;

    @Column(name = "last_quality_assessment")
    private BigDecimal lastQualityAssessment;

    @Column(name = "total_labor_cost", precision = 21, scale = 2)
    private BigDecimal totalLaborCost;

    @Column(name = "total_equipment_cost", precision = 21, scale = 2)
    private BigDecimal totalEquipmentCost;

    @Column(name = "total_outsourcing_cost", precision = 21, scale = 2)
    private BigDecimal totalOutsourcingCost;

    @Column(name = "total_overhead_cost", precision = 21, scale = 2)
    private BigDecimal totalOverheadCost;

    @Column(name = "total_actual_cost", precision = 21, scale = 2)
    private BigDecimal totalActualCost;

    @Column(name = "cost_variance", precision = 21, scale = 2)
    private BigDecimal costVariance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Supervisor supervisor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Contract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Contract title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public Contract contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public Contract description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getStartDate() {
        return this.startDate;
    }

    public Contract startDate(BigDecimal startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEndDate() {
        return this.endDate;
    }

    public Contract endDate(BigDecimal endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalBudget() {
        return this.totalBudget;
    }

    public Contract totalBudget(BigDecimal totalBudget) {
        this.setTotalBudget(totalBudget);
        return this;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getCurrentBudget() {
        return this.currentBudget;
    }

    public Contract currentBudget(BigDecimal currentBudget) {
        this.setCurrentBudget(currentBudget);
        return this;
    }

    public void setCurrentBudget(BigDecimal currentBudget) {
        this.currentBudget = currentBudget;
    }

    public ContractStatus getStatus() {
        return this.status;
    }

    public Contract status(ContractStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return this.notes;
    }

    public Contract notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhysicalAddress() {
        return this.physicalAddress;
    }

    public Contract physicalAddress(String physicalAddress) {
        this.setPhysicalAddress(physicalAddress);
        return this;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public Contract qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public QualityStatus getQualityStatus() {
        return this.qualityStatus;
    }

    public Contract qualityStatus(QualityStatus qualityStatus) {
        this.setQualityStatus(qualityStatus);
        return this;
    }

    public void setQualityStatus(QualityStatus qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public BigDecimal getLastQualityAssessment() {
        return this.lastQualityAssessment;
    }

    public Contract lastQualityAssessment(BigDecimal lastQualityAssessment) {
        this.setLastQualityAssessment(lastQualityAssessment);
        return this;
    }

    public void setLastQualityAssessment(BigDecimal lastQualityAssessment) {
        this.lastQualityAssessment = lastQualityAssessment;
    }

    public BigDecimal getTotalLaborCost() {
        return this.totalLaborCost;
    }

    public Contract totalLaborCost(BigDecimal totalLaborCost) {
        this.setTotalLaborCost(totalLaborCost);
        return this;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalEquipmentCost() {
        return this.totalEquipmentCost;
    }

    public Contract totalEquipmentCost(BigDecimal totalEquipmentCost) {
        this.setTotalEquipmentCost(totalEquipmentCost);
        return this;
    }

    public void setTotalEquipmentCost(BigDecimal totalEquipmentCost) {
        this.totalEquipmentCost = totalEquipmentCost;
    }

    public BigDecimal getTotalOutsourcingCost() {
        return this.totalOutsourcingCost;
    }

    public Contract totalOutsourcingCost(BigDecimal totalOutsourcingCost) {
        this.setTotalOutsourcingCost(totalOutsourcingCost);
        return this;
    }

    public void setTotalOutsourcingCost(BigDecimal totalOutsourcingCost) {
        this.totalOutsourcingCost = totalOutsourcingCost;
    }

    public BigDecimal getTotalOverheadCost() {
        return this.totalOverheadCost;
    }

    public Contract totalOverheadCost(BigDecimal totalOverheadCost) {
        this.setTotalOverheadCost(totalOverheadCost);
        return this;
    }

    public void setTotalOverheadCost(BigDecimal totalOverheadCost) {
        this.totalOverheadCost = totalOverheadCost;
    }

    public BigDecimal getTotalActualCost() {
        return this.totalActualCost;
    }

    public Contract totalActualCost(BigDecimal totalActualCost) {
        this.setTotalActualCost(totalActualCost);
        return this;
    }

    public void setTotalActualCost(BigDecimal totalActualCost) {
        this.totalActualCost = totalActualCost;
    }

    public BigDecimal getCostVariance() {
        return this.costVariance;
    }

    public Contract costVariance(BigDecimal costVariance) {
        this.setCostVariance(costVariance);
        return this;
    }

    public void setCostVariance(BigDecimal costVariance) {
        this.costVariance = costVariance;
    }

    public Supervisor getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Contract supervisor(Supervisor supervisor) {
        this.setSupervisor(supervisor);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return getId() != null && getId().equals(((Contract) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", totalBudget=" + getTotalBudget() +
            ", currentBudget=" + getCurrentBudget() +
            ", status='" + getStatus() + "'" +
            ", notes='" + getNotes() + "'" +
            ", physicalAddress='" + getPhysicalAddress() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", qualityStatus='" + getQualityStatus() + "'" +
            ", lastQualityAssessment='" + getLastQualityAssessment() + "'" +
            ", totalLaborCost=" + getTotalLaborCost() +
            ", totalEquipmentCost=" + getTotalEquipmentCost() +
            ", totalOutsourcingCost=" + getTotalOutsourcingCost() +
            ", totalOverheadCost=" + getTotalOverheadCost() +
            ", totalActualCost=" + getTotalActualCost() +
            ", costVariance=" + getCostVariance() +
            "}";
    }
}
