package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Equipment.class)
public abstract class Equipment_ {

	public static volatile SingularAttribute<Equipment, BigDecimal> purchaseDate;
	public static volatile SingularAttribute<Equipment, BigDecimal> purchaseCost;
	public static volatile SingularAttribute<Equipment, String> name;
	public static volatile SingularAttribute<Equipment, String> description;
	public static volatile SingularAttribute<Equipment, Long> id;
	public static volatile SingularAttribute<Equipment, String> maintenanceSchedule;
	public static volatile SingularAttribute<Equipment, Double> depreciationRate;
	public static volatile SingularAttribute<Equipment, String> equipmentId;
	public static volatile SingularAttribute<Equipment, BigDecimal> currentValue;

	public static final String PURCHASE_DATE = "purchaseDate";
	public static final String PURCHASE_COST = "purchaseCost";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String MAINTENANCE_SCHEDULE = "maintenanceSchedule";
	public static final String DEPRECIATION_RATE = "depreciationRate";
	public static final String EQUIPMENT_ID = "equipmentId";
	public static final String CURRENT_VALUE = "currentValue";

}

