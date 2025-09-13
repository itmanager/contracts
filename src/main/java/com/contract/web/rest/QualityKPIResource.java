package com.contract.web.rest;

import com.contract.repository.QualityKPIRepository;
import com.contract.service.QualityKPIService;
import com.contract.service.dto.QualityKPIDTO;
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
 * REST controller for managing {@link com.contract.domain.QualityKPI}.
 */
@RestController
@RequestMapping("/api/quality-kpis")
public class QualityKPIResource {

    private static final Logger LOG = LoggerFactory.getLogger(QualityKPIResource.class);

    private static final String ENTITY_NAME = "qualityKPI";

    @Value("${spring.application.name}")
    private String applicationName;

    private final QualityKPIService qualityKPIService;

    private final QualityKPIRepository qualityKPIRepository;

    public QualityKPIResource(QualityKPIService qualityKPIService, QualityKPIRepository qualityKPIRepository) {
        this.qualityKPIService = qualityKPIService;
        this.qualityKPIRepository = qualityKPIRepository;
    }

    /**
     * {@code POST  /quality-kpis} : Create a new qualityKPI.
     *
     * @param qualityKPIDTO the qualityKPIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new qualityKPIDTO, or with status {@code 400 (Bad Request)} if the qualityKPI has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<QualityKPIDTO> createQualityKPI(@Valid @RequestBody QualityKPIDTO qualityKPIDTO) throws URISyntaxException {
        LOG.debug("REST request to save QualityKPI : {}", qualityKPIDTO);
        if (qualityKPIDTO.getId() != null) {
            throw new BadRequestAlertException("A new qualityKPI cannot already have an ID", ENTITY_NAME, "idexists");
        }
        qualityKPIDTO = qualityKPIService.save(qualityKPIDTO);
        return ResponseEntity.created(new URI("/api/quality-kpis/" + qualityKPIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, qualityKPIDTO.getId().toString()))
            .body(qualityKPIDTO);
    }

    /**
     * {@code PUT  /quality-kpis/:id} : Updates an existing qualityKPI.
     *
     * @param id the id of the qualityKPIDTO to save.
     * @param qualityKPIDTO the qualityKPIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityKPIDTO,
     * or with status {@code 400 (Bad Request)} if the qualityKPIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the qualityKPIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualityKPIDTO> updateQualityKPI(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody QualityKPIDTO qualityKPIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update QualityKPI : {}, {}", id, qualityKPIDTO);
        if (qualityKPIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityKPIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityKPIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        qualityKPIDTO = qualityKPIService.update(qualityKPIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityKPIDTO.getId().toString()))
            .body(qualityKPIDTO);
    }

    /**
     * {@code PATCH  /quality-kpis/:id} : Partial updates given fields of an existing qualityKPI, field will ignore if it is null
     *
     * @param id the id of the qualityKPIDTO to save.
     * @param qualityKPIDTO the qualityKPIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated qualityKPIDTO,
     * or with status {@code 400 (Bad Request)} if the qualityKPIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the qualityKPIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the qualityKPIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<QualityKPIDTO> partialUpdateQualityKPI(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody QualityKPIDTO qualityKPIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update QualityKPI partially : {}, {}", id, qualityKPIDTO);
        if (qualityKPIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, qualityKPIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!qualityKPIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<QualityKPIDTO> result = qualityKPIService.partialUpdate(qualityKPIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, qualityKPIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /quality-kpis} : get all the qualityKPIS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of qualityKPIS in body.
     */
    @GetMapping("")
    public ResponseEntity<List<QualityKPIDTO>> getAllQualityKPIS(Pageable pageable) {
        LOG.debug("REST request to get a page of QualityKPIS");
        Page<QualityKPIDTO> page = qualityKPIService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quality-kpis/:id} : get the "id" qualityKPI.
     *
     * @param id the id of the qualityKPIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the qualityKPIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QualityKPIDTO> getQualityKPI(@PathVariable("id") Long id) {
        LOG.debug("REST request to get QualityKPI : {}", id);
        Optional<QualityKPIDTO> qualityKPIDTO = qualityKPIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(qualityKPIDTO);
    }

    /**
     * {@code DELETE  /quality-kpis/:id} : delete the "id" qualityKPI.
     *
     * @param id the id of the qualityKPIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualityKPI(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete QualityKPI : {}", id);
        qualityKPIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
