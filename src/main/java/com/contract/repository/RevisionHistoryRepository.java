package com.contract.repository;

import com.contract.domain.RevisionHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RevisionHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RevisionHistoryRepository extends JpaRepository<RevisionHistory, Long> {}
