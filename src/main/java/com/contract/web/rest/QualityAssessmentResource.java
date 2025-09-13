package com.contract.web.rest;

import com.contract.repository.QualityAssessmentRepository;
import com.contract.service.QualityAssessmentService;
import com.contract.service.dto.QualityAssessmentDTO;
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
 * REST controller for managing {@link com.contract.domain.QualityAssessment}.
 */
@RestController
@RequestMapping("/api/quality-assessments")
public class QualityAssessmentResource {

    private static final Logger LOG = LoggerFactory.getLogger(QualityAssessmentResource.class);

    private static final String ENTITY_NAME = "qualityAssessment";

    @Value("${spring.application.name}")
    private String applicationName;

    private final QualityAssessmentService qualityAssessmentService;

    private final QualityAssessmentRepository qualityAssessmentRepository;

    public QualityAssessmentResource(
        QualityAssessmentService qualityAssessmentService,
        QualityAssessmentRepository qualityAssessmentRepository
    ) {
        this.qualityAssessmentService = qualityAssessmentService;
        this.qualityAssessmentRepository = qualityAssessmentRepository;
    }

    /**
     * {@code POST  /quality-assessments} : Create a new qualityAssessment.
     *
     * @param qualityAssessmentDTO the qualityAssessmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityAssessmentDTO, or with status {@code 400 (Bad Request)} if the qualityAssessment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityAssessmentDTO> createQualityAssessment(@Valid @RequestBody QualityAssessmentDTO qualityAssessmentDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save QualityAssessment : {}", qualityAssessmentDTO);
        if (qualityAssessmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualityAssessment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityAssessmentDTO = qualityAssessmentService.save(qualityAssessmentDTO);
        return ResponseEntity.created(new URI("/api/quality-assessments/" + qualityAssessmentDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, qualityAssessmentDTO.getId().toString()))
            .body(qualityAssessmentDTO);
    }

    /**
     * {@code PUT  /quality-assessments/:id} : Updates an existing qualityAssessment.
     *
     * @param id the id of the qualityAssessmentDTO to save.
     * @param qualityAssessmentDTO the qualityAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the qualityAssessmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityAssessmentDTO> updateQualityAssessment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody QualityAssessmentDTO qualityAssessmentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update QualityAssessment : {}, {}", id, qualityAssessmentDTO);
        if (qualityAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityAssessmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityAssessmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityAssessmentDTO = qualityAssessmentService.update(qualityAssessmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityAssessmentDTO.getId().toString()))
            .body(qualityAssessmentDTO);
    }

    /**
     * {@code PATCH  /quality-assessments/:id} : Partial updates given fields of an existing qualityAssessment, field will ignore if it is null
     *
     * @param id the id of the qualityAssessmentDTO to save.
     * @param qualityAssessmentDTO the qualityAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the qualityAssessmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the qualityAssessmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityAssessmentDTO> partialUpdateQualityAssessment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody QualityAssessmentDTO qualityAssessmentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update QualityAssessment partially : {}, {}", id, qualityAssessmentDTO);
        if (qualityAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityAssessmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityAssessmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityAssessmentDTO> result = qualityAssessmentService.partialUpdate(qualityAssessmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityAssessmentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /quality-assessments} : get all the qualityAssessments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityAssessments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<QualityAssessmentDTO>> getAllQualityAssessments(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of QualityAssessments");
        Page<QualityAssessmentDTO> page = qualityAssessmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quality-assessments/:id} : get the "id" qualityAssessment.
     *
     * @param id the id of the qualityAssessmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityAssessmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityAssessmentDTO> getQualityAssessment(@PathVariable("id") Long id) {
        LOG.debug("REST request to get QualityAssessment : {}", id);
        Optional<QualityAssessmentDTO> qualityAssessmentDTO = qualityAssessmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityAssessmentDTO);
    }

    /**
     * {@code DELETE  /quality-assessments/:id} : delete the "id" qualityAssessment.
     *
     * @param id the id of the qualityAssessmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityAssessment(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete QualityAssessment : {}", id);
        qualityAssessmentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
