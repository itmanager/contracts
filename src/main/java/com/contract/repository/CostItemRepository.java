package com.contract.repository;

import com.contract.domain.CostItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Spring Data JPA repository for the CostItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostItemRepository extends JpaRepository<CostItem, Long> {
    List<CostItem> findAllByContractPhaseId(Long contractPhaseId);

    List<CostItem> findAllByContractId(Long contractId);
}
