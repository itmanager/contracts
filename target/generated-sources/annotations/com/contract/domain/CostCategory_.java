package com.contract.domain;

import com.contract.domain.enumeration.CostType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CostCategory.class)
public abstract class CostCategory_ {

	public static volatile SingularAttribute<CostCategory, String> code;
	public static volatile SingularAttribute<CostCategory, CostType> costType;
	public static volatile SingularAttribute<CostCategory, String> name;
	public static volatile SingularAttribute<CostCategory, String> description;
	public static volatile SingularAttribute<CostCategory, Long> id;

	public static final String CODE = "code";
	public static final String COST_TYPE = "costType";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

