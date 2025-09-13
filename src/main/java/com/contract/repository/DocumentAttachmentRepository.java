package com.contract.repository;

import com.contract.domain.DocumentAttachment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DocumentAttachment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentAttachmentRepository extends JpaRepository<DocumentAttachment, Long> {}
