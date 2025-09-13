package com.contract.service.dto;

import com.contract.domain.enumeration.QualityDimension;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain} entity.
 */
//@Schema(description = "سنجش کیفیت\nارزیابی‌های دوره‌ای کیفیت پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityAssessmentDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime assessmentDate;

    @NotNull
    private String assessedBy;

    @NotNull
    private QualityDimension dimension;

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double score;

    @Min(value = 1)
    @Max(value = 100)
    private Integer weight;

    @Lob
    private String comments;

    @Lob
    private String recommendations;

    private ZonedDateTime nextAssessmentDate;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(ZonedDateTime assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAssessedBy() {
        return assessedBy;
    }

    public void setAssessedBy(String assessedBy) {
        this.assessedBy = assessedBy;
    }

    public QualityDimension getDimension() {
        return dimension;
    }

    public void setDimension(QualityDimension dimension) {
        this.dimension = dimension;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public ZonedDateTime getNextAssessmentDate() {
        return nextAssessmentDate;
    }

    public void setNextAssessmentDate(ZonedDateTime nextAssessmentDate) {
        this.nextAssessmentDate = nextAssessmentDate;
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
        if (!(o instanceof QualityAssessmentDTO)) {
            return false;
        }

        QualityAssessmentDTO qualityAssessmentDTO = (QualityAssessmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, qualityAssessmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityAssessmentDTO{" +
            "id=" + getId() +
            ", assessmentDate='" + getAssessmentDate() + "'" +
            ", assessedBy='" + getAssessedBy() + "'" +
            ", dimension='" + getDimension() + "'" +
            ", score=" + getScore() +
            ", weight=" + getWeight() +
            ", comments='" + getComments() + "'" +
            ", recommendations='" + getRecommendations() + "'" +
            ", nextAssessmentDate='" + getNextAssessmentDate() + "'" +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
