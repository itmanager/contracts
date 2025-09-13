package com.contract.repository;

import com.contract.domain.QualityAssessment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityAssessment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityAssessmentRepository extends JpaRepository<QualityAssessment, Long> {}
