package com.contract.repository;

import com.contract.domain.QualityImprovement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityImprovement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityImprovementRepository extends JpaRepository<QualityImprovement, Long> {}
