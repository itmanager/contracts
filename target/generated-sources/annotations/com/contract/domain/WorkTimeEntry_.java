package com.contract.domain;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkTimeEntry.class)
public abstract class WorkTimeEntry_ {

	public static volatile SingularAttribute<WorkTimeEntry, Boolean> approved;
	public static volatile SingularAttribute<WorkTimeEntry, ZonedDateTime> approvalDate;
	public static volatile SingularAttribute<WorkTimeEntry, LocalDate> entryDate;
	public static volatile SingularAttribute<WorkTimeEntry, GanttActivity> ganttActivity;
	public static volatile SingularAttribute<WorkTimeEntry, Supervisor> approvedBy;
	public static volatile SingularAttribute<WorkTimeEntry, String> description;
	public static volatile SingularAttribute<WorkTimeEntry, Long> id;
	public static volatile SingularAttribute<WorkTimeEntry, Employee> employee;
	public static volatile SingularAttribute<WorkTimeEntry, Integer> hoursWorked;

	public static final String APPROVED = "approved";
	public static final String APPROVAL_DATE = "approvalDate";
	public static final String ENTRY_DATE = "entryDate";
	public static final String GANTT_ACTIVITY = "ganttActivity";
	public static final String APPROVED_BY = "approvedBy";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String EMPLOYEE = "employee";
	public static final String HOURS_WORKED = "hoursWorked";

}

