package com.contract.web.rest;

import com.contract.repository.QualityImprovementRepository;
import com.contract.service.QualityImprovementService;
import com.contract.service.dto.QualityImprovementDTO;
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
 * REST controller for managing {@link com.contract.domain.QualityImprovement}.
 */
@RestController
@RequestMapping("/api/quality-improvements")
public class QualityImprovementResource {

    private static final Logger LOG = LoggerFactory.getLogger(QualityImprovementResource.class);

    private static final String ENTITY_NAME = "qualityImprovement";

    @Value("${spring.application.name}")
    private String applicationName;

    private final QualityImprovementService qualityImprovementService;

    private final QualityImprovementRepository qualityImprovementRepository;

    public QualityImprovementResource(
        QualityImprovementService qualityImprovementService,
        QualityImprovementRepository qualityImprovementRepository
    ) {
        this.qualityImprovementService = qualityImprovementService;
        this.qualityImprovementRepository = qualityImprovementRepository;
    }

    /**
     * {@code POST  /quality-improvements} : Create a new qualityImprovement.
     *
     * @param qualityImprovementDTO the qualityImprovementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityImprovementDTO, or with status {@code 400 (Bad Request)} if the qualityImprovement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityImprovementDTO> createQualityImprovement(@Valid @RequestBody QualityImprovementDTO qualityImprovementDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save QualityImprovement : {}", qualityImprovementDTO);
        if (qualityImprovementDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualityImprovement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityImprovementDTO = qualityImprovementService.save(qualityImprovementDTO);
        return ResponseEntity.created(new URI("/api/quality-improvements/" + qualityImprovementDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, qualityImprovementDTO.getId().toString()))
            .body(qualityImprovementDTO);
    }

    /**
     * {@code PUT  /quality-improvements/:id} : Updates an existing qualityImprovement.
     *
     * @param id the id of the qualityImprovementDTO to save.
     * @param qualityImprovementDTO the qualityImprovementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityImprovementDTO,
     * or with status {@code 400 (Bad Request)} if the qualityImprovementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityImprovementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityImprovementDTO> updateQualityImprovement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody QualityImprovementDTO qualityImprovementDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update QualityImprovement : {}, {}", id, qualityImprovementDTO);
        if (qualityImprovementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityImprovementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityImprovementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityImprovementDTO = qualityImprovementService.update(qualityImprovementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityImprovementDTO.getId().toString()))
            .body(qualityImprovementDTO);
    }

    /**
     * {@code PATCH  /quality-improvements/:id} : Partial updates given fields of an existing qualityImprovement, field will ignore if it is null
     *
     * @param id the id of the qualityImprovementDTO to save.
     * @param qualityImprovementDTO the qualityImprovementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityImprovementDTO,
     * or with status {@code 400 (Bad Request)} if the qualityImprovementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the qualityImprovementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityImprovementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityImprovementDTO> partialUpdateQualityImprovement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody QualityImprovementDTO qualityImprovementDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update QualityImprovement partially : {}, {}", id, qualityImprovementDTO);
        if (qualityImprovementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityImprovementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityImprovementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityImprovementDTO> result = qualityImprovementService.partialUpdate(qualityImprovementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityImprovementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /quality-improvements} : get all the qualityImprovements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityImprovements in body.
     */
    @GetMapping("")
    public ResponseEntity<List<QualityImprovementDTO>> getAllQualityImprovements(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of QualityImprovements");
        Page<QualityImprovementDTO> page = qualityImprovementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quality-improvements/:id} : get the "id" qualityImprovement.
     *
     * @param id the id of the qualityImprovementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityImprovementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityImprovementDTO> getQualityImprovement(@PathVariable("id") Long id) {
        LOG.debug("REST request to get QualityImprovement : {}", id);
        Optional<QualityImprovementDTO> qualityImprovementDTO = qualityImprovementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityImprovementDTO);
    }

    /**
     * {@code DELETE  /quality-improvements/:id} : delete the "id" qualityImprovement.
     *
     * @param id the id of the qualityImprovementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityImprovement(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete QualityImprovement : {}", id);
        qualityImprovementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
