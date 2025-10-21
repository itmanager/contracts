package com.contract.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.BudgetAllocation} entity.
 */
//@Schema(description = "تخصیص بودجه\nمدیریت تخصیص بودجه به قراردادها و فازها")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BudgetAllocationDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal allocatedBudget;

    private BigDecimal spentBudget;

    private BigDecimal remainingBudget;

    private BigDecimal lastUpdated;

    private Integer revisionNumber;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAllocatedBudget() {
        return allocatedBudget;
    }

    public void setAllocatedBudget(BigDecimal allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    public BigDecimal getSpentBudget() {
        return spentBudget;
    }

    public void setSpentBudget(BigDecimal spentBudget) {
        this.spentBudget = spentBudget;
    }

    public BigDecimal getRemainingBudget() {
        return remainingBudget;
    }

    public void setRemainingBudget(BigDecimal remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public BigDecimal getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(BigDecimal lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
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
        if (!(o instanceof BudgetAllocationDTO)) {
            return false;
        }

        BudgetAllocationDTO budgetAllocationDTO = (BudgetAllocationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, budgetAllocationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BudgetAllocationDTO{" +
            "id=" + getId() +
            ", allocatedBudget=" + getAllocatedBudget() +
            ", spentBudget=" + getSpentBudget() +
            ", remainingBudget=" + getRemainingBudget() +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", revisionNumber=" + getRevisionNumber() +
            ", qualityScore=" + getQualityScore() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
