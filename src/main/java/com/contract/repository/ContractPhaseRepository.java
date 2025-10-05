package com.contract.repository;

import com.contract.domain.ContractPhase;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the ContractPhase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractPhaseRepository extends JpaRepository<ContractPhase, Long> {

    List<ContractPhase> findByContractId(Long contractId);
}
