package com.contract.service.dto;

import com.contract.domain.enumeration.PaymentStatus;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.PaymentRequest} entity.
 */
//@Schema(description = "درخواست پرداخت\nدرخواست‌های پرداخت وجه از سوی پیمانکار")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentRequestDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate requestDate;

    private LocalDate dueDate;

    private String description;

    @NotNull
    private PaymentStatus status;

    private String invoiceNumber;

    private LocalDate paidDate;

    @Lob
    private String notes;

    private String paymentMethod;

    private String bankAccountDetails;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
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
        if (!(o instanceof PaymentRequestDTO)) {
            return false;
        }

        PaymentRequestDTO paymentRequestDTO = (PaymentRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentRequestDTO{" +
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
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
