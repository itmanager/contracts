package com.contract.web.rest;

import com.contract.repository.CostCategoryRepository;
import com.contract.service.CostCategoryService;
import com.contract.service.dto.CostCategoryDTO;
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
 * REST controller for managing {@link com.contract.domain.CostCategory}.
 */
@RestController
@RequestMapping("/api/cost-categories")
public class CostCategoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(CostCategoryResource.class);

    private static final String ENTITY_NAME = "costCategory";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CostCategoryService costCategoryService;

    private final CostCategoryRepository costCategoryRepository;

    public CostCategoryResource(CostCategoryService costCategoryService, CostCategoryRepository costCategoryRepository) {
        this.costCategoryService = costCategoryService;
        this.costCategoryRepository = costCategoryRepository;
    }

    /**
     * {@code POST  /cost-categories} : Create a new costCategory.
     *
     * @param costCategoryDTO the costCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costCategoryDTO, or with status {@code 400 (Bad Request)} if the costCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CostCategoryDTO> createCostCategory(@Valid @RequestBody CostCategoryDTO costCategoryDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save CostCategory : {}", costCategoryDTO);
        if (costCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new costCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costCategoryDTO = costCategoryService.save(costCategoryDTO);
        return ResponseEntity.created(new URI("/api/cost-categories/" + costCategoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, costCategoryDTO.getId().toString()))
            .body(costCategoryDTO);
    }

    /**
     * {@code PUT  /cost-categories/:id} : Updates an existing costCategory.
     *
     * @param id the id of the costCategoryDTO to save.
     * @param costCategoryDTO the costCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the costCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CostCategoryDTO> updateCostCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CostCategoryDTO costCategoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CostCategory : {}, {}", id, costCategoryDTO);
        if (costCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        costCategoryDTO = costCategoryService.update(costCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costCategoryDTO.getId().toString()))
            .body(costCategoryDTO);
    }

    /**
     * {@code PATCH  /cost-categories/:id} : Partial updates given fields of an existing costCategory, field will ignore if it is null
     *
     * @param id the id of the costCategoryDTO to save.
     * @param costCategoryDTO the costCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the costCategoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the costCategoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the costCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CostCategoryDTO> partialUpdateCostCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CostCategoryDTO costCategoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CostCategory partially : {}, {}", id, costCategoryDTO);
        if (costCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CostCategoryDTO> result = costCategoryService.partialUpdate(costCategoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costCategoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cost-categories} : get all the costCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costCategories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CostCategoryDTO>> getAllCostCategories(Pageable pageable) {
        LOG.debug("REST request to get a page of CostCategories");
        Page<CostCategoryDTO> page = costCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cost-categories/:id} : get the "id" costCategory.
     *
     * @param id the id of the costCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CostCategoryDTO> getCostCategory(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CostCategory : {}", id);
        Optional<CostCategoryDTO> costCategoryDTO = costCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(costCategoryDTO);
    }

    /**
     * {@code DELETE  /cost-categories/:id} : delete the "id" costCategory.
     *
     * @param id the id of the costCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostCategory(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CostCategory : {}", id);
        costCategoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
