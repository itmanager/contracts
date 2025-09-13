package com.contract.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.OutsourcingContract} entity.
 */
//@Schema(description = "قراردادهای برون سپاری\nاطلاعات قراردادهای برون‌سپاری شده")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingContractDTO implements Serializable {

    private Long id;

    @NotNull
    private String companyName;

    @NotNull
    private String contractNumber;

    private String description;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    private BigDecimal totalValue;

    private String contactPerson;

    private String contactInfo;

    private String paymentTerms;

    private String deliverables;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDeliverables() {
        return deliverables;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingContractDTO)) {
            return false;
        }

        OutsourcingContractDTO outsourcingContractDTO = (OutsourcingContractDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, outsourcingContractDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingContractDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", contractNumber='" + getContractNumber() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", totalValue=" + getTotalValue() +
            ", contactPerson='" + getContactPerson() + "'" +
            ", contactInfo='" + getContactInfo() + "'" +
            ", paymentTerms='" + getPaymentTerms() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            "}";
    }
}
