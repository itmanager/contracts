package com.contract.domain;

import com.contract.domain.enumeration.CostType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * آیتم هزینه
 * ریز هزینه‌های انجام شده در پروژه
 */
@Entity
@Table(name = "cost_item")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CostItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "unit_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "total_amount", precision = 21, scale = 2)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "cost_date", nullable = false)
    private BigDecimal costDate;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "approved")
    private Boolean approved;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "cost_type", nullable = false)
    private CostType costType;

    @ManyToOne(fetch = FetchType.LAZY)
    private CostCategory costCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private Contract contract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CostItem id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public CostItem description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public CostItem quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    public CostItem unitPrice(BigDecimal unitPrice) {
        this.setUnitPrice(unitPrice);
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalAmount() {
        return this.totalAmount;
    }

    public CostItem totalAmount(BigDecimal totalAmount) {
        this.setTotalAmount(totalAmount);
        return this;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getCostDate() {
        return this.costDate;
    }

    public CostItem costDate(BigDecimal costDate) {
        this.setCostDate(costDate);
        return this;
    }

    public void setCostDate(BigDecimal costDate) {
        this.costDate = costDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public CostItem notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getApproved() {
        return this.approved;
    }

    public CostItem approved(Boolean approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public CostItem qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public CostType getCostType() {
        return this.costType;
    }

    public CostItem costType(CostType costType) {
        this.setCostType(costType);
        return this;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    public CostCategory getCostCategory() {
        return this.costCategory;
    }

    public void setCostCategory(CostCategory costCategory) {
        this.costCategory = costCategory;
    }

    public CostItem costCategory(CostCategory costCategory) {
        this.setCostCategory(costCategory);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public CostItem contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostItem)) {
            return false;
        }
        return getId() != null && getId().equals(((CostItem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostItem{" +
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
            "}";
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
