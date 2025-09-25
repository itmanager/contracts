package com.contract.domain;

import com.contract.domain.enumeration.PaymentStatus;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PaymentRequest.class)
public abstract class PaymentRequest_ {

	public static volatile SingularAttribute<PaymentRequest, BigDecimal> amount;
	public static volatile SingularAttribute<PaymentRequest, String> notes;
	public static volatile SingularAttribute<PaymentRequest, BigDecimal> dueDate;
	public static volatile SingularAttribute<PaymentRequest, String> description;
	public static volatile SingularAttribute<PaymentRequest, String> bankAccountDetails;
	public static volatile SingularAttribute<PaymentRequest, BigDecimal> paidDate;
	public static volatile SingularAttribute<PaymentRequest, ContractPhase> contractPhase;
	public static volatile SingularAttribute<PaymentRequest, BigDecimal> requestDate;
	public static volatile SingularAttribute<PaymentRequest, String> invoiceNumber;
	public static volatile SingularAttribute<PaymentRequest, Double> qualityScore;
	public static volatile SingularAttribute<PaymentRequest, String> paymentMethod;
	public static volatile SingularAttribute<PaymentRequest, Long> id;
	public static volatile SingularAttribute<PaymentRequest, PaymentStatus> status;

	public static final String AMOUNT = "amount";
	public static final String NOTES = "notes";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String BANK_ACCOUNT_DETAILS = "bankAccountDetails";
	public static final String PAID_DATE = "paidDate";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String REQUEST_DATE = "requestDate";
	public static final String INVOICE_NUMBER = "invoiceNumber";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String PAYMENT_METHOD = "paymentMethod";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

