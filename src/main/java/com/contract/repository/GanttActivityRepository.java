package com.contract.repository;

import com.contract.domain.GanttActivity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the GanttActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GanttActivityRepository extends JpaRepository<GanttActivity, Long> {

    List<GanttActivity> findByContractPhaseIn(Long[] aLong);

    List<GanttActivity> findByContractId(Long contractId);
}
