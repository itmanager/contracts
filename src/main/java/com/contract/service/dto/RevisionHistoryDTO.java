package com.contract.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.RevisionHistory} entity.
 */
//@Schema(description = "تغییرات و بازنگری‌ها\nتاریخچه تغییرات و بازنگری‌های پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RevisionHistoryDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime revisionDate;

    @NotNull
    private String revisedBy;

    @Lob
    private String revisionDescription;

    private String revisionType;

    private String affectedEntities;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityImpact;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(ZonedDateTime revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getRevisedBy() {
        return revisedBy;
    }

    public void setRevisedBy(String revisedBy) {
        this.revisedBy = revisedBy;
    }

    public String getRevisionDescription() {
        return revisionDescription;
    }

    public void setRevisionDescription(String revisionDescription) {
        this.revisionDescription = revisionDescription;
    }

    public String getRevisionType() {
        return revisionType;
    }

    public void setRevisionType(String revisionType) {
        this.revisionType = revisionType;
    }

    public String getAffectedEntities() {
        return affectedEntities;
    }

    public void setAffectedEntities(String affectedEntities) {
        this.affectedEntities = affectedEntities;
    }

    public Double getQualityImpact() {
        return qualityImpact;
    }

    public void setQualityImpact(Double qualityImpact) {
        this.qualityImpact = qualityImpact;
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
        if (!(o instanceof RevisionHistoryDTO)) {
            return false;
        }

        RevisionHistoryDTO revisionHistoryDTO = (RevisionHistoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, revisionHistoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RevisionHistoryDTO{" +
            "id=" + getId() +
            ", revisionDate='" + getRevisionDate() + "'" +
            ", revisedBy='" + getRevisedBy() + "'" +
            ", revisionDescription='" + getRevisionDescription() + "'" +
            ", revisionType='" + getRevisionType() + "'" +
            ", affectedEntities='" + getAffectedEntities() + "'" +
            ", qualityImpact=" + getQualityImpact() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
