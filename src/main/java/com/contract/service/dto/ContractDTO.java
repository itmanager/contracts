package com.contract.service.dto;

import com.contract.domain.enumeration.ContractStatus;
import com.contract.domain.enumeration.QualityStatus;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.Contract} entity.
 */
//@Schema(description = "قرارداد\nاصلی‌ترین موجودیت سیستم مدیریت قراردادها")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String contractNumber;

    private String description;

    @NotNull
    private BigDecimal startDate;

    private BigDecimal endDate;

    @NotNull
    private BigDecimal totalBudget;

    private BigDecimal currentBudget;

    @NotNull
    private ContractStatus status;

    @Lob
    private String notes;

    private String physicalAddress;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private QualityStatus qualityStatus;

    private BigDecimal lastQualityAssessment;

    private BigDecimal totalLaborCost;

    private BigDecimal totalEquipmentCost;

    private BigDecimal totalOutsourcingCost;

    private BigDecimal totalOverheadCost;

    private BigDecimal totalActualCost;

    private BigDecimal costVariance;

    private SupervisorDTO supervisor;
    private ThirdPartyDTO employer;
    private ThirdPartyDTO contractor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(BigDecimal currentBudget) {
        this.currentBudget = currentBudget;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public QualityStatus getQualityStatus() {
        return qualityStatus;
    }

    public void setQualityStatus(QualityStatus qualityStatus) {
        this.qualityStatus = qualityStatus;
    }

    public BigDecimal getLastQualityAssessment() {
        return lastQualityAssessment;
    }

    public void setLastQualityAssessment(BigDecimal lastQualityAssessment) {
        this.lastQualityAssessment = lastQualityAssessment;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalEquipmentCost() {
        return totalEquipmentCost;
    }

    public void setTotalEquipmentCost(BigDecimal totalEquipmentCost) {
        this.totalEquipmentCost = totalEquipmentCost;
    }

    public BigDecimal getTotalOutsourcingCost() {
        return totalOutsourcingCost;
    }

    public void setTotalOutsourcingCost(BigDecimal totalOutsourcingCost) {
        this.totalOutsourcingCost = totalOutsourcingCost;
    }

    public BigDecimal getTotalOverheadCost() {
        return totalOverheadCost;
    }

    public void setTotalOverheadCost(BigDecimal totalOverheadCost) {
        this.totalOverheadCost = totalOverheadCost;
    }

    public BigDecimal getTotalActualCost() {
        return totalActualCost;
    }

    public void setTotalActualCost(BigDecimal totalActualCost) {
        this.totalActualCost = totalActualCost;
    }

    public BigDecimal getCostVariance() {
        return costVariance;
    }

    public void setCostVariance(BigDecimal costVariance) {
        this.costVariance = costVariance;
    }

    public SupervisorDTO getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SupervisorDTO supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractDTO)) {
            return false;
        }

        ContractDTO contractDTO = (ContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contractDTO.id);
    }
    public ThirdPartyDTO getEmployer() {
        return employer;
    }

    public void setEmployer(ThirdPartyDTO employer) {
        this.employer = employer;
    }

    public ThirdPartyDTO getContractor() {
        return contractor;
    }

    public void setContractor(ThirdPartyDTO contractor) {
        this.contractor = contractor;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractDTO{" +
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
            ", supervisor=" + getSupervisor() +
            "}";
    }


}
