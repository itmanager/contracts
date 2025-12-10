package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkTimeEntry.class)
public abstract class WorkTimeEntry_ {

	public static volatile SingularAttribute<WorkTimeEntry, String> employeeName;
	public static volatile SingularAttribute<WorkTimeEntry, BigDecimal> approvalDate;
	public static volatile SingularAttribute<WorkTimeEntry, BigDecimal> entryDate;
	public static volatile SingularAttribute<WorkTimeEntry, Integer> year;
	public static volatile SingularAttribute<WorkTimeEntry, Contract> contract;
	public static volatile SingularAttribute<WorkTimeEntry, Supervisor> approvedBy;
	public static volatile SingularAttribute<WorkTimeEntry, String> description;
	public static volatile SingularAttribute<WorkTimeEntry, Long> employeeId;
	public static volatile SingularAttribute<WorkTimeEntry, Boolean> approved;
	public static volatile SingularAttribute<WorkTimeEntry, Integer> month;
	public static volatile SingularAttribute<WorkTimeEntry, ContractPhase> contractPhase;
	public static volatile SingularAttribute<WorkTimeEntry, GanttActivity> ganttActivity;
	public static volatile SingularAttribute<WorkTimeEntry, Long> id;
	public static volatile SingularAttribute<WorkTimeEntry, Integer> hoursWorked;

	public static final String EMPLOYEE_NAME = "employeeName";
	public static final String APPROVAL_DATE = "approvalDate";
	public static final String ENTRY_DATE = "entryDate";
	public static final String YEAR = "year";
	public static final String CONTRACT = "contract";
	public static final String APPROVED_BY = "approvedBy";
	public static final String DESCRIPTION = "description";
	public static final String EMPLOYEE_ID = "employeeId";
	public static final String APPROVED = "approved";
	public static final String MONTH = "month";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String GANTT_ACTIVITY = "ganttActivity";
	public static final String ID = "id";
	public static final String HOURS_WORKED = "hoursWorked";

}

