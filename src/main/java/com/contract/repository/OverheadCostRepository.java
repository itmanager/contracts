package com.contract.repository;

import com.contract.domain.OverheadCost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OverheadCost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OverheadCostRepository extends JpaRepository<OverheadCost, Long> {}
