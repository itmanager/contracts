package com.contract.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(QualityAssessment.class)
public abstract class QualityAssessment_ {

	public static volatile SingularAttribute<QualityAssessment, String> metric_unit;
	public static volatile SingularAttribute<QualityAssessment, String> trend;
	public static volatile SingularAttribute<QualityAssessment, Long> assessmentDate;
	public static volatile SingularAttribute<QualityAssessment, String> metric_maxAcceptable;
	public static volatile SingularAttribute<QualityAssessment, String> testMethods;
	public static volatile SingularAttribute<QualityAssessment, String> recommendations;
	public static volatile SingularAttribute<QualityAssessment, String> characteristic;
	public static volatile SingularAttribute<QualityAssessment, Integer> score;
	public static volatile SingularAttribute<QualityAssessment, String> metric_name;
	public static volatile SingularAttribute<QualityAssessment, String> metric_result;
	public static volatile SingularAttribute<QualityAssessment, String> weaknesses;
	public static volatile SingularAttribute<QualityAssessment, Long> id;
	public static volatile SingularAttribute<QualityAssessment, String> metric_score;
	public static volatile SingularAttribute<QualityAssessment, String> comments;
	public static volatile SingularAttribute<QualityAssessment, Integer> maxAcceptable;
	public static volatile SingularAttribute<QualityAssessment, Contract> contract;
	public static volatile SingularAttribute<QualityAssessment, String> priority;
	public static volatile SingularAttribute<QualityAssessment, String> certifications;
	public static volatile SingularAttribute<QualityAssessment, String> version;
	public static volatile SingularAttribute<QualityAssessment, String> assetType;
	public static volatile SingularAttribute<QualityAssessment, String> subCharacteristic;
	public static volatile SingularAttribute<QualityAssessment, String> risks;
	public static volatile SingularAttribute<QualityAssessment, Integer> minAcceptable;
	public static volatile SingularAttribute<QualityAssessment, String> assessedBy;
	public static volatile SingularAttribute<QualityAssessment, String> standardsCompliance;
	public static volatile SingularAttribute<QualityAssessment, String> strengths;
	public static volatile SingularAttribute<QualityAssessment, String> toolsUsed;
	public static volatile SingularAttribute<QualityAssessment, String> metric_minAcceptable;
	public static volatile SingularAttribute<QualityAssessment, String> metric_weight;
	public static volatile SingularAttribute<QualityAssessment, String> status;

	public static final String METRIC_UNIT = "metric_unit";
	public static final String TREND = "trend";
	public static final String ASSESSMENT_DATE = "assessmentDate";
	public static final String METRIC_MAX_ACCEPTABLE = "metric_maxAcceptable";
	public static final String TEST_METHODS = "testMethods";
	public static final String RECOMMENDATIONS = "recommendations";
	public static final String CHARACTERISTIC = "characteristic";
	public static final String SCORE = "score";
	public static final String METRIC_NAME = "metric_name";
	public static final String METRIC_RESULT = "metric_result";
	public static final String WEAKNESSES = "weaknesses";
	public static final String ID = "id";
	public static final String METRIC_SCORE = "metric_score";
	public static final String COMMENTS = "comments";
	public static final String MAX_ACCEPTABLE = "maxAcceptable";
	public static final String CONTRACT = "contract";
	public static final String PRIORITY = "priority";
	public static final String CERTIFICATIONS = "certifications";
	public static final String VERSION = "version";
	public static final String ASSET_TYPE = "assetType";
	public static final String SUB_CHARACTERISTIC = "subCharacteristic";
	public static final String RISKS = "risks";
	public static final String MIN_ACCEPTABLE = "minAcceptable";
	public static final String ASSESSED_BY = "assessedBy";
	public static final String STANDARDS_COMPLIANCE = "standardsCompliance";
	public static final String STRENGTHS = "strengths";
	public static final String TOOLS_USED = "toolsUsed";
	public static final String METRIC_MIN_ACCEPTABLE = "metric_minAcceptable";
	public static final String METRIC_WEIGHT = "metric_weight";
	public static final String STATUS = "status";

}

