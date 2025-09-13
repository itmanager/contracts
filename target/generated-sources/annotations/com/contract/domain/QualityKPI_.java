package com.contract.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QualityKPI.class)
public abstract class QualityKPI_ {

	public static volatile SingularAttribute<QualityKPI, String> unit;
	public static volatile SingularAttribute<QualityKPI, String> trend;
	public static volatile SingularAttribute<QualityKPI, ContractPhase> contractPhase;
	public static volatile SingularAttribute<QualityKPI, Integer> importance;
	public static volatile SingularAttribute<QualityKPI, Contract> contract;
	public static volatile SingularAttribute<QualityKPI, String> name;
	public static volatile SingularAttribute<QualityKPI, Double> targetValue;
	public static volatile SingularAttribute<QualityKPI, String> description;
	public static volatile SingularAttribute<QualityKPI, String> formula;
	public static volatile SingularAttribute<QualityKPI, Long> id;
	public static volatile SingularAttribute<QualityKPI, Double> currentValue;
	public static volatile SingularAttribute<QualityKPI, String> frequency;

	public static final String UNIT = "unit";
	public static final String TREND = "trend";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String IMPORTANCE = "importance";
	public static final String CONTRACT = "contract";
	public static final String NAME = "name";
	public static final String TARGET_VALUE = "targetValue";
	public static final String DESCRIPTION = "description";
	public static final String FORMULA = "formula";
	public static final String ID = "id";
	public static final String CURRENT_VALUE = "currentValue";
	public static final String FREQUENCY = "frequency";

}

