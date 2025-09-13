package com.contract.web.rest;

import com.contract.repository.OutsourcingContractRepository;
import com.contract.service.OutsourcingContractService;
import com.contract.service.dto.OutsourcingContractDTO;
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
 * REST controller for managing {@link com.contract.domain.OutsourcingContract}.
 */
@RestController
@RequestMapping("/api/outsourcing-contracts")
public class OutsourcingContractResource {

    private static final Logger LOG = LoggerFactory.getLogger(OutsourcingContractResource.class);

    private static final String ENTITY_NAME = "outsourcingContract";

    @Value("${spring.application.name}")
    private String applicationName;

    private final OutsourcingContractService outsourcingContractService;

    private final OutsourcingContractRepository outsourcingContractRepository;

    public OutsourcingContractResource(
        OutsourcingContractService outsourcingContractService,
        OutsourcingContractRepository outsourcingContractRepository
    ) {
        this.outsourcingContractService = outsourcingContractService;
        this.outsourcingContractRepository = outsourcingContractRepository;
    }

    /**
     * {@code POST  /outsourcing-contracts} : Create a new outsourcingContract.
     *
     * @param outsourcingContractDTO the outsourcingContractDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outsourcingContractDTO, or with status {@code 400 (Bad Request)} if the outsourcingContract has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OutsourcingContractDTO> createOutsourcingContract(
        @Valid @RequestBody OutsourcingContractDTO outsourcingContractDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to save OutsourcingContract : {}", outsourcingContractDTO);
        if (outsourcingContractDTO.getId() != null) {
            throw new BadRequestAlertException("A new outsourcingContract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        outsourcingContractDTO = outsourcingContractService.save(outsourcingContractDTO);
        return ResponseEntity.created(new URI("/api/outsourcing-contracts/" + outsourcingContractDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, outsourcingContractDTO.getId().toString()))
            .body(outsourcingContractDTO);
    }

    /**
     * {@code PUT  /outsourcing-contracts/:id} : Updates an existing outsourcingContract.
     *
     * @param id the id of the outsourcingContractDTO to save.
     * @param outsourcingContractDTO the outsourcingContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContractDTO,
     * or with status {@code 400 (Bad Request)} if the outsourcingContractDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OutsourcingContractDTO> updateOutsourcingContract(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OutsourcingContractDTO outsourcingContractDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update OutsourcingContract : {}, {}", id, outsourcingContractDTO);
        if (outsourcingContractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContractDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        outsourcingContractDTO = outsourcingContractService.update(outsourcingContractDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, outsourcingContractDTO.getId().toString()))
            .body(outsourcingContractDTO);
    }

    /**
     * {@code PATCH  /outsourcing-contracts/:id} : Partial updates given fields of an existing outsourcingContract, field will ignore if it is null
     *
     * @param id the id of the outsourcingContractDTO to save.
     * @param outsourcingContractDTO the outsourcingContractDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outsourcingContractDTO,
     * or with status {@code 400 (Bad Request)} if the outsourcingContractDTO is not valid,
     * or with status {@code 404 (Not Found)} if the outsourcingContractDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the outsourcingContractDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OutsourcingContractDTO> partialUpdateOutsourcingContract(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OutsourcingContractDTO outsourcingContractDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OutsourcingContract partially : {}, {}", id, outsourcingContractDTO);
        if (outsourcingContractDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outsourcingContractDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!outsourcingContractRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OutsourcingContractDTO> result = outsourcingContractService.partialUpdate(outsourcingContractDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, outsourcingContractDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /outsourcing-contracts} : get all the outsourcingContracts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outsourcingContracts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<OutsourcingContractDTO>> getAllOutsourcingContracts(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of OutsourcingContracts");
        Page<OutsourcingContractDTO> page = outsourcingContractService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /outsourcing-contracts/:id} : get the "id" outsourcingContract.
     *
     * @param id the id of the outsourcingContractDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outsourcingContractDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OutsourcingContractDTO> getOutsourcingContract(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OutsourcingContract : {}", id);
        Optional<OutsourcingContractDTO> outsourcingContractDTO = outsourcingContractService.findOne(id);
        return ResponseUtil.wrapOrNotFound(outsourcingContractDTO);
    }

    /**
     * {@code DELETE  /outsourcing-contracts/:id} : delete the "id" outsourcingContract.
     *
     * @param id the id of the outsourcingContractDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutsourcingContract(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OutsourcingContract : {}", id);
        outsourcingContractService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
