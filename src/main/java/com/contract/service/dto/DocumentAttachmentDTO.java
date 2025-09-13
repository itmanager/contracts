package com.contract.service.dto;

import com.contract.domain.enumeration.AttachmentType;
import com.contract.domain.enumeration.DocumentStatus;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.DocumentAttachment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentAttachmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String filePath;

    private Long fileSize;

    @NotNull
    private ZonedDateTime uploadDate;

    private String uploader;

    @NotNull
    private AttachmentType attachmentType;

    @NotNull
    private DocumentStatus status;

    private String version;

    private String keywords;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public ZonedDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(ZonedDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public AttachmentType getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public DocumentStatus getStatus() {
        return status;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentAttachmentDTO)) {
            return false;
        }

        DocumentAttachmentDTO documentAttachmentDTO = (DocumentAttachmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, documentAttachmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentAttachmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", fileSize=" + getFileSize() +
            ", uploadDate='" + getUploadDate() + "'" +
            ", uploader='" + getUploader() + "'" +
            ", attachmentType='" + getAttachmentType() + "'" +
            ", status='" + getStatus() + "'" +
            ", version='" + getVersion() + "'" +
            ", keywords='" + getKeywords() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
