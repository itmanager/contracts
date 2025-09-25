package com.contract.domain;

import com.contract.domain.enumeration.CostType;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CostItem.class)
public abstract class CostItem_ {

	public static volatile SingularAttribute<CostItem, BigDecimal> unitPrice;
	public static volatile SingularAttribute<CostItem, BigDecimal> totalAmount;
	public static volatile SingularAttribute<CostItem, Boolean> approved;
	public static volatile SingularAttribute<CostItem, CostCategory> costCategory;
	public static volatile SingularAttribute<CostItem, Integer> quantity;
	public static volatile SingularAttribute<CostItem, BigDecimal> costDate;
	public static volatile SingularAttribute<CostItem, String> notes;
	public static volatile SingularAttribute<CostItem, ContractPhase> contractPhase;
	public static volatile SingularAttribute<CostItem, CostType> costType;
	public static volatile SingularAttribute<CostItem, Double> qualityScore;
	public static volatile SingularAttribute<CostItem, String> description;
	public static volatile SingularAttribute<CostItem, Long> id;

	public static final String UNIT_PRICE = "unitPrice";
	public static final String TOTAL_AMOUNT = "totalAmount";
	public static final String APPROVED = "approved";
	public static final String COST_CATEGORY = "costCategory";
	public static final String QUANTITY = "quantity";
	public static final String COST_DATE = "costDate";
	public static final String NOTES = "notes";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String COST_TYPE = "costType";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

