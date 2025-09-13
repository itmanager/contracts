package com.contract.web.rest;

import com.contract.repository.PaymentRequestRepository;
import com.contract.service.PaymentRequestService;
import com.contract.service.dto.PaymentRequestDTO;
import com.contract.web.rest.errors.BadRequestAlertException;
import com.contract.web.utils.HeaderUtil;
import com.contract.web.utils.PaginationUtil;
import com.contract.web.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link com.contract.domain.PaymentRequest}.
 */
@RestController
@RequestMapping("/api/payment-requests")
public class PaymentRequestResource {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentRequestResource.class);

    private static final String ENTITY_NAME = "paymentRequest";

    @Value("${spring.application.name}")
    private String applicationName;

    private final PaymentRequestService paymentRequestService;

    private final PaymentRequestRepository paymentRequestRepository;

    public PaymentRequestResource(PaymentRequestService paymentRequestService, PaymentRequestRepository paymentRequestRepository) {
        this.paymentRequestService = paymentRequestService;
        this.paymentRequestRepository = paymentRequestRepository;
    }

    /**
     * {@code POST  /payment-requests} : Create a new paymentRequest.
     *
     * @param paymentRequestDTO the paymentRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRequestDTO, or with status {@code 400 (Bad Request)} if the paymentRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PaymentRequestDTO> createPaymentRequest(@Valid @RequestBody PaymentRequestDTO paymentRequestDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save PaymentRequest : {}", paymentRequestDTO);
        if (paymentRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new paymentRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentRequestDTO = paymentRequestService.save(paymentRequestDTO);
        return ResponseEntity.created(new URI("/api/payment-requests/" + paymentRequestDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, paymentRequestDTO.getId().toString()))
            .body(paymentRequestDTO);
    }

    /**
     * {@code PUT  /payment-requests/:id} : Updates an existing paymentRequest.
     *
     * @param id the id of the paymentRequestDTO to save.
     * @param paymentRequestDTO the paymentRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRequestDTO,
     * or with status {@code 400 (Bad Request)} if the paymentRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentRequestDTO> updatePaymentRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PaymentRequestDTO paymentRequestDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update PaymentRequest : {}, {}", id, paymentRequestDTO);
        if (paymentRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentRequestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentRequestDTO = paymentRequestService.update(paymentRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentRequestDTO.getId().toString()))
            .body(paymentRequestDTO);
    }

    /**
     * {@code PATCH  /payment-requests/:id} : Partial updates given fields of an existing paymentRequest, field will ignore if it is null
     *
     * @param id the id of the paymentRequestDTO to save.
     * @param paymentRequestDTO the paymentRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRequestDTO,
     * or with status {@code 400 (Bad Request)} if the paymentRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the paymentRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PaymentRequestDTO> partialUpdatePaymentRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PaymentRequestDTO paymentRequestDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update PaymentRequest partially : {}, {}", id, paymentRequestDTO);
        if (paymentRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymentRequestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PaymentRequestDTO> result = paymentRequestService.partialUpdate(paymentRequestDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentRequestDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /payment-requests} : get all the paymentRequests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRequests in body.
     */
    @GetMapping("")
    public ResponseEntity<List<PaymentRequestDTO>> getAllPaymentRequests(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of PaymentRequests");
        Page<PaymentRequestDTO> page = paymentRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-requests/:id} : get the "id" paymentRequest.
     *
     * @param id the id of the paymentRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentRequestDTO> getPaymentRequest(@PathVariable("id") Long id) {
        LOG.debug("REST request to get PaymentRequest : {}", id);
        Optional<PaymentRequestDTO> paymentRequestDTO = paymentRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentRequestDTO);
    }

    /**
     * {@code DELETE  /payment-requests/:id} : delete the "id" paymentRequest.
     *
     * @param id the id of the paymentRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentRequest(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete PaymentRequest : {}", id);
        paymentRequestService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
