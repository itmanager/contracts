package com.contract.repository;

import com.contract.domain.PaymentRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PaymentRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {}
