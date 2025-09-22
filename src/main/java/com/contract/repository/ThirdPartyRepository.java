package com.contract.repository;


import com.contract.domain.ThirdPartyEntity;
import com.contract.domain.enumeration.ThirdPartyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdPartyEntity, Long>, JpaSpecificationExecutor<ThirdPartyEntity> {

    List<ThirdPartyEntity> findByType(ThirdPartyType type);
    
    List<ThirdPartyEntity> findByIsActive(Boolean isActive);
    
    List<ThirdPartyEntity> findByNameContainingIgnoreCase(String name);
    
    Optional<ThirdPartyEntity> findByTaxId(String taxId);
    
    Optional<ThirdPartyEntity> findByRegistrationNumber(String registrationNumber);
    
    List<ThirdPartyEntity> findByTypeAndIsActive(ThirdPartyType type, Boolean isActive);
}