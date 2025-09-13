package com.contract.web.rest;

import com.contract.repository.CostItemRepository;
import com.contract.service.CostItemService;
import com.contract.service.dto.CostItemDTO;
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
 * REST controller for managing {@link com.contract.domain.CostItem}.
 */
@RestController
@RequestMapping("/api/cost-items")
public class CostItemResource {

    private static final Logger LOG = LoggerFactory.getLogger(CostItemResource.class);

    private static final String ENTITY_NAME = "costItem";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CostItemService costItemService;

    private final CostItemRepository costItemRepository;

    public CostItemResource(CostItemService costItemService, CostItemRepository costItemRepository) {
        this.costItemService = costItemService;
        this.costItemRepository = costItemRepository;
    }

    /**
     * {@code POST  /cost-items} : Create a new costItem.
     *
     * @param costItemDTO the costItemDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new costItemDTO, or with status {@code 400 (Bad Request)} if the costItem has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CostItemDTO> createCostItem(@Valid @RequestBody CostItemDTO costItemDTO) throws URISyntaxException {
        LOG.debug("REST request to save CostItem : {}", costItemDTO);
        if (costItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new costItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        costItemDTO = costItemService.save(costItemDTO);
        return ResponseEntity.created(new URI("/api/cost-items/" + costItemDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, costItemDTO.getId().toString()))
            .body(costItemDTO);
    }

    /**
     * {@code PUT  /cost-items/:id} : Updates an existing costItem.
     *
     * @param id the id of the costItemDTO to save.
     * @param costItemDTO the costItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costItemDTO,
     * or with status {@code 400 (Bad Request)} if the costItemDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the costItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CostItemDTO> updateCostItem(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CostItemDTO costItemDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CostItem : {}, {}", id, costItemDTO);
        if (costItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        costItemDTO = costItemService.update(costItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costItemDTO.getId().toString()))
            .body(costItemDTO);
    }

    /**
     * {@code PATCH  /cost-items/:id} : Partial updates given fields of an existing costItem, field will ignore if it is null
     *
     * @param id the id of the costItemDTO to save.
     * @param costItemDTO the costItemDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated costItemDTO,
     * or with status {@code 400 (Bad Request)} if the costItemDTO is not valid,
     * or with status {@code 404 (Not Found)} if the costItemDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the costItemDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CostItemDTO> partialUpdateCostItem(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CostItemDTO costItemDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CostItem partially : {}, {}", id, costItemDTO);
        if (costItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, costItemDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!costItemRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CostItemDTO> result = costItemService.partialUpdate(costItemDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, costItemDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /cost-items} : get all the costItems.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of costItems in body.
     */
    @GetMapping("")
    public ResponseEntity<List<CostItemDTO>> getAllCostItems(Pageable pageable) {
        LOG.debug("REST request to get a page of CostItems");
        Page<CostItemDTO> page = costItemService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cost-items/:id} : get the "id" costItem.
     *
     * @param id the id of the costItemDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the costItemDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CostItemDTO> getCostItem(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CostItem : {}", id);
        Optional<CostItemDTO> costItemDTO = costItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(costItemDTO);
    }

    /**
     * {@code DELETE  /cost-items/:id} : delete the "id" costItem.
     *
     * @param id the id of the costItemDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCostItem(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CostItem : {}", id);
        costItemService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
