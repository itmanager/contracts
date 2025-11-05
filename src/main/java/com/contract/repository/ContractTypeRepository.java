package com.contract.repository;

import com.contract.domain.Contract;
import com.contract.domain.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Contract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractTypeRepository extends JpaRepository<ContractType, Long> {}
