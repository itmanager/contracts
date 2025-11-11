package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GanttActivity.class)
public abstract class GanttActivity_ {

	public static volatile SingularAttribute<GanttActivity, String> code;
	public static volatile SingularAttribute<GanttActivity, BigDecimal> endDate;
	public static volatile SingularAttribute<GanttActivity, Boolean> isMilestone;
	public static volatile SingularAttribute<GanttActivity, Contract> contract;
	public static volatile SingularAttribute<GanttActivity, String> description;
	public static volatile SingularAttribute<GanttActivity, Integer> weight;
	public static volatile SingularAttribute<GanttActivity, Boolean> criticalPath;
	public static volatile SingularAttribute<GanttActivity, Integer> actualHours;
	public static volatile SingularAttribute<GanttActivity, String> dependencies;
	public static volatile SingularAttribute<GanttActivity, Double> verifiedProgress;
	public static volatile SingularAttribute<GanttActivity, GanttActivity> milestone;
	public static volatile SingularAttribute<GanttActivity, ContractPhase> contractPhase;
	public static volatile SingularAttribute<GanttActivity, String> name;
	public static volatile SingularAttribute<GanttActivity, Double> qualityScore;
	public static volatile SingularAttribute<GanttActivity, BigDecimal> laborCost;
	public static volatile SingularAttribute<GanttActivity, Integer> estimatedHours;
	public static volatile SingularAttribute<GanttActivity, Long> id;
	public static volatile SingularAttribute<GanttActivity, BigDecimal> hourlyRate;
	public static volatile SingularAttribute<GanttActivity, BigDecimal> startDate;
	public static volatile SingularAttribute<GanttActivity, Double> reportedProgress;

	public static final String CODE = "code";
	public static final String END_DATE = "endDate";
	public static final String IS_MILESTONE = "isMilestone";
	public static final String CONTRACT = "contract";
	public static final String DESCRIPTION = "description";
	public static final String WEIGHT = "weight";
	public static final String CRITICAL_PATH = "criticalPath";
	public static final String ACTUAL_HOURS = "actualHours";
	public static final String DEPENDENCIES = "dependencies";
	public static final String VERIFIED_PROGRESS = "verifiedProgress";
	public static final String MILESTONE = "milestone";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String NAME = "name";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String LABOR_COST = "laborCost";
	public static final String ESTIMATED_HOURS = "estimatedHours";
	public static final String ID = "id";
	public static final String HOURLY_RATE = "hourlyRate";
	public static final String START_DATE = "startDate";
	public static final String REPORTED_PROGRESS = "reportedProgress";

}

