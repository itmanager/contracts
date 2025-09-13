package com.contract.repository;

import com.contract.domain.SupervisorComment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SupervisorComment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SupervisorCommentRepository extends JpaRepository<SupervisorComment, Long> {}
