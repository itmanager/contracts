package com.contract.domain;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QualityImprovement.class)
public abstract class QualityImprovement_ {

	public static volatile SingularAttribute<QualityImprovement, Double> effectiveness;
	public static volatile SingularAttribute<QualityImprovement, BigDecimal> costImpact;
	public static volatile SingularAttribute<QualityImprovement, Integer> timeImpact;
	public static volatile SingularAttribute<QualityImprovement, Contract> contract;
	public static volatile SingularAttribute<QualityImprovement, Double> afterScore;
	public static volatile SingularAttribute<QualityImprovement, String> description;
	public static volatile SingularAttribute<QualityImprovement, String> title;
	public static volatile SingularAttribute<QualityImprovement, String> assignedTo;
	public static volatile SingularAttribute<QualityImprovement, QualityAssessment> qualityAssessment;
	public static volatile SingularAttribute<QualityImprovement, ContractPhase> contractPhase;
	public static volatile SingularAttribute<QualityImprovement, Long> id;
	public static volatile SingularAttribute<QualityImprovement, ZonedDateTime> deadline;
	public static volatile SingularAttribute<QualityImprovement, String> status;
	public static volatile SingularAttribute<QualityImprovement, Double> beforeScore;

	public static final String EFFECTIVENESS = "effectiveness";
	public static final String COST_IMPACT = "costImpact";
	public static final String TIME_IMPACT = "timeImpact";
	public static final String CONTRACT = "contract";
	public static final String AFTER_SCORE = "afterScore";
	public static final String DESCRIPTION = "description";
	public static final String TITLE = "title";
	public static final String ASSIGNED_TO = "assignedTo";
	public static final String QUALITY_ASSESSMENT = "qualityAssessment";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String ID = "id";
	public static final String DEADLINE = "deadline";
	public static final String STATUS = "status";
	public static final String BEFORE_SCORE = "beforeScore";

}

