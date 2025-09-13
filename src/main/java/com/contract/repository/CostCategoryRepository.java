package com.contract.repository;

import com.contract.domain.CostCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CostCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostCategoryRepository extends JpaRepository<CostCategory, Long> {}
