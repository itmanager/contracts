package com.contract.domain;

import com.contract.domain.enumeration.QualityDimension;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * سنجش کیفیت
 * ارزیابی‌های دوره‌ای کیفیت پروژه
 */
@Entity
@Table(name = "quality_assessment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityAssessment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "assessment_date", nullable = false)
    private ZonedDateTime assessmentDate;

    @NotNull
    @Column(name = "assessed_by", nullable = false)
    private String assessedBy;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dimension", nullable = false)
    private QualityDimension dimension;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "score", nullable = false)
    private Double score;

    @Min(value = 1)
    @Max(value = 100)
    @Column(name = "weight")
    private Integer weight;

    @Lob
    @Column(name = "comments")
    private String comments;

    @Lob
    @Column(name = "recommendations")
    private String recommendations;

    @Column(name = "next_assessment_date")
    private ZonedDateTime nextAssessmentDate;

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

    public QualityAssessment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAssessmentDate() {
        return this.assessmentDate;
    }

    public QualityAssessment assessmentDate(ZonedDateTime assessmentDate) {
        this.setAssessmentDate(assessmentDate);
        return this;
    }

    public void setAssessmentDate(ZonedDateTime assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAssessedBy() {
        return this.assessedBy;
    }

    public QualityAssessment assessedBy(String assessedBy) {
        this.setAssessedBy(assessedBy);
        return this;
    }

    public void setAssessedBy(String assessedBy) {
        this.assessedBy = assessedBy;
    }

    public QualityDimension getDimension() {
        return this.dimension;
    }

    public QualityAssessment dimension(QualityDimension dimension) {
        this.setDimension(dimension);
        return this;
    }

    public void setDimension(QualityDimension dimension) {
        this.dimension = dimension;
    }

    public Double getScore() {
        return this.score;
    }

    public QualityAssessment score(Double score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public QualityAssessment weight(Integer weight) {
        this.setWeight(weight);
        return this;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getComments() {
        return this.comments;
    }

    public QualityAssessment comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecommendations() {
        return this.recommendations;
    }

    public QualityAssessment recommendations(String recommendations) {
        this.setRecommendations(recommendations);
        return this;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public ZonedDateTime getNextAssessmentDate() {
        return this.nextAssessmentDate;
    }

    public QualityAssessment nextAssessmentDate(ZonedDateTime nextAssessmentDate) {
        this.setNextAssessmentDate(nextAssessmentDate);
        return this;
    }

    public void setNextAssessmentDate(ZonedDateTime nextAssessmentDate) {
        this.nextAssessmentDate = nextAssessmentDate;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public QualityAssessment contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public QualityAssessment contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityAssessment)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityAssessment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityAssessment{" +
            "id=" + getId() +
            ", assessmentDate='" + getAssessmentDate() + "'" +
            ", assessedBy='" + getAssessedBy() + "'" +
            ", dimension='" + getDimension() + "'" +
            ", score=" + getScore() +
            ", weight=" + getWeight() +
            ", comments='" + getComments() + "'" +
            ", recommendations='" + getRecommendations() + "'" +
            ", nextAssessmentDate='" + getNextAssessmentDate() + "'" +
            "}";
    }
}
