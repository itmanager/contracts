package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EquipmentUsage.class)
public abstract class EquipmentUsage_ {

	public static volatile SingularAttribute<EquipmentUsage, String> country;
	public static volatile SingularAttribute<EquipmentUsage, String> notes;
	public static volatile SingularAttribute<EquipmentUsage, String> function;
	public static volatile SingularAttribute<EquipmentUsage, Contract> contract;
	public static volatile SingularAttribute<EquipmentUsage, String> name;
	public static volatile SingularAttribute<EquipmentUsage, Integer> count;
	public static volatile SingularAttribute<EquipmentUsage, String> model;
	public static volatile SingularAttribute<EquipmentUsage, Long> id;
	public static volatile SingularAttribute<EquipmentUsage, BigDecimal> wholesalePrice;
	public static volatile SingularAttribute<EquipmentUsage, String> specifications;
	public static volatile SingularAttribute<EquipmentUsage, String> group;
	public static volatile SingularAttribute<EquipmentUsage, String> classField;

	public static final String COUNTRY = "country";
	public static final String NOTES = "notes";
	public static final String FUNCTION = "function";
	public static final String CONTRACT = "contract";
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String WHOLESALE_PRICE = "wholesalePrice";
	public static final String SPECIFICATIONS = "specifications";
	public static final String GROUP = "group";
	public static final String CLASS_FIELD = "classField";

}

