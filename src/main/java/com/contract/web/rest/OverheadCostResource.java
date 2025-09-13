package com.contract.web.rest;

import com.contract.repository.OverheadCostRepository;
import com.contract.service.OverheadCostService;
import com.contract.service.dto.OverheadCostDTO;
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
 * REST controller for managing {@link com.contract.domain.OverheadCost}.
 */
@RestController
@RequestMapping("/api/overhead-costs")
public class OverheadCostResource {

    private static final Logger LOG = LoggerFactory.getLogger(OverheadCostResource.class);

    private static final String ENTITY_NAME = "overheadCost";

    @Value("${spring.application.name}")
    private String applicationName;

    private final OverheadCostService overheadCostService;

    private final OverheadCostRepository overheadCostRepository;

    public OverheadCostResource(OverheadCostService overheadCostService, OverheadCostRepository overheadCostRepository) {
        this.overheadCostService = overheadCostService;
        this.overheadCostRepository = overheadCostRepository;
    }

    /**
     * {@code POST  /overhead-costs} : Create a new overheadCost.
     *
     * @param overheadCostDTO the overheadCostDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new overheadCostDTO, or with status {@code 400 (Bad Request)} if the overheadCost has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OverheadCostDTO> createOverheadCost(@Valid @RequestBody OverheadCostDTO overheadCostDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save OverheadCost : {}", overheadCostDTO);
        if (overheadCostDTO.getId() != null) {
            throw new BadRequestAlertException("A new overheadCost cannot already have an ID", ENTITY_NAME, "idexists");
        }
        overheadCostDTO = overheadCostService.save(overheadCostDTO);
        return ResponseEntity.created(new URI("/api/overhead-costs/" + overheadCostDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, overheadCostDTO.getId().toString()))
            .body(overheadCostDTO);
    }

    /**
     * {@code PUT  /overhead-costs/:id} : Updates an existing overheadCost.
     *
     * @param id the id of the overheadCostDTO to save.
     * @param overheadCostDTO the overheadCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated overheadCostDTO,
     * or with status {@code 400 (Bad Request)} if the overheadCostDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the overheadCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OverheadCostDTO> updateOverheadCost(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OverheadCostDTO overheadCostDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update OverheadCost : {}, {}", id, overheadCostDTO);
        if (overheadCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, overheadCostDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!overheadCostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        overheadCostDTO = overheadCostService.update(overheadCostDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, overheadCostDTO.getId().toString()))
            .body(overheadCostDTO);
    }

    /**
     * {@code PATCH  /overhead-costs/:id} : Partial updates given fields of an existing overheadCost, field will ignore if it is null
     *
     * @param id the id of the overheadCostDTO to save.
     * @param overheadCostDTO the overheadCostDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated overheadCostDTO,
     * or with status {@code 400 (Bad Request)} if the overheadCostDTO is not valid,
     * or with status {@code 404 (Not Found)} if the overheadCostDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the overheadCostDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OverheadCostDTO> partialUpdateOverheadCost(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OverheadCostDTO overheadCostDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update OverheadCost partially : {}, {}", id, overheadCostDTO);
        if (overheadCostDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, overheadCostDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!overheadCostRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OverheadCostDTO> result = overheadCostService.partialUpdate(overheadCostDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, overheadCostDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /overhead-costs} : get all the overheadCosts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of overheadCosts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<OverheadCostDTO>> getAllOverheadCosts(Pageable pageable) {
        LOG.debug("REST request to get a page of OverheadCosts");
        Page<OverheadCostDTO> page = overheadCostService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /overhead-costs/:id} : get the "id" overheadCost.
     *
     * @param id the id of the overheadCostDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the overheadCostDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OverheadCostDTO> getOverheadCost(@PathVariable("id") Long id) {
        LOG.debug("REST request to get OverheadCost : {}", id);
        Optional<OverheadCostDTO> overheadCostDTO = overheadCostService.findOne(id);
        return ResponseUtil.wrapOrNotFound(overheadCostDTO);
    }

    /**
     * {@code DELETE  /overhead-costs/:id} : delete the "id" overheadCost.
     *
     * @param id the id of the overheadCostDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOverheadCost(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete OverheadCost : {}", id);
        overheadCostService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
