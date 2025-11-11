package com.contract.repository;

import com.contract.domain.MonthlyPhaseProgress;
import com.google.common.io.Files;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the MonthlyPhaseProgress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyPhaseProgressRepository extends JpaRepository<MonthlyPhaseProgress, Long> {

    void deleteByContractId(Long contractId);

    List<MonthlyPhaseProgress> findAllByContractId(Long contractId);
}
