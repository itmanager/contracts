package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * تخصیص بودجه
 * مدیریت تخصیص بودجه به قراردادها و فازها
 */
@Entity
@Table(name = "budget_allocation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BudgetAllocation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "allocated_budget", precision = 21, scale = 2, nullable = false)
    private BigDecimal allocatedBudget;

    @Column(name = "spent_budget", precision = 21, scale = 2)
    private BigDecimal spentBudget;

    @Column(name = "remaining_budget", precision = 21, scale = 2)
    private BigDecimal remainingBudget;

    @Column(name = "last_updated")
    private ZonedDateTime lastUpdated;

    @Column(name = "revision_number")
    private Integer revisionNumber;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

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

    public BudgetAllocation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAllocatedBudget() {
        return this.allocatedBudget;
    }

    public BudgetAllocation allocatedBudget(BigDecimal allocatedBudget) {
        this.setAllocatedBudget(allocatedBudget);
        return this;
    }

    public void setAllocatedBudget(BigDecimal allocatedBudget) {
        this.allocatedBudget = allocatedBudget;
    }

    public BigDecimal getSpentBudget() {
        return this.spentBudget;
    }

    public BudgetAllocation spentBudget(BigDecimal spentBudget) {
        this.setSpentBudget(spentBudget);
        return this;
    }

    public void setSpentBudget(BigDecimal spentBudget) {
        this.spentBudget = spentBudget;
    }

    public BigDecimal getRemainingBudget() {
        return this.remainingBudget;
    }

    public BudgetAllocation remainingBudget(BigDecimal remainingBudget) {
        this.setRemainingBudget(remainingBudget);
        return this;
    }

    public void setRemainingBudget(BigDecimal remainingBudget) {
        this.remainingBudget = remainingBudget;
    }

    public ZonedDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public BudgetAllocation lastUpdated(ZonedDateTime lastUpdated) {
        this.setLastUpdated(lastUpdated);
        return this;
    }

    public void setLastUpdated(ZonedDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getRevisionNumber() {
        return this.revisionNumber;
    }

    public BudgetAllocation revisionNumber(Integer revisionNumber) {
        this.setRevisionNumber(revisionNumber);
        return this;
    }

    public void setRevisionNumber(Integer revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public BudgetAllocation qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public BudgetAllocation contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public BudgetAllocation contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BudgetAllocation)) {
            return false;
        }
        return getId() != null && getId().equals(((BudgetAllocation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BudgetAllocation{" +
            "id=" + getId() +
            ", allocatedBudget=" + getAllocatedBudget() +
            ", spentBudget=" + getSpentBudget() +
            ", remainingBudget=" + getRemainingBudget() +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", revisionNumber=" + getRevisionNumber() +
            ", qualityScore=" + getQualityScore() +
            "}";
    }
}
