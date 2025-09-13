package com.contract.repository;

import com.contract.domain.ContractPhase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ContractPhase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractPhaseRepository extends JpaRepository<ContractPhase, Long> {}
