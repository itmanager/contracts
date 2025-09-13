package com.contract.service;

import com.contract.domain.PaymentRequest;
import com.contract.repository.PaymentRequestRepository;
import com.contract.service.dto.PaymentRequestDTO;
import com.contract.service.mapper.PaymentRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PaymentRequest}.
 */
@Service
@Transactional
public class PaymentRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestService.class);

    private final PaymentRequestRepository paymentRequestRepository;

    private final PaymentRequestMapper paymentRequestMapper;

    public PaymentRequestService(PaymentRequestRepository paymentRequestRepository, PaymentRequestMapper paymentRequestMapper) {
        this.paymentRequestRepository = paymentRequestRepository;
        this.paymentRequestMapper = paymentRequestMapper;
    }

    /**
     * Save a paymentRequest.
     *
     * @param paymentRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentRequestDTO save(PaymentRequestDTO paymentRequestDTO) {
        LOG.debug("Request to save PaymentRequest : {}", paymentRequestDTO);
        PaymentRequest paymentRequest = paymentRequestMapper.toEntity(paymentRequestDTO);
        paymentRequest = paymentRequestRepository.save(paymentRequest);
        return paymentRequestMapper.toDto(paymentRequest);
    }

    /**
     * Update a paymentRequest.
     *
     * @param paymentRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentRequestDTO update(PaymentRequestDTO paymentRequestDTO) {
        LOG.debug("Request to update PaymentRequest : {}", paymentRequestDTO);
        PaymentRequest paymentRequest = paymentRequestMapper.toEntity(paymentRequestDTO);
        paymentRequest = paymentRequestRepository.save(paymentRequest);
        return paymentRequestMapper.toDto(paymentRequest);
    }

    /**
     * Partially update a paymentRequest.
     *
     * @param paymentRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PaymentRequestDTO> partialUpdate(PaymentRequestDTO paymentRequestDTO) {
        LOG.debug("Request to partially update PaymentRequest : {}", paymentRequestDTO);

        return paymentRequestRepository
            .findById(paymentRequestDTO.getId())
            .map(existingPaymentRequest -> {
                paymentRequestMapper.partialUpdate(existingPaymentRequest, paymentRequestDTO);

                return existingPaymentRequest;
            })
            .map(paymentRequestRepository::save)
            .map(paymentRequestMapper::toDto);
    }

    /**
     * Get all the paymentRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentRequestDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all PaymentRequests");
        return paymentRequestRepository.findAll(pageable).map(paymentRequestMapper::toDto);
    }

    /**
     * Get one paymentRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentRequestDTO> findOne(Long id) {
        LOG.debug("Request to get PaymentRequest : {}", id);
        return paymentRequestRepository.findById(id).map(paymentRequestMapper::toDto);
    }

    /**
     * Delete the paymentRequest by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete PaymentRequest : {}", id);
        paymentRequestRepository.deleteById(id);
    }
}
