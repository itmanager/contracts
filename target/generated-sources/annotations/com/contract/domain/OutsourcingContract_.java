package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OutsourcingContract.class)
public abstract class OutsourcingContract_ {

	public static volatile SingularAttribute<OutsourcingContract, BigDecimal> totalValue;
	public static volatile SingularAttribute<OutsourcingContract, String> contactInfo;
	public static volatile SingularAttribute<OutsourcingContract, BigDecimal> endDate;
	public static volatile SingularAttribute<OutsourcingContract, String> companyName;
	public static volatile SingularAttribute<OutsourcingContract, String> contractNumber;
	public static volatile SingularAttribute<OutsourcingContract, String> description;
	public static volatile SingularAttribute<OutsourcingContract, String> contactPerson;
	public static volatile SingularAttribute<OutsourcingContract, Long> id;
	public static volatile SingularAttribute<OutsourcingContract, String> deliverables;
	public static volatile SingularAttribute<OutsourcingContract, BigDecimal> startDate;
	public static volatile SingularAttribute<OutsourcingContract, String> paymentTerms;

	public static final String TOTAL_VALUE = "totalValue";
	public static final String CONTACT_INFO = "contactInfo";
	public static final String END_DATE = "endDate";
	public static final String COMPANY_NAME = "companyName";
	public static final String CONTRACT_NUMBER = "contractNumber";
	public static final String DESCRIPTION = "description";
	public static final String CONTACT_PERSON = "contactPerson";
	public static final String ID = "id";
	public static final String DELIVERABLES = "deliverables";
	public static final String START_DATE = "startDate";
	public static final String PAYMENT_TERMS = "paymentTerms";

}

