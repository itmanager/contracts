package com.contract.web.rest;

import com.contract.repository.WorkTimeEntryRepository;
import com.contract.service.WorkTimeEntryService;
import com.contract.service.dto.WorkTimeEntryDTO;
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
 * REST controller for managing {@link com.contract.domain.WorkTimeEntry}.
 */
@RestController
@RequestMapping("/api/work-time-entries")
public class WorkTimeEntryResource {

    private static final Logger LOG = LoggerFactory.getLogger(WorkTimeEntryResource.class);

    private static final String ENTITY_NAME = "workTimeEntry";

    @Value("${spring.application.name}")
    private String applicationName;

    private final WorkTimeEntryService workTimeEntryService;

    private final WorkTimeEntryRepository workTimeEntryRepository;

    public WorkTimeEntryResource(WorkTimeEntryService workTimeEntryService, WorkTimeEntryRepository workTimeEntryRepository) {
        this.workTimeEntryService = workTimeEntryService;
        this.workTimeEntryRepository = workTimeEntryRepository;
    }

    /**
     * {@code POST  /work-time-entries} : Create a new workTimeEntry.
     *
     * @param workTimeEntryDTO the workTimeEntryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workTimeEntryDTO, or with status {@code 400 (Bad Request)} if the workTimeEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<WorkTimeEntryDTO> createWorkTimeEntry(@Valid @RequestBody WorkTimeEntryDTO workTimeEntryDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save WorkTimeEntry : {}", workTimeEntryDTO);
        if (workTimeEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new workTimeEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workTimeEntryDTO = workTimeEntryService.save(workTimeEntryDTO);
        return ResponseEntity.created(new URI("/api/work-time-entries/" + workTimeEntryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, workTimeEntryDTO.getId().toString()))
            .body(workTimeEntryDTO);
    }

    /**
     * {@code PUT  /work-time-entries/:id} : Updates an existing workTimeEntry.
     *
     * @param id the id of the workTimeEntryDTO to save.
     * @param workTimeEntryDTO the workTimeEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workTimeEntryDTO,
     * or with status {@code 400 (Bad Request)} if the workTimeEntryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workTimeEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<WorkTimeEntryDTO> updateWorkTimeEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody WorkTimeEntryDTO workTimeEntryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update WorkTimeEntry : {}, {}", id, workTimeEntryDTO);
        if (workTimeEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workTimeEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workTimeEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        workTimeEntryDTO = workTimeEntryService.update(workTimeEntryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workTimeEntryDTO.getId().toString()))
            .body(workTimeEntryDTO);
    }

    /**
     * {@code PATCH  /work-time-entries/:id} : Partial updates given fields of an existing workTimeEntry, field will ignore if it is null
     *
     * @param id the id of the workTimeEntryDTO to save.
     * @param workTimeEntryDTO the workTimeEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workTimeEntryDTO,
     * or with status {@code 400 (Bad Request)} if the workTimeEntryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the workTimeEntryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the workTimeEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<WorkTimeEntryDTO> partialUpdateWorkTimeEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody WorkTimeEntryDTO workTimeEntryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update WorkTimeEntry partially : {}, {}", id, workTimeEntryDTO);
        if (workTimeEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workTimeEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workTimeEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<WorkTimeEntryDTO> result = workTimeEntryService.partialUpdate(workTimeEntryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workTimeEntryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /work-time-entries} : get all the workTimeEntries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workTimeEntries in body.
     */
    @GetMapping("")
    public ResponseEntity<List<WorkTimeEntryDTO>> getAllWorkTimeEntries(Pageable pageable) {
        LOG.debug("REST request to get a page of WorkTimeEntries");
        Page<WorkTimeEntryDTO> page = workTimeEntryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /work-time-entries/:id} : get the "id" workTimeEntry.
     *
     * @param id the id of the workTimeEntryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workTimeEntryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WorkTimeEntryDTO> getWorkTimeEntry(@PathVariable("id") Long id) {
        LOG.debug("REST request to get WorkTimeEntry : {}", id);
        Optional<WorkTimeEntryDTO> workTimeEntryDTO = workTimeEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(workTimeEntryDTO);
    }

    /**
     * {@code DELETE  /work-time-entries/:id} : delete the "id" workTimeEntry.
     *
     * @param id the id of the workTimeEntryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkTimeEntry(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete WorkTimeEntry : {}", id);
        workTimeEntryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
