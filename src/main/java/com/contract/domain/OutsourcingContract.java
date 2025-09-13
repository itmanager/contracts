package com.contract.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * قراردادهای برون سپاری
 * اطلاعات قراردادهای برون‌سپاری شده
 */
@Entity
@Table(name = "outsourcing_contract")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @NotNull
    @Column(name = "contract_number", nullable = false, unique = true)
    private String contractNumber;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @NotNull
    @Column(name = "total_value", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalValue;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "deliverables")
    private String deliverables;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OutsourcingContract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public OutsourcingContract companyName(String companyName) {
        this.setCompanyName(companyName);
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractNumber() {
        return this.contractNumber;
    }

    public OutsourcingContract contractNumber(String contractNumber) {
        this.setContractNumber(contractNumber);
        return this;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getDescription() {
        return this.description;
    }

    public OutsourcingContract description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public OutsourcingContract startDate(LocalDate startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public OutsourcingContract endDate(LocalDate endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalValue() {
        return this.totalValue;
    }

    public OutsourcingContract totalValue(BigDecimal totalValue) {
        this.setTotalValue(totalValue);
        return this;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getContactPerson() {
        return this.contactPerson;
    }

    public OutsourcingContract contactPerson(String contactPerson) {
        this.setContactPerson(contactPerson);
        return this;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public OutsourcingContract contactInfo(String contactInfo) {
        this.setContactInfo(contactInfo);
        return this;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getPaymentTerms() {
        return this.paymentTerms;
    }

    public OutsourcingContract paymentTerms(String paymentTerms) {
        this.setPaymentTerms(paymentTerms);
        return this;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getDeliverables() {
        return this.deliverables;
    }

    public OutsourcingContract deliverables(String deliverables) {
        this.setDeliverables(deliverables);
        return this;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingContract)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingContract) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingContract{" +
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
