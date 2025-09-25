package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthlyPhaseProgress.class)
public abstract class MonthlyPhaseProgress_ {

	public static volatile SingularAttribute<MonthlyPhaseProgress, String> notes;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> approvalDate;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Integer> year;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> equipmentCost;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> submissionDate;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> outsourcingCost;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> verifiedProgress;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Integer> month;
	public static volatile SingularAttribute<MonthlyPhaseProgress, ContractPhase> contractPhase;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> qualityScore;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> laborCost;
	public static volatile SingularAttribute<MonthlyPhaseProgress, BigDecimal> overheadCost;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Long> id;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Integer> laborHours;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> reportedProgress;

	public static final String NOTES = "notes";
	public static final String APPROVAL_DATE = "approvalDate";
	public static final String YEAR = "year";
	public static final String EQUIPMENT_COST = "equipmentCost";
	public static final String SUBMISSION_DATE = "submissionDate";
	public static final String OUTSOURCING_COST = "outsourcingCost";
	public static final String VERIFIED_PROGRESS = "verifiedProgress";
	public static final String MONTH = "month";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String LABOR_COST = "laborCost";
	public static final String OVERHEAD_COST = "overheadCost";
	public static final String ID = "id";
	public static final String LABOR_HOURS = "laborHours";
	public static final String REPORTED_PROGRESS = "reportedProgress";

}

