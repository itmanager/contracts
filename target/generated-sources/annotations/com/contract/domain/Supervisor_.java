package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supervisor.class)
public abstract class Supervisor_ {

	public static volatile SingularAttribute<Supervisor, String> phone;
	public static volatile SingularAttribute<Supervisor, String> name;
	public static volatile SingularAttribute<Supervisor, Double> qualityScore;
	public static volatile SingularAttribute<Supervisor, String> specialization;
	public static volatile SingularAttribute<Supervisor, String> company;
	public static volatile SingularAttribute<Supervisor, Long> id;
	public static volatile SingularAttribute<Supervisor, BigDecimal> hourlyRate;
	public static volatile SingularAttribute<Supervisor, String> email;

	public static final String PHONE = "phone";
	public static final String NAME = "name";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String SPECIALIZATION = "specialization";
	public static final String COMPANY = "company";
	public static final String ID = "id";
	public static final String HOURLY_RATE = "hourlyRate";
	public static final String EMAIL = "email";

}

