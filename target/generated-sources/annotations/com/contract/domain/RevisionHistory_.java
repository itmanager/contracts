package com.contract.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RevisionHistory.class)
public abstract class RevisionHistory_ {

	public static volatile SingularAttribute<RevisionHistory, String> revisionType;
	public static volatile SingularAttribute<RevisionHistory, String> revisedBy;
	public static volatile SingularAttribute<RevisionHistory, ContractPhase> contractPhase;
	public static volatile SingularAttribute<RevisionHistory, ZonedDateTime> revisionDate;
	public static volatile SingularAttribute<RevisionHistory, Contract> contract;
	public static volatile SingularAttribute<RevisionHistory, Long> id;
	public static volatile SingularAttribute<RevisionHistory, String> revisionDescription;
	public static volatile SingularAttribute<RevisionHistory, String> affectedEntities;
	public static volatile SingularAttribute<RevisionHistory, Double> qualityImpact;

	public static final String REVISION_TYPE = "revisionType";
	public static final String REVISED_BY = "revisedBy";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String REVISION_DATE = "revisionDate";
	public static final String CONTRACT = "contract";
	public static final String ID = "id";
	public static final String REVISION_DESCRIPTION = "revisionDescription";
	public static final String AFFECTED_ENTITIES = "affectedEntities";
	public static final String QUALITY_IMPACT = "qualityImpact";

}

