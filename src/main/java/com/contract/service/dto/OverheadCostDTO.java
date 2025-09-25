package com.contract.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;
/**
 * A DTO for the {@link com.contract.domain.OverheadCost} entity.
 */
//@Schema(description = "هزینه‌های سربار\nثبت هزینه‌های سربار پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OverheadCostDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private BigDecimal costDate;

    @NotNull
    private BigDecimal amount;

    private String category;

    private String allocationMethod;

    private ContractDTO allocatedTo;

    private ContractPhaseDTO allocatedToPhase;

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

    public BigDecimal getCostDate() {
        return costDate;
    }

    public void setCostDate(BigDecimal costDate) {
        this.costDate = costDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAllocationMethod() {
        return allocationMethod;
    }

    public void setAllocationMethod(String allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    public ContractDTO getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(ContractDTO allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    public ContractPhaseDTO getAllocatedToPhase() {
        return allocatedToPhase;
    }

    public void setAllocatedToPhase(ContractPhaseDTO allocatedToPhase) {
        this.allocatedToPhase = allocatedToPhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OverheadCostDTO)) {
            return false;
        }

        OverheadCostDTO overheadCostDTO = (OverheadCostDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, overheadCostDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OverheadCostDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", costDate='" + getCostDate() + "'" +
            ", amount=" + getAmount() +
            ", category='" + getCategory() + "'" +
            ", allocationMethod='" + getAllocationMethod() + "'" +
            ", allocatedTo=" + getAllocatedTo() +
            ", allocatedToPhase=" + getAllocatedToPhase() +
            "}";
    }
}
