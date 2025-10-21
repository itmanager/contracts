package com.contract.web.rest;

import com.contract.repository.BudgetAllocationRepository;
import com.contract.service.BudgetAllocationService;
import com.contract.service.dto.BudgetAllocationDTO;
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
 * REST controller for managing {@link com.contract.domain.BudgetAllocation}.
 */
@RestController
@RequestMapping("/api/budget-allocations")
public class BudgetAllocationResource {

    private static final Logger LOG = LoggerFactory.getLogger(BudgetAllocationResource.class);

    private static final String ENTITY_NAME = "budgetAllocation";

    @Value("${spring.application.name}")
    private String applicationName;

    private final BudgetAllocationService budgetAllocationService;

    private final BudgetAllocationRepository budgetAllocationRepository;

    public BudgetAllocationResource(
        BudgetAllocationService budgetAllocationService,
        BudgetAllocationRepository budgetAllocationRepository
    ) {
        this.budgetAllocationService = budgetAllocationService;
        this.budgetAllocationRepository = budgetAllocationRepository;
    }

    /**
     * {@code POST  /budget-allocations} : Create a new budgetAllocation.
     *
     * @param budgetAllocationDTO the budgetAllocationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new budgetAllocationDTO, or with status {@code 400 (Bad Request)} if the budgetAllocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BudgetAllocationDTO> createBudgetAllocation(@Valid @RequestBody BudgetAllocationDTO budgetAllocationDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save BudgetAllocation : {}", budgetAllocationDTO);
        if (budgetAllocationDTO.getId() != null) {
            throw new BadRequestAlertException("A new budgetAllocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        budgetAllocationDTO = budgetAllocationService.save(budgetAllocationDTO);
        return ResponseEntity.created(new URI("/api/budget-allocations/" + budgetAllocationDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, budgetAllocationDTO.getId().toString()))
            .body(budgetAllocationDTO);
    }

    /**
     * {@code PUT  /budget-allocations/:id} : Updates an existing budgetAllocation.
     *
     * @param id the id of the budgetAllocationDTO to save.
     * @param budgetAllocationDTO the budgetAllocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated budgetAllocationDTO,
     * or with status {@code 400 (Bad Request)} if the budgetAllocationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the budgetAllocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BudgetAllocationDTO> updateBudgetAllocation(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BudgetAllocationDTO budgetAllocationDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update BudgetAllocation : {}, {}", id, budgetAllocationDTO);
        if (budgetAllocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, budgetAllocationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!budgetAllocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        budgetAllocationDTO = budgetAllocationService.update(budgetAllocationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, budgetAllocationDTO.getId().toString()))
            .body(budgetAllocationDTO);
    }

    /**
     * {@code PATCH  /budget-allocations/:id} : Partial updates given fields of an existing budgetAllocation, field will ignore if it is null
     *
     * @param id the id of the budgetAllocationDTO to save.
     * @param budgetAllocationDTO the budgetAllocationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated budgetAllocationDTO,
     * or with status {@code 400 (Bad Request)} if the budgetAllocationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the budgetAllocationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the budgetAllocationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BudgetAllocationDTO> partialUpdateBudgetAllocation(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BudgetAllocationDTO budgetAllocationDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update BudgetAllocation partially : {}, {}", id, budgetAllocationDTO);
        if (budgetAllocationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, budgetAllocationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!budgetAllocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BudgetAllocationDTO> result = budgetAllocationService.partialUpdate(budgetAllocationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, budgetAllocationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /budget-allocations} : get all the budgetAllocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of budgetAllocations in body.
     */
    @GetMapping("")
    public ResponseEntity<List<BudgetAllocationDTO>> getAllBudgetAllocations(Pageable pageable) {
        LOG.debug("REST request to get a page of BudgetAllocations");
        Page<BudgetAllocationDTO> page = budgetAllocationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /budget-allocations/:id} : get the "id" budgetAllocation.
     *
     * @param id the id of the budgetAllocationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the budgetAllocationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BudgetAllocationDTO> getBudgetAllocation(@PathVariable("id") Long id) {
        LOG.debug("REST request to get BudgetAllocation : {}", id);
        Optional<BudgetAllocationDTO> budgetAllocationDTO = budgetAllocationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(budgetAllocationDTO);
    }

    /**
     * {@code DELETE  /budget-allocations/:id} : delete the "id" budgetAllocation.
     *
     * @param id the id of the budgetAllocationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudgetAllocation(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete BudgetAllocation : {}", id);
        budgetAllocationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * {@code GET  /budget-allocations} : get all the budgetAllocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of budgetAllocations in body.
     */
    @GetMapping("/contractPhase/{contractPhaseId}")
    public ResponseEntity<List<BudgetAllocationDTO>> getAllBudgetAllocationsByContractPhaseId(@PathVariable("contractPhaseId") Long contractPhaseId) {
        LOG.debug("REST request to get a page of BudgetAllocations");
        List<BudgetAllocationDTO> page = budgetAllocationService.findByContractPhaseId(contractPhaseId);
        return ResponseEntity.ok().body(page);
    }
}
