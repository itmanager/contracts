package com.contract.domain;

import com.contract.domain.enumeration.AttachmentType;
import com.contract.domain.enumeration.DocumentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * پیوست‌ها و مستندات
 * مدیریت مدارک و مستندات پیوست شده به سیستم
 */
@Entity
@Table(name = "document_attachment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocumentAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "file_path", nullable = false)
    private String filePath;

    @Column(name = "file_size")
    private Long fileSize;

    @NotNull
    @Column(name = "upload_date", nullable = false)
    private ZonedDateTime uploadDate;

    @Column(name = "uploader")
    private String uploader;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "attachment_type", nullable = false)
    private AttachmentType attachmentType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status;

    @Column(name = "version")
    private String version;

    @Column(name = "keywords")
    private String keywords;

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

    public DocumentAttachment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public DocumentAttachment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public DocumentAttachment description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public DocumentAttachment filePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public DocumentAttachment fileSize(Long fileSize) {
        this.setFileSize(fileSize);
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public ZonedDateTime getUploadDate() {
        return this.uploadDate;
    }

    public DocumentAttachment uploadDate(ZonedDateTime uploadDate) {
        this.setUploadDate(uploadDate);
        return this;
    }

    public void setUploadDate(ZonedDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getUploader() {
        return this.uploader;
    }

    public DocumentAttachment uploader(String uploader) {
        this.setUploader(uploader);
        return this;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public AttachmentType getAttachmentType() {
        return this.attachmentType;
    }

    public DocumentAttachment attachmentType(AttachmentType attachmentType) {
        this.setAttachmentType(attachmentType);
        return this;
    }

    public void setAttachmentType(AttachmentType attachmentType) {
        this.attachmentType = attachmentType;
    }

    public DocumentStatus getStatus() {
        return this.status;
    }

    public DocumentAttachment status(DocumentStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public String getVersion() {
        return this.version;
    }

    public DocumentAttachment version(String version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public DocumentAttachment keywords(String keywords) {
        this.setKeywords(keywords);
        return this;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public DocumentAttachment qualityScore(Double qualityScore) {
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

    public DocumentAttachment contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public DocumentAttachment contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentAttachment)) {
            return false;
        }
        return getId() != null && getId().equals(((DocumentAttachment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocumentAttachment{" +
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
            "}";
    }
}
