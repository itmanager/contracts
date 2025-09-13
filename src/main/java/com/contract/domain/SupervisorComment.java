package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * نظرات ناظر
 * نظرات و گزارش‌های ناظر پروژه
 */
@Entity
@Table(name = "supervisor_comment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SupervisorComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "comment_date", nullable = false)
    private ZonedDateTime commentDate;

    @Lob
    @Column(name = "comment_text", nullable = false)
    private String commentText;

    @Column(name = "priority")
    private String priority;

    @Column(name = "status")
    private String status;

    @Column(name = "related_to")
    private String relatedTo;

    @Lob
    @Column(name = "response_text")
    private String responseText;

    @Column(name = "response_date")
    private ZonedDateTime responseDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Supervisor supervisor;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SupervisorComment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCommentDate() {
        return this.commentDate;
    }

    public SupervisorComment commentDate(ZonedDateTime commentDate) {
        this.setCommentDate(commentDate);
        return this;
    }

    public void setCommentDate(ZonedDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentText() {
        return this.commentText;
    }

    public SupervisorComment commentText(String commentText) {
        this.setCommentText(commentText);
        return this;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getPriority() {
        return this.priority;
    }

    public SupervisorComment priority(String priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public SupervisorComment status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRelatedTo() {
        return this.relatedTo;
    }

    public SupervisorComment relatedTo(String relatedTo) {
        this.setRelatedTo(relatedTo);
        return this;
    }

    public void setRelatedTo(String relatedTo) {
        this.relatedTo = relatedTo;
    }

    public String getResponseText() {
        return this.responseText;
    }

    public SupervisorComment responseText(String responseText) {
        this.setResponseText(responseText);
        return this;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public ZonedDateTime getResponseDate() {
        return this.responseDate;
    }

    public SupervisorComment responseDate(ZonedDateTime responseDate) {
        this.setResponseDate(responseDate);
        return this;
    }

    public void setResponseDate(ZonedDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public SupervisorComment qualityScore(Double qualityScore) {
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

    public SupervisorComment contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public SupervisorComment contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    public Supervisor getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public SupervisorComment supervisor(Supervisor supervisor) {
        this.setSupervisor(supervisor);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupervisorComment)) {
            return false;
        }
        return getId() != null && getId().equals(((SupervisorComment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupervisorComment{" +
            "id=" + getId() +
            ", commentDate='" + getCommentDate() + "'" +
            ", commentText='" + getCommentText() + "'" +
            ", priority='" + getPriority() + "'" +
            ", status='" + getStatus() + "'" +
            ", relatedTo='" + getRelatedTo() + "'" +
            ", responseText='" + getResponseText() + "'" +
            ", responseDate='" + getResponseDate() + "'" +
            ", qualityScore=" + getQualityScore() +
            "}";
    }
}
