package com.contract.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EquipmentUsage.class)
public abstract class EquipmentUsage_ {

	public static volatile SingularAttribute<EquipmentUsage, Integer> hoursUsed;
	public static volatile SingularAttribute<EquipmentUsage, BigDecimal> cost;
	public static volatile SingularAttribute<EquipmentUsage, String> notes;
	public static volatile SingularAttribute<EquipmentUsage, ZonedDateTime> endDate;
	public static volatile SingularAttribute<EquipmentUsage, GanttActivity> ganttActivity;
	public static volatile SingularAttribute<EquipmentUsage, Equipment> equipment;
	public static volatile SingularAttribute<EquipmentUsage, Long> id;
	public static volatile SingularAttribute<EquipmentUsage, ZonedDateTime> startDate;

	public static final String HOURS_USED = "hoursUsed";
	public static final String COST = "cost";
	public static final String NOTES = "notes";
	public static final String END_DATE = "endDate";
	public static final String GANTT_ACTIVITY = "ganttActivity";
	public static final String EQUIPMENT = "equipment";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";

}

