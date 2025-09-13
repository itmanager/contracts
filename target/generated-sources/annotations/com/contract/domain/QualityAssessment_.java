package com.contract.domain;

import com.contract.domain.enumeration.QualityDimension;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QualityAssessment.class)
public abstract class QualityAssessment_ {

	public static volatile SingularAttribute<QualityAssessment, Double> score;
	public static volatile SingularAttribute<QualityAssessment, String> comments;
	public static volatile SingularAttribute<QualityAssessment, String> assessedBy;
	public static volatile SingularAttribute<QualityAssessment, ContractPhase> contractPhase;
	public static volatile SingularAttribute<QualityAssessment, ZonedDateTime> assessmentDate;
	public static volatile SingularAttribute<QualityAssessment, Contract> contract;
	public static volatile SingularAttribute<QualityAssessment, Integer> weight;
	public static volatile SingularAttribute<QualityAssessment, Long> id;
	public static volatile SingularAttribute<QualityAssessment, QualityDimension> dimension;
	public static volatile SingularAttribute<QualityAssessment, String> recommendations;
	public static volatile SingularAttribute<QualityAssessment, ZonedDateTime> nextAssessmentDate;

	public static final String SCORE = "score";
	public static final String COMMENTS = "comments";
	public static final String ASSESSED_BY = "assessedBy";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String ASSESSMENT_DATE = "assessmentDate";
	public static final String CONTRACT = "contract";
	public static final String WEIGHT = "weight";
	public static final String ID = "id";
	public static final String DIMENSION = "dimension";
	public static final String RECOMMENDATIONS = "recommendations";
	public static final String NEXT_ASSESSMENT_DATE = "nextAssessmentDate";

}

