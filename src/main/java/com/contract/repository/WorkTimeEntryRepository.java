package com.contract.repository;

import com.contract.domain.WorkTimeEntry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the WorkTimeEntry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkTimeEntryRepository extends JpaRepository<WorkTimeEntry, Long> {}
