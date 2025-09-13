package com.contract.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SupervisorComment.class)
public abstract class SupervisorComment_ {

	public static volatile SingularAttribute<SupervisorComment, ContractPhase> contractPhase;
	public static volatile SingularAttribute<SupervisorComment, String> responseText;
	public static volatile SingularAttribute<SupervisorComment, ZonedDateTime> commentDate;
	public static volatile SingularAttribute<SupervisorComment, Contract> contract;
	public static volatile SingularAttribute<SupervisorComment, Double> qualityScore;
	public static volatile SingularAttribute<SupervisorComment, Long> id;
	public static volatile SingularAttribute<SupervisorComment, String> priority;
	public static volatile SingularAttribute<SupervisorComment, String> commentText;
	public static volatile SingularAttribute<SupervisorComment, ZonedDateTime> responseDate;
	public static volatile SingularAttribute<SupervisorComment, Supervisor> supervisor;
	public static volatile SingularAttribute<SupervisorComment, String> status;
	public static volatile SingularAttribute<SupervisorComment, String> relatedTo;

	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String RESPONSE_TEXT = "responseText";
	public static final String COMMENT_DATE = "commentDate";
	public static final String CONTRACT = "contract";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String ID = "id";
	public static final String PRIORITY = "priority";
	public static final String COMMENT_TEXT = "commentText";
	public static final String RESPONSE_DATE = "responseDate";
	public static final String SUPERVISOR = "supervisor";
	public static final String STATUS = "status";
	public static final String RELATED_TO = "relatedTo";

}

