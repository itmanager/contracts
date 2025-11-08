package com.contract.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BudgetAllocation.class)
public abstract class BudgetAllocation_ {

	public static volatile SingularAttribute<BudgetAllocation, BigDecimal> lastUpdated;
	public static volatile SingularAttribute<BudgetAllocation, BigDecimal> allocatedBudget;
	public static volatile SingularAttribute<BudgetAllocation, BigDecimal> spentBudget;
	public static volatile SingularAttribute<BudgetAllocation, BigDecimal> remainingBudget;
	public static volatile SingularAttribute<BudgetAllocation, Integer> revisionNumber;
	public static volatile SingularAttribute<BudgetAllocation, ContractPhase> contractPhase;
	public static volatile SingularAttribute<BudgetAllocation, String> contractPhaseName;
	public static volatile SingularAttribute<BudgetAllocation, Contract> contract;
	public static volatile SingularAttribute<BudgetAllocation, Double> qualityScore;
	public static volatile SingularAttribute<BudgetAllocation, String> contractName;
	public static volatile SingularAttribute<BudgetAllocation, Long> id;

	public static final String LAST_UPDATED = "lastUpdated";
	public static final String ALLOCATED_BUDGET = "allocatedBudget";
	public static final String SPENT_BUDGET = "spentBudget";
	public static final String REMAINING_BUDGET = "remainingBudget";
	public static final String REVISION_NUMBER = "revisionNumber";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String CONTRACT_PHASE_NAME = "contractPhaseName";
	public static final String CONTRACT = "contract";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String CONTRACT_NAME = "contractName";
	public static final String ID = "id";

}

