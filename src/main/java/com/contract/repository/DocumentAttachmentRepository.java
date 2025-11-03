package com.contract.repository;

import com.contract.domain.DocumentAttachment;
import com.contract.domain.enumeration.AttachmentType;
import com.contract.domain.enumeration.DocumentStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentAttachmentRepository extends JpaRepository<DocumentAttachment, Long> {
    Page<DocumentAttachment> findByContractId(Long contractId, Pageable pageable);

    Page<DocumentAttachment> findByContractPhaseId(Long contractPhaseId, Pageable pageable);

    Page<DocumentAttachment> findAllByContractId(Long contractId,Pageable pageable);
}