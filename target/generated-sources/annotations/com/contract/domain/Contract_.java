package com.contract.domain;

import com.contract.domain.enumeration.ContractStatus;
import com.contract.domain.enumeration.QualityStatus;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Contract.class)
public abstract class Contract_ {

	public static volatile SingularAttribute<Contract, String> supervisorName;
	public static volatile SingularAttribute<Contract, ThirdPartyEntity> contractor;
	public static volatile SingularAttribute<Contract, String> notes;
	public static volatile SingularAttribute<Contract, BigDecimal> totalOutsourcingCost;
	public static volatile SingularAttribute<Contract, BigDecimal> totalBudget;
	public static volatile SingularAttribute<Contract, BigDecimal> endDate;
	public static volatile SingularAttribute<Contract, String> description;
	public static volatile SingularAttribute<Contract, String> employerName;
	public static volatile SingularAttribute<Contract, String> contractorName;
	public static volatile SingularAttribute<Contract, String> title;
	public static volatile SingularAttribute<Contract, String> physicalAddress;
	public static volatile SingularAttribute<Contract, Double> qualityScore;
	public static volatile SingularAttribute<Contract, QualityStatus> qualityStatus;
	public static volatile SingularAttribute<Contract, ThirdPartyEntity> employer;
	public static volatile SingularAttribute<Contract, Long> id;
	public static volatile SingularAttribute<Contract, BigDecimal> totalEquipmentCost;
	public static volatile SingularAttribute<Contract, BigDecimal> totalOverheadCost;
	public static volatile SingularAttribute<Contract, BigDecimal> currentBudget;
	public static volatile SingularAttribute<Contract, BigDecimal> costVariance;
	public static volatile SingularAttribute<Contract, String> contractNumber;
	public static volatile SingularAttribute<Contract, BigDecimal> totalActualCost;
	public static volatile SingularAttribute<Contract, String> userName;
	public static volatile SingularAttribute<Contract, BigDecimal> lastQualityAssessment;
	public static volatile SingularAttribute<Contract, ThirdPartyEntity> user;
	public static volatile SingularAttribute<Contract, BigDecimal> startDate;
	public static volatile SingularAttribute<Contract, BigDecimal> totalLaborCost;
	public static volatile SingularAttribute<Contract, Supervisor> supervisor;
	public static volatile SingularAttribute<Contract, ContractStatus> status;

	public static final String SUPERVISOR_NAME = "supervisorName";
	public static final String CONTRACTOR = "contractor";
	public static final String NOTES = "notes";
	public static final String TOTAL_OUTSOURCING_COST = "totalOutsourcingCost";
	public static final String TOTAL_BUDGET = "totalBudget";
	public static final String END_DATE = "endDate";
	public static final String DESCRIPTION = "description";
	public static final String EMPLOYER_NAME = "employerName";
	public static final String CONTRACTOR_NAME = "contractorName";
	public static final String TITLE = "title";
	public static final String PHYSICAL_ADDRESS = "physicalAddress";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String QUALITY_STATUS = "qualityStatus";
	public static final String EMPLOYER = "employer";
	public static final String ID = "id";
	public static final String TOTAL_EQUIPMENT_COST = "totalEquipmentCost";
	public static final String TOTAL_OVERHEAD_COST = "totalOverheadCost";
	public static final String CURRENT_BUDGET = "currentBudget";
	public static final String COST_VARIANCE = "costVariance";
	public static final String CONTRACT_NUMBER = "contractNumber";
	public static final String TOTAL_ACTUAL_COST = "totalActualCost";
	public static final String USER_NAME = "userName";
	public static final String LAST_QUALITY_ASSESSMENT = "lastQualityAssessment";
	public static final String USER = "user";
	public static final String START_DATE = "startDate";
	public static final String TOTAL_LABOR_COST = "totalLaborCost";
	public static final String SUPERVISOR = "supervisor";
	public static final String STATUS = "status";

}

