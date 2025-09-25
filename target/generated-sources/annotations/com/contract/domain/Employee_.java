package com.contract.domain;

import com.contract.domain.enumeration.EmployeeType;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, EmployeeType> employeeType;
	public static volatile SingularAttribute<Employee, String> phone;
	public static volatile SingularAttribute<Employee, BigDecimal> endDate;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Boolean> active;
	public static volatile SingularAttribute<Employee, String> employeeId;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, String> position;
	public static volatile SingularAttribute<Employee, String> department;
	public static volatile SingularAttribute<Employee, BigDecimal> hourlyRate;
	public static volatile SingularAttribute<Employee, String> email;
	public static volatile SingularAttribute<Employee, BigDecimal> startDate;

	public static final String EMPLOYEE_TYPE = "employeeType";
	public static final String PHONE = "phone";
	public static final String END_DATE = "endDate";
	public static final String NAME = "name";
	public static final String ACTIVE = "active";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String ID = "id";
	public static final String POSITION = "position";
	public static final String DEPARTMENT = "department";
	public static final String HOURLY_RATE = "hourlyRate";
	public static final String EMAIL = "email";
	public static final String START_DATE = "startDate";

}

