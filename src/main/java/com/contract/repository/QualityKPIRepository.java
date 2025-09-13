package com.contract.repository;

import com.contract.domain.QualityKPI;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityKPI entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityKPIRepository extends JpaRepository<QualityKPI, Long> {}
