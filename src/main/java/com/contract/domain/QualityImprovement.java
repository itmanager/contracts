package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * اقدامات بهبود کیفیت
 * اقدامات اصلاحی و بهبود کیفیت پروژه
 */
@Entity
@Table(name = "quality_improvement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityImprovement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "deadline")
    private ZonedDateTime deadline;

    @Column(name = "status")
    private String status;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "effectiveness")
    private Double effectiveness;

    @Column(name = "cost_impact", precision = 21, scale = 2)
    private BigDecimal costImpact;

    @Column(name = "time_impact")
    private Integer timeImpact;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "before_score")
    private Double beforeScore;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "after_score")
    private Double afterScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "supervisor" }, allowSetters = true)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract", "contractPhase" }, allowSetters = true)
    private QualityAssessment qualityAssessment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public QualityImprovement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public QualityImprovement title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public QualityImprovement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedTo() {
        return this.assignedTo;
    }

    public QualityImprovement assignedTo(String assignedTo) {
        this.setAssignedTo(assignedTo);
        return this;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public ZonedDateTime getDeadline() {
        return this.deadline;
    }

    public QualityImprovement deadline(ZonedDateTime deadline) {
        this.setDeadline(deadline);
        return this;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return this.status;
    }

    public QualityImprovement status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getEffectiveness() {
        return this.effectiveness;
    }

    public QualityImprovement effectiveness(Double effectiveness) {
        this.setEffectiveness(effectiveness);
        return this;
    }

    public void setEffectiveness(Double effectiveness) {
        this.effectiveness = effectiveness;
    }

    public BigDecimal getCostImpact() {
        return this.costImpact;
    }

    public QualityImprovement costImpact(BigDecimal costImpact) {
        this.setCostImpact(costImpact);
        return this;
    }

    public void setCostImpact(BigDecimal costImpact) {
        this.costImpact = costImpact;
    }

    public Integer getTimeImpact() {
        return this.timeImpact;
    }

    public QualityImprovement timeImpact(Integer timeImpact) {
        this.setTimeImpact(timeImpact);
        return this;
    }

    public void setTimeImpact(Integer timeImpact) {
        this.timeImpact = timeImpact;
    }

    public Double getBeforeScore() {
        return this.beforeScore;
    }

    public QualityImprovement beforeScore(Double beforeScore) {
        this.setBeforeScore(beforeScore);
        return this;
    }

    public void setBeforeScore(Double beforeScore) {
        this.beforeScore = beforeScore;
    }

    public Double getAfterScore() {
        return this.afterScore;
    }

    public QualityImprovement afterScore(Double afterScore) {
        this.setAfterScore(afterScore);
        return this;
    }

    public void setAfterScore(Double afterScore) {
        this.afterScore = afterScore;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public QualityImprovement contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public QualityImprovement contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    public QualityAssessment getQualityAssessment() {
        return this.qualityAssessment;
    }

    public void setQualityAssessment(QualityAssessment qualityAssessment) {
        this.qualityAssessment = qualityAssessment;
    }

    public QualityImprovement qualityAssessment(QualityAssessment qualityAssessment) {
        this.setQualityAssessment(qualityAssessment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityImprovement)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityImprovement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityImprovement{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", assignedTo='" + getAssignedTo() + "'" +
            ", deadline='" + getDeadline() + "'" +
            ", status='" + getStatus() + "'" +
            ", effectiveness=" + getEffectiveness() +
            ", costImpact=" + getCostImpact() +
            ", timeImpact=" + getTimeImpact() +
            ", beforeScore=" + getBeforeScore() +
            ", afterScore=" + getAfterScore() +
            "}";
    }
}
