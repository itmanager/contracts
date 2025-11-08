package com.contract.repository;

import com.contract.domain.BudgetAllocation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the BudgetAllocation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BudgetAllocationRepository extends JpaRepository<BudgetAllocation, Long> {

    List<BudgetAllocation> findAllByContractPhaseId(Long contractPhaseId);

    List<BudgetAllocation> findAllByContractId(Long contractId);
}
