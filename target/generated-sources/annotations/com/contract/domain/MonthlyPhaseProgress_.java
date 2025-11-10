package com.contract.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthlyPhaseProgress.class)
public abstract class MonthlyPhaseProgress_ {

	public static volatile SingularAttribute<MonthlyPhaseProgress, String> notes;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Long> approvalDate;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Integer> year;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Contract> contract;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Long> submissionDate;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> actualHours;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> verifiedProgress;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Integer> month;
	public static volatile SingularAttribute<MonthlyPhaseProgress, GanttActivity> milestone;
	public static volatile SingularAttribute<MonthlyPhaseProgress, ContractPhase> contractPhase;
	public static volatile SingularAttribute<MonthlyPhaseProgress, String> contractPhaseName;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> programProgress;
	public static volatile SingularAttribute<MonthlyPhaseProgress, String> contractName;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Long> id;
	public static volatile SingularAttribute<MonthlyPhaseProgress, Double> reportedProgress;
	public static volatile SingularAttribute<MonthlyPhaseProgress, String> ganttName;

	public static final String NOTES = "notes";
	public static final String APPROVAL_DATE = "approvalDate";
	public static final String YEAR = "year";
	public static final String CONTRACT = "contract";
	public static final String SUBMISSION_DATE = "submissionDate";
	public static final String ACTUAL_HOURS = "actualHours";
	public static final String VERIFIED_PROGRESS = "verifiedProgress";
	public static final String MONTH = "month";
	public static final String MILESTONE = "milestone";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String CONTRACT_PHASE_NAME = "contractPhaseName";
	public static final String PROGRAM_PROGRESS = "programProgress";
	public static final String CONTRACT_NAME = "contractName";
	public static final String ID = "id";
	public static final String REPORTED_PROGRESS = "reportedProgress";
	public static final String GANTT_NAME = "ganttName";

}

