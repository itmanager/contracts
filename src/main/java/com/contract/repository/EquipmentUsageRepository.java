package com.contract.repository;

import com.contract.domain.EquipmentUsage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the EquipmentUsage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipmentUsageRepository extends JpaRepository<EquipmentUsage, Long> {}
