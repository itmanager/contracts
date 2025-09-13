package com.contract.domain;

import com.contract.domain.enumeration.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * درخواست پرداخت
 * درخواست‌های پرداخت وجه از سوی پیمانکار
 */
@Entity
@Table(name = "payment_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "invoice_number")
    private String invoiceNumber;

    @Column(name = "paid_date")
    private LocalDate paidDate;

    @Lob
    @Column(name = "notes")
    private String notes;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "bank_account_details")
    private String bankAccountDetails;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PaymentRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public PaymentRequest amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getRequestDate() {
        return this.requestDate;
    }

    public PaymentRequest requestDate(LocalDate requestDate) {
        this.setRequestDate(requestDate);
        return this;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public PaymentRequest dueDate(LocalDate dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return this.description;
    }

    public PaymentRequest description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentStatus getStatus() {
        return this.status;
    }

    public PaymentRequest status(PaymentStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public PaymentRequest invoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getPaidDate() {
        return this.paidDate;
    }

    public PaymentRequest paidDate(LocalDate paidDate) {
        this.setPaidDate(paidDate);
        return this;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public PaymentRequest notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public PaymentRequest paymentMethod(String paymentMethod) {
        this.setPaymentMethod(paymentMethod);
        return this;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankAccountDetails() {
        return this.bankAccountDetails;
    }

    public PaymentRequest bankAccountDetails(String bankAccountDetails) {
        this.setBankAccountDetails(bankAccountDetails);
        return this;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public PaymentRequest qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public PaymentRequest contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentRequest)) {
            return false;
        }
        return getId() != null && getId().equals(((PaymentRequest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentRequest{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", requestDate='" + getRequestDate() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", invoiceNumber='" + getInvoiceNumber() + "'" +
            ", paidDate='" + getPaidDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", paymentMethod='" + getPaymentMethod() + "'" +
            ", bankAccountDetails='" + getBankAccountDetails() + "'" +
            ", qualityScore=" + getQualityScore() +
            "}";
    }
}
