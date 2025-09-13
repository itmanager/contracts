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
 * تغییرات و بازنگری‌ها
 * تاریخچه تغییرات و بازنگری‌های پروژه
 */
@Entity
@Table(name = "revision_history")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RevisionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "revision_date", nullable = false)
    private ZonedDateTime revisionDate;

    @NotNull
    @Column(name = "revised_by", nullable = false)
    private String revisedBy;

    @Lob
    @Column(name = "revision_description", nullable = false)
    private String revisionDescription;

    @Column(name = "revision_type")
    private String revisionType;

    @Column(name = "affected_entities")
    private String affectedEntities;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_impact")
    private Double qualityImpact;

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

    public RevisionHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRevisionDate() {
        return this.revisionDate;
    }

    public RevisionHistory revisionDate(ZonedDateTime revisionDate) {
        this.setRevisionDate(revisionDate);
        return this;
    }

    public void setRevisionDate(ZonedDateTime revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRevisedBy() {
        return this.revisedBy;
    }

    public RevisionHistory revisedBy(String revisedBy) {
        this.setRevisedBy(revisedBy);
        return this;
    }

    public void setRevisedBy(String revisedBy) {
        this.revisedBy = revisedBy;
    }

    public String getRevisionDescription() {
        return this.revisionDescription;
    }

    public RevisionHistory revisionDescription(String revisionDescription) {
        this.setRevisionDescription(revisionDescription);
        return this;
    }

    public void setRevisionDescription(String revisionDescription) {
        this.revisionDescription = revisionDescription;
    }

    public String getRevisionType() {
        return this.revisionType;
    }

    public RevisionHistory revisionType(String revisionType) {
        this.setRevisionType(revisionType);
        return this;
    }

    public void setRevisionType(String revisionType) {
        this.revisionType = revisionType;
    }

    public String getAffectedEntities() {
        return this.affectedEntities;
    }

    public RevisionHistory affectedEntities(String affectedEntities) {
        this.setAffectedEntities(affectedEntities);
        return this;
    }

    public void setAffectedEntities(String affectedEntities) {
        this.affectedEntities = affectedEntities;
    }

    public Double getQualityImpact() {
        return this.qualityImpact;
    }

    public RevisionHistory qualityImpact(Double qualityImpact) {
        this.setQualityImpact(qualityImpact);
        return this;
    }

    public void setQualityImpact(Double qualityImpact) {
        this.qualityImpact = qualityImpact;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public RevisionHistory contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public RevisionHistory contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RevisionHistory)) {
            return false;
        }
        return getId() != null && getId().equals(((RevisionHistory) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RevisionHistory{" +
            "id=" + getId() +
            ", revisionDate='" + getRevisionDate() + "'" +
            ", revisedBy='" + getRevisedBy() + "'" +
            ", revisionDescription='" + getRevisionDescription() + "'" +
            ", revisionType='" + getRevisionType() + "'" +
            ", affectedEntities='" + getAffectedEntities() + "'" +
            ", qualityImpact=" + getQualityImpact() +
            "}";
    }
}
