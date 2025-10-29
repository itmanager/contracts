package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TestReport.class)
public abstract class TestReport_ {

	public static volatile SingularAttribute<TestReport, String> relatedModule;
	public static volatile SingularAttribute<TestReport, String> testData;
	public static volatile SingularAttribute<TestReport, String> buildVersion;
	public static volatile SingularAttribute<TestReport, String> notes;
	public static volatile SingularAttribute<TestReport, String> preConditions;
	public static volatile SingularAttribute<TestReport, String> postConditions;
	public static volatile SingularAttribute<TestReport, String> conclusions;
	public static volatile SingularAttribute<TestReport, String> filePath;
	public static volatile SingularAttribute<TestReport, Contract> contract;
	public static volatile SingularAttribute<TestReport, String> testType;
	public static volatile SingularAttribute<TestReport, String> description;
	public static volatile SingularAttribute<TestReport, String> testSteps;
	public static volatile SingularAttribute<TestReport, String> title;
	public static volatile SingularAttribute<TestReport, String> recommendations;
	public static volatile SingularAttribute<TestReport, String> environment;
	public static volatile SingularAttribute<TestReport, String> expectedResult;
	public static volatile SingularAttribute<TestReport, ContractPhase> contractPhase;
	public static volatile SingularAttribute<TestReport, Double> qualityScore;
	public static volatile SingularAttribute<TestReport, String> objectives;
	public static volatile SingularAttribute<TestReport, Long> id;
	public static volatile SingularAttribute<TestReport, String> results;
	public static volatile SingularAttribute<TestReport, BigDecimal> testDate;
	public static volatile SingularAttribute<TestReport, String> participants;
	public static volatile SingularAttribute<TestReport, String> status;

	public static final String RELATED_MODULE = "relatedModule";
	public static final String TEST_DATA = "testData";
	public static final String BUILD_VERSION = "buildVersion";
	public static final String NOTES = "notes";
	public static final String PRE_CONDITIONS = "preConditions";
	public static final String POST_CONDITIONS = "postConditions";
	public static final String CONCLUSIONS = "conclusions";
	public static final String FILE_PATH = "filePath";
	public static final String CONTRACT = "contract";
	public static final String TEST_TYPE = "testType";
	public static final String DESCRIPTION = "description";
	public static final String TEST_STEPS = "testSteps";
	public static final String TITLE = "title";
	public static final String RECOMMENDATIONS = "recommendations";
	public static final String ENVIRONMENT = "environment";
	public static final String EXPECTED_RESULT = "expectedResult";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String OBJECTIVES = "objectives";
	public static final String ID = "id";
	public static final String RESULTS = "results";
	public static final String TEST_DATE = "testDate";
	public static final String PARTICIPANTS = "participants";
	public static final String STATUS = "status";

}

