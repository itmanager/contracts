package com.contract.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.QualityImprovement} entity.
 */
//@Schema(description = "اقدامات بهبود کیفیت\nاقدامات اصلاحی و بهبود کیفیت پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityImprovementDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @Lob
    private String description;

    private String assignedTo;

    private ZonedDateTime deadline;

    private String status;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double effectiveness;

    private BigDecimal costImpact;

    private Integer timeImpact;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double beforeScore;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double afterScore;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    private QualityAssessmentDTO qualityAssessment;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getEffectiveness() {
        return effectiveness;
    }

    public void setEffectiveness(Double effectiveness) {
        this.effectiveness = effectiveness;
    }

    public BigDecimal getCostImpact() {
        return costImpact;
    }

    public void setCostImpact(BigDecimal costImpact) {
        this.costImpact = costImpact;
    }

    public Integer getTimeImpact() {
        return timeImpact;
    }

    public void setTimeImpact(Integer timeImpact) {
        this.timeImpact = timeImpact;
    }

    public Double getBeforeScore() {
        return beforeScore;
    }

    public void setBeforeScore(Double beforeScore) {
        this.beforeScore = beforeScore;
    }

    public Double getAfterScore() {
        return afterScore;
    }

    public void setAfterScore(Double afterScore) {
        this.afterScore = afterScore;
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

    public QualityAssessmentDTO getQualityAssessment() {
        return qualityAssessment;
    }

    public void setQualityAssessment(QualityAssessmentDTO qualityAssessment) {
        this.qualityAssessment = qualityAssessment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityImprovementDTO)) {
            return false;
        }

        QualityImprovementDTO qualityImprovementDTO = (QualityImprovementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, qualityImprovementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityImprovementDTO{" +
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
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            ", qualityAssessment=" + getQualityAssessment() +
            "}";
    }
}
