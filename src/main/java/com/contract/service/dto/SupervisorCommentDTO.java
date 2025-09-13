package com.contract.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.SupervisorComment} entity.
 */
//@Schema(description = "نظرات ناظر\nنظرات و گزارش‌های ناظر پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SupervisorCommentDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime commentDate;

    @Lob
    private String commentText;

    private String priority;

    private String status;

    private String relatedTo;

    @Lob
    private String responseText;

    private ZonedDateTime responseDate;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    private SupervisorDTO supervisor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(ZonedDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public ZonedDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(ZonedDateTime responseDate) {
        this.responseDate = responseDate;
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

    public SupervisorDTO getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(SupervisorDTO supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupervisorCommentDTO)) {
            return false;
        }

        SupervisorCommentDTO supervisorCommentDTO = (SupervisorCommentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, supervisorCommentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupervisorCommentDTO{" +
            "id=" + getId() +
            ", commentDate='" + getCommentDate() + "'" +
            ", commentText='" + getCommentText() + "'" +
            ", priority='" + getPriority() + "'" +
            ", status='" + getStatus() + "'" +
            ", relatedTo='" + getRelatedTo() + "'" +
            ", responseText='" + getResponseText() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            ", supervisor=" + getSupervisor() +
            "}";
    }
}
