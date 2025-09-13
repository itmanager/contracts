package com.contract.repository;

import com.contract.domain.GanttActivity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the GanttActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GanttActivityRepository extends JpaRepository<GanttActivity, Long> {}
