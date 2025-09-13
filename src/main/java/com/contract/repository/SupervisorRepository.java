package com.contract.repository;

import com.contract.domain.Supervisor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Supervisor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {}
