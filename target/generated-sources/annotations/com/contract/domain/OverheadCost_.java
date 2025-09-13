package com.contract.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OverheadCost.class)
public abstract class OverheadCost_ {

	public static volatile SingularAttribute<OverheadCost, BigDecimal> amount;
	public static volatile SingularAttribute<OverheadCost, ContractPhase> allocatedToPhase;
	public static volatile SingularAttribute<OverheadCost, LocalDate> costDate;
	public static volatile SingularAttribute<OverheadCost, String> allocationMethod;
	public static volatile SingularAttribute<OverheadCost, Contract> allocatedTo;
	public static volatile SingularAttribute<OverheadCost, String> name;
	public static volatile SingularAttribute<OverheadCost, String> description;
	public static volatile SingularAttribute<OverheadCost, Long> id;
	public static volatile SingularAttribute<OverheadCost, String> category;

	public static final String AMOUNT = "amount";
	public static final String ALLOCATED_TO_PHASE = "allocatedToPhase";
	public static final String COST_DATE = "costDate";
	public static final String ALLOCATION_METHOD = "allocationMethod";
	public static final String ALLOCATED_TO = "allocatedTo";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String CATEGORY = "category";

}

