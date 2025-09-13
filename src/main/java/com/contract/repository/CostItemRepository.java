package com.contract.repository;

import com.contract.domain.CostItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CostItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostItemRepository extends JpaRepository<CostItem, Long> {}
