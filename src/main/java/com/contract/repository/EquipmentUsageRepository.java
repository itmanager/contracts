package com.contract.repository;

import com.contract.domain.EquipmentUsage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the EquipmentUsage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EquipmentUsageRepository extends JpaRepository<EquipmentUsage, Long> {
/*    @Query("select equipmentUsage from EquipmentUsage equipmentUsage where equipmentUsage.contract.id = :contractId")
    List<EquipmentUsage> findByContractId(@Param("contractId") Long contractId);*/

    @Query("select equipmentUsage from EquipmentUsage equipmentUsage " +
            "where equipmentUsage.name like %:search% " +
            "or equipmentUsage.group like %:search% " +
            "or equipmentUsage.model like %:search% " +
            "or equipmentUsage.country like %:search%")
    Page<EquipmentUsage> search(@Param("search") String search, Pageable pageable);

    Page<EquipmentUsage> findByContractId(Long contractId, Pageable pageable);

    Optional<EquipmentUsage> findByNameAndContractId(String name, Long contractId);
}
