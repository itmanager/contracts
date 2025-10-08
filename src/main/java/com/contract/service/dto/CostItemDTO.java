package com.contract.service.dto;

import com.contract.domain.enumeration.CostType;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.CostItem} entity.
 */
//@Schema(description = "آیتم هزینه\nریز هزینه‌های انجام شده در پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CostItemDTO implements Serializable {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private BigDecimal unitPrice;

    private BigDecimal totalAmount;

    @NotNull
    private BigDecimal costDate;

    @Lob
    private String notes;

    private Boolean approved;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    @NotNull
    private CostType costType;

    private CostCategoryDTO costCategory;

    private ContractPhaseDTO contractPhase;

    private ContractDTO contract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCostDate() {
        return costDate;
    }

    public void setCostDate(BigDecimal costDate) {
        this.costDate = costDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public CostCategoryDTO getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(CostCategoryDTO costCategory) {
        this.costCategory = costCategory;
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
        if (!(o instanceof CostItemDTO)) {
            return false;
        }

        CostItemDTO costItemDTO = (CostItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, costItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostItemDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", quantity=" + getQuantity() +
            ", unitPrice=" + getUnitPrice() +
            ", totalAmount=" + getTotalAmount() +
            ", costDate='" + getCostDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", approved='" + getApproved() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", costType='" + getCostType() + "'" +
            ", costCategory=" + getCostCategory() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }
}
