package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * هزینه‌های سربار
 * ثبت هزینه‌های سربار پروژه
 */
@Entity
@Table(name = "overhead_cost")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OverheadCost implements Serializable {

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
    @Column(name = "cost_date", nullable = false)
    private BigDecimal costDate;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "category")
    private String category;

    @Column(name = "allocation_method")
    private String allocationMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "supervisor" }, allowSetters = true)
    private Contract allocatedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase allocatedToPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OverheadCost id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public OverheadCost name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public OverheadCost description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCostDate() {
        return this.costDate;
    }

    public OverheadCost costDate(BigDecimal costDate) {
        this.setCostDate(costDate);
        return this;
    }

    public void setCostDate(BigDecimal costDate) {
        this.costDate = costDate;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public OverheadCost amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return this.category;
    }

    public OverheadCost category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAllocationMethod() {
        return this.allocationMethod;
    }

    public OverheadCost allocationMethod(String allocationMethod) {
        this.setAllocationMethod(allocationMethod);
        return this;
    }

    public void setAllocationMethod(String allocationMethod) {
        this.allocationMethod = allocationMethod;
    }

    public Contract getAllocatedTo() {
        return this.allocatedTo;
    }

    public void setAllocatedTo(Contract contract) {
        this.allocatedTo = contract;
    }

    public OverheadCost allocatedTo(Contract contract) {
        this.setAllocatedTo(contract);
        return this;
    }

    public ContractPhase getAllocatedToPhase() {
        return this.allocatedToPhase;
    }

    public void setAllocatedToPhase(ContractPhase contractPhase) {
        this.allocatedToPhase = contractPhase;
    }

    public OverheadCost allocatedToPhase(ContractPhase contractPhase) {
        this.setAllocatedToPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OverheadCost)) {
            return false;
        }
        return getId() != null && getId().equals(((OverheadCost) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OverheadCost{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", costDate='" + getCostDate() + "'" +
            ", amount=" + getAmount() +
            ", category='" + getCategory() + "'" +
            ", allocationMethod='" + getAllocationMethod() + "'" +
            "}";
    }
}
