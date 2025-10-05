package com.contract.web.rest;

import com.contract.repository.ContractPhaseRepository;
import com.contract.service.ContractPhaseService;
import com.contract.service.dto.ContractPhaseDTO;
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
 * REST controller for managing {@link com.contract.domain.ContractPhase}.
 */
@RestController
@RequestMapping("/api/contract-phases")
public class ContractPhaseResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContractPhaseResource.class);

    private static final String ENTITY_NAME = "contractPhase";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ContractPhaseService contractPhaseService;

    private final ContractPhaseRepository contractPhaseRepository;

    public ContractPhaseResource(ContractPhaseService contractPhaseService, ContractPhaseRepository contractPhaseRepository) {
        this.contractPhaseService = contractPhaseService;
        this.contractPhaseRepository = contractPhaseRepository;
    }

    /**
     * {@code POST  /contract-phases} : Create a new contractPhase.
     *
     * @param contractPhaseDTO the contractPhaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractPhaseDTO, or with status {@code 400 (Bad Request)} if the contractPhase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContractPhaseDTO> createContractPhase(@Valid @RequestBody ContractPhaseDTO contractPhaseDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save ContractPhase : {}", contractPhaseDTO);
        if (contractPhaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new contractPhase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractPhaseDTO = contractPhaseService.save(contractPhaseDTO);
        return ResponseEntity.created(new URI("/api/contract-phases/" + contractPhaseDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, contractPhaseDTO.getId().toString()))
            .body(contractPhaseDTO);
    }

    /**
     * {@code PUT  /contract-phases/:id} : Updates an existing contractPhase.
     *
     * @param id the id of the contractPhaseDTO to save.
     * @param contractPhaseDTO the contractPhaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPhaseDTO,
     * or with status {@code 400 (Bad Request)} if the contractPhaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractPhaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractPhaseDTO> updateContractPhase(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractPhaseDTO contractPhaseDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ContractPhase : {}, {}", id, contractPhaseDTO);
        if (contractPhaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPhaseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPhaseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractPhaseDTO = contractPhaseService.update(contractPhaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractPhaseDTO.getId().toString()))
            .body(contractPhaseDTO);
    }

    /**
     * {@code PATCH  /contract-phases/:id} : Partial updates given fields of an existing contractPhase, field will ignore if it is null
     *
     * @param id the id of the contractPhaseDTO to save.
     * @param contractPhaseDTO the contractPhaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPhaseDTO,
     * or with status {@code 400 (Bad Request)} if the contractPhaseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractPhaseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractPhaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractPhaseDTO> partialUpdateContractPhase(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractPhaseDTO contractPhaseDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ContractPhase partially : {}, {}", id, contractPhaseDTO);
        if (contractPhaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPhaseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPhaseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractPhaseDTO> result = contractPhaseService.partialUpdate(contractPhaseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractPhaseDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-phases} : get all the contractPhases.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractPhases in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ContractPhaseDTO>> getAllContractPhases(Pageable pageable) {
        LOG.debug("REST request to get a page of ContractPhases");
        Page<ContractPhaseDTO> page = contractPhaseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contract-phases/:id} : get the "id" contractPhase.
     *
     * @param id the id of the contractPhaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPhaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractPhaseDTO> getContractPhase(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ContractPhase : {}", id);
        Optional<ContractPhaseDTO> contractPhaseDTO = contractPhaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractPhaseDTO);
    }

    /**
     * {@code DELETE  /contract-phases/:id} : delete the "id" contractPhase.
     *
     * @param id the id of the contractPhaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractPhase(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ContractPhase : {}", id);
        contractPhaseService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * {@code GET  /contract-phases/:id} : get the "id" contractPhase.
     *
     * @param contractId the id of the contractPhaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPhaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<ContractPhaseDTO>> getContractPhaseByContractId(@PathVariable("contractId") Long contractId) {
        LOG.debug("REST request to get ContractPhase : {}", contractId);
        List<ContractPhaseDTO> page = contractPhaseService.findByContractId(contractId);
        return ResponseEntity.ok().body(page);
    }
}
