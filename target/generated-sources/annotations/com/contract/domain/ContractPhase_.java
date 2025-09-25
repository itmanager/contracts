package com.contract.domain;

import com.contract.domain.enumeration.PhaseStatus;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContractPhase.class)
public abstract class ContractPhase_ {

	public static volatile SingularAttribute<ContractPhase, String> notes;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> endDate;
	public static volatile SingularAttribute<ContractPhase, String> description;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> overheadBudget;
	public static volatile SingularAttribute<ContractPhase, Double> verifiedProgress;
	public static volatile SingularAttribute<ContractPhase, Integer> totalLaborHours;
	public static volatile SingularAttribute<ContractPhase, Double> qualityScore;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> outsourcingBudget;
	public static volatile SingularAttribute<ContractPhase, Long> id;
	public static volatile SingularAttribute<ContractPhase, Double> reportedProgress;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> budget;
	public static volatile SingularAttribute<ContractPhase, Double> plannedProgress;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> equipmentCost;
	public static volatile SingularAttribute<ContractPhase, Contract> contract;
	public static volatile SingularAttribute<ContractPhase, Integer> weight;
	public static volatile SingularAttribute<ContractPhase, Integer> priority;
	public static volatile SingularAttribute<ContractPhase, Double> costPerformanceIndex;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> equipmentBudget;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> outsourcingCost;
	public static volatile SingularAttribute<ContractPhase, String> name;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> laborCost;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> overheadCost;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> startDate;
	public static volatile SingularAttribute<ContractPhase, PhaseStatus> status;
	public static volatile SingularAttribute<ContractPhase, BigDecimal> laborBudget;

	public static final String NOTES = "notes";
	public static final String END_DATE = "endDate";
	public static final String DESCRIPTION = "description";
	public static final String OVERHEAD_BUDGET = "overheadBudget";
	public static final String VERIFIED_PROGRESS = "verifiedProgress";
	public static final String TOTAL_LABOR_HOURS = "totalLaborHours";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String OUTSOURCING_BUDGET = "outsourcingBudget";
	public static final String ID = "id";
	public static final String REPORTED_PROGRESS = "reportedProgress";
	public static final String BUDGET = "budget";
	public static final String PLANNED_PROGRESS = "plannedProgress";
	public static final String EQUIPMENT_COST = "equipmentCost";
	public static final String CONTRACT = "contract";
	public static final String WEIGHT = "weight";
	public static final String PRIORITY = "priority";
	public static final String COST_PERFORMANCE_INDEX = "costPerformanceIndex";
	public static final String EQUIPMENT_BUDGET = "equipmentBudget";
	public static final String OUTSOURCING_COST = "outsourcingCost";
	public static final String NAME = "name";
	public static final String LABOR_COST = "laborCost";
	public static final String OVERHEAD_COST = "overheadCost";
	public static final String START_DATE = "startDate";
	public static final String STATUS = "status";
	public static final String LABOR_BUDGET = "laborBudget";

}

