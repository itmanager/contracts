package com.contract.repository;

import com.contract.domain.OutsourcingContract;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingContract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingContractRepository extends JpaRepository<OutsourcingContract, Long> {}
