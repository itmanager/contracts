package com.contract.web.rest;

import com.contract.repository.GanttActivityRepository;
import com.contract.service.GanttActivityService;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.GanttActivityDTO;
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
 * REST controller for managing {@link com.contract.domain.GanttActivity}.
 */
@RestController
@RequestMapping("/api/gantt-activities")
public class GanttActivityResource {

    private static final Logger LOG = LoggerFactory.getLogger(GanttActivityResource.class);

    private static final String ENTITY_NAME = "ganttActivity";

    @Value("${spring.application.name}")
    private String applicationName;

    private final GanttActivityService ganttActivityService;

    private final GanttActivityRepository ganttActivityRepository;

    public GanttActivityResource(GanttActivityService ganttActivityService, GanttActivityRepository ganttActivityRepository) {
        this.ganttActivityService = ganttActivityService;
        this.ganttActivityRepository = ganttActivityRepository;
    }

    /**
     * {@code POST  /gantt-activities} : Create a new ganttActivity.
     *
     * @param ganttActivityDTO the ganttActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ganttActivityDTO, or with status {@code 400 (Bad Request)} if the ganttActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<GanttActivityDTO> createGanttActivity(@Valid @RequestBody GanttActivityDTO ganttActivityDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save GanttActivity : {}", ganttActivityDTO);
        if (ganttActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new ganttActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ganttActivityDTO = ganttActivityService.save(ganttActivityDTO);
        return ResponseEntity.created(new URI("/api/gantt-activities/" + ganttActivityDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ganttActivityDTO.getId().toString()))
            .body(ganttActivityDTO);
    }

    /**
     * {@code PUT  /gantt-activities/:id} : Updates an existing ganttActivity.
     *
     * @param id the id of the ganttActivityDTO to save.
     * @param ganttActivityDTO the ganttActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ganttActivityDTO,
     * or with status {@code 400 (Bad Request)} if the ganttActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ganttActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<GanttActivityDTO> updateGanttActivity(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GanttActivityDTO ganttActivityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update GanttActivity : {}, {}", id, ganttActivityDTO);
        if (ganttActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ganttActivityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ganttActivityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ganttActivityDTO = ganttActivityService.update(ganttActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ganttActivityDTO.getId().toString()))
            .body(ganttActivityDTO);
    }

    /**
     * {@code PATCH  /gantt-activities/:id} : Partial updates given fields of an existing ganttActivity, field will ignore if it is null
     *
     * @param id the id of the ganttActivityDTO to save.
     * @param ganttActivityDTO the ganttActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ganttActivityDTO,
     * or with status {@code 400 (Bad Request)} if the ganttActivityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ganttActivityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ganttActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<GanttActivityDTO> partialUpdateGanttActivity(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GanttActivityDTO ganttActivityDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update GanttActivity partially : {}, {}", id, ganttActivityDTO);
        if (ganttActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ganttActivityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ganttActivityRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<GanttActivityDTO> result = ganttActivityService.partialUpdate(ganttActivityDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ganttActivityDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /gantt-activities} : get all the ganttActivities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ganttActivities in body.
     */
    @GetMapping("")
    public ResponseEntity<List<GanttActivityDTO>> getAllGanttActivities(Pageable pageable) {
        LOG.debug("REST request to get a page of GanttActivities");
        Page<GanttActivityDTO> page = ganttActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gantt-activities/:id} : get the "id" ganttActivity.
     *
     * @param id the id of the ganttActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ganttActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GanttActivityDTO> getGanttActivity(@PathVariable("id") Long id) {
        LOG.debug("REST request to get GanttActivity : {}", id);
        Optional<GanttActivityDTO> ganttActivityDTO = ganttActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ganttActivityDTO);
    }

    /**
     * {@code DELETE  /gantt-activities/:id} : delete the "id" ganttActivity.
     *
     * @param id the id of the ganttActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGanttActivity(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete GanttActivity : {}", id);
        ganttActivityService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /contract-phases/:id} : get the "id" contractPhase.
     *
     * @param contractPhaseIds the id of the contractPhaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPhaseDTO, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/contract-phases")
    public ResponseEntity<List<GanttActivityDTO>> getByContractPhaseIds(@RequestBody  Long[] contractPhaseIds) {
        LOG.debug("REST request to get ContractPhase : {}", contractPhaseIds);
        List<GanttActivityDTO> page = ganttActivityService.findByContractPhaseIds(contractPhaseIds);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /contract-phases/:id} : get the "id" contractPhase.
     *
     * @param contractId the id of the contractPhaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPhaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<GanttActivityDTO>> getContractPhaseByContractId(@PathVariable("contractId") Long contractId) {
        LOG.debug("REST request to get ContractPhase : {}", contractId);
         List<GanttActivityDTO> page = ganttActivityService.findByContractId(contractId);
        return ResponseEntity.ok().body(page);
    }
}
