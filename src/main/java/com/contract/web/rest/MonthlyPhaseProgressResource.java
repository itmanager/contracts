package com.contract.web.rest;

import com.contract.repository.MonthlyPhaseProgressRepository;
import com.contract.service.MonthlyPhaseProgressService;
import com.contract.service.dto.MonthlyPhaseProgressDTO;
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
 * REST controller for managing {@link com.contract.domain.MonthlyPhaseProgress}.
 */
@RestController
@RequestMapping("/api/monthly-phase-progresses")
public class MonthlyPhaseProgressResource {

    private static final Logger LOG = LoggerFactory.getLogger(MonthlyPhaseProgressResource.class);

    private static final String ENTITY_NAME = "monthlyPhaseProgress";

    @Value("${spring.application.name}")
    private String applicationName;

    private final MonthlyPhaseProgressService monthlyPhaseProgressService;

    private final MonthlyPhaseProgressRepository monthlyPhaseProgressRepository;

    public MonthlyPhaseProgressResource(
        MonthlyPhaseProgressService monthlyPhaseProgressService,
        MonthlyPhaseProgressRepository monthlyPhaseProgressRepository
    ) {
        this.monthlyPhaseProgressService = monthlyPhaseProgressService;
        this.monthlyPhaseProgressRepository = monthlyPhaseProgressRepository;
    }

    /**
     * {@code POST  /monthly-phase-progresses} : Create a new monthlyPhaseProgress.
     *
     * @param monthlyPhaseProgressDTO the monthlyPhaseProgressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyPhaseProgressDTO, or with status {@code 400 (Bad Request)} if the monthlyPhaseProgress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MonthlyPhaseProgressDTO> createMonthlyPhaseProgress(
        @Valid @RequestBody MonthlyPhaseProgressDTO monthlyPhaseProgressDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save MonthlyPhaseProgress : {}", monthlyPhaseProgressDTO);
        if (monthlyPhaseProgressDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyPhaseProgress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        monthlyPhaseProgressDTO = monthlyPhaseProgressService.save(monthlyPhaseProgressDTO);
        return ResponseEntity.created(new URI("/api/monthly-phase-progresses/" + monthlyPhaseProgressDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, monthlyPhaseProgressDTO.getId().toString()))
            .body(monthlyPhaseProgressDTO);
    }

    /**
     * {@code PUT  /monthly-phase-progresses/:id} : Updates an existing monthlyPhaseProgress.
     *
     * @param id the id of the monthlyPhaseProgressDTO to save.
     * @param monthlyPhaseProgressDTO the monthlyPhaseProgressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPhaseProgressDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPhaseProgressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPhaseProgressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPhaseProgressDTO> updateMonthlyPhaseProgress(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MonthlyPhaseProgressDTO monthlyPhaseProgressDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update MonthlyPhaseProgress : {}, {}", id, monthlyPhaseProgressDTO);
        if (monthlyPhaseProgressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPhaseProgressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPhaseProgressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        monthlyPhaseProgressDTO = monthlyPhaseProgressService.update(monthlyPhaseProgressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPhaseProgressDTO.getId().toString()))
            .body(monthlyPhaseProgressDTO);
    }

    /**
     * {@code PATCH  /monthly-phase-progresses/:id} : Partial updates given fields of an existing monthlyPhaseProgress, field will ignore if it is null
     *
     * @param id the id of the monthlyPhaseProgressDTO to save.
     * @param monthlyPhaseProgressDTO the monthlyPhaseProgressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPhaseProgressDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPhaseProgressDTO is not valid,
     * or with status {@code 404 (Not Found)} if the monthlyPhaseProgressDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPhaseProgressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MonthlyPhaseProgressDTO> partialUpdateMonthlyPhaseProgress(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MonthlyPhaseProgressDTO monthlyPhaseProgressDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MonthlyPhaseProgress partially : {}, {}", id, monthlyPhaseProgressDTO);
        if (monthlyPhaseProgressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPhaseProgressDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPhaseProgressRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MonthlyPhaseProgressDTO> result = monthlyPhaseProgressService.partialUpdate(monthlyPhaseProgressDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPhaseProgressDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /monthly-phase-progresses} : get all the monthlyPhaseProgresses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyPhaseProgresses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MonthlyPhaseProgressDTO>> getAllMonthlyPhaseProgresses(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of MonthlyPhaseProgresses");
        Page<MonthlyPhaseProgressDTO> page = monthlyPhaseProgressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-phase-progresses/:id} : get the "id" monthlyPhaseProgress.
     *
     * @param id the id of the monthlyPhaseProgressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyPhaseProgressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPhaseProgressDTO> getMonthlyPhaseProgress(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MonthlyPhaseProgress : {}", id);
        Optional<MonthlyPhaseProgressDTO> monthlyPhaseProgressDTO = monthlyPhaseProgressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyPhaseProgressDTO);
    }

    /**
     * {@code DELETE  /monthly-phase-progresses/:id} : delete the "id" monthlyPhaseProgress.
     *
     * @param id the id of the monthlyPhaseProgressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyPhaseProgress(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MonthlyPhaseProgress : {}", id);
        monthlyPhaseProgressService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * {@code DELETE  /monthly-phase-progresses/:id} : delete the "id" monthlyPhaseProgress.
     *
     * @param contractId the id of the monthlyPhaseProgressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/delete-contract/{contractId}")
    public ResponseEntity<Void> deleteMonthlyPhaseProgressByContractId(@PathVariable("contractId") Long contractId) {
        LOG.debug("REST request to delete MonthlyPhaseProgress : {}", contractId);
        monthlyPhaseProgressService.deleteByContractId(contractId);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, contractId.toString()))
                .build();
    }

    @PostMapping("/array")
    public void createArrayMonthlyPhaseProgress(
            @RequestBody MonthlyPhaseProgressDTO[] monthlyPhaseProgressDTOs
    ) {
        LOG.debug("REST request to save MonthlyPhaseProgress : {}", monthlyPhaseProgressDTOs);
        for (int i = 0; i < monthlyPhaseProgressDTOs.length; i++) {
            monthlyPhaseProgressService.save(monthlyPhaseProgressDTOs[i]);
        }
    }

    /**
     * {@code GET  /monthly-phase-progresses} : get all the monthlyPhaseProgresses.
     *
     * @param contractId the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyPhaseProgresses in body.
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<MonthlyPhaseProgressDTO>> getAllMonthlyPhaseProgresses(@PathVariable("contractId") Long contractId) {
        LOG.debug("REST request to get a page of MonthlyPhaseProgresses");
        List<MonthlyPhaseProgressDTO> page = monthlyPhaseProgressService.findAllByContractId(contractId);
        return ResponseEntity.ok().body(page);
    }
}
