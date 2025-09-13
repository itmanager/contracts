package com.contract.web.rest;

import com.contract.repository.SupervisorRepository;
import com.contract.service.SupervisorService;
import com.contract.service.dto.SupervisorDTO;
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
 * REST controller for managing {@link com.contract.domain.Supervisor}.
 */
@RestController
@RequestMapping("/api/supervisors")
public class SupervisorResource {

    private static final Logger LOG = LoggerFactory.getLogger(SupervisorResource.class);

    private static final String ENTITY_NAME = "supervisor";

    @Value("${spring.application.name}")
    private String applicationName;

    private final SupervisorService supervisorService;

    private final SupervisorRepository supervisorRepository;

    public SupervisorResource(SupervisorService supervisorService, SupervisorRepository supervisorRepository) {
        this.supervisorService = supervisorService;
        this.supervisorRepository = supervisorRepository;
    }

    /**
     * {@code POST  /supervisors} : Create a new supervisor.
     *
     * @param supervisorDTO the supervisorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supervisorDTO, or with status {@code 400 (Bad Request)} if the supervisor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SupervisorDTO> createSupervisor(@Valid @RequestBody SupervisorDTO supervisorDTO) throws URISyntaxException {
        LOG.debug("REST request to save Supervisor : {}", supervisorDTO);
        if (supervisorDTO.getId() != null) {
            throw new BadRequestAlertException("A new supervisor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        supervisorDTO = supervisorService.save(supervisorDTO);
        return ResponseEntity.created(new URI("/api/supervisors/" + supervisorDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, supervisorDTO.getId().toString()))
            .body(supervisorDTO);
    }

    /**
     * {@code PUT  /supervisors/:id} : Updates an existing supervisor.
     *
     * @param id the id of the supervisorDTO to save.
     * @param supervisorDTO the supervisorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supervisorDTO,
     * or with status {@code 400 (Bad Request)} if the supervisorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supervisorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SupervisorDTO> updateSupervisor(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SupervisorDTO supervisorDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Supervisor : {}, {}", id, supervisorDTO);
        if (supervisorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supervisorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supervisorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        supervisorDTO = supervisorService.update(supervisorDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supervisorDTO.getId().toString()))
            .body(supervisorDTO);
    }

    /**
     * {@code PATCH  /supervisors/:id} : Partial updates given fields of an existing supervisor, field will ignore if it is null
     *
     * @param id the id of the supervisorDTO to save.
     * @param supervisorDTO the supervisorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supervisorDTO,
     * or with status {@code 400 (Bad Request)} if the supervisorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the supervisorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the supervisorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SupervisorDTO> partialUpdateSupervisor(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SupervisorDTO supervisorDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Supervisor partially : {}, {}", id, supervisorDTO);
        if (supervisorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supervisorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supervisorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SupervisorDTO> result = supervisorService.partialUpdate(supervisorDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supervisorDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /supervisors} : get all the supervisors.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supervisors in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SupervisorDTO>> getAllSupervisors(Pageable pageable) {
        LOG.debug("REST request to get a page of Supervisors");
        Page<SupervisorDTO> page = supervisorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supervisors/:id} : get the "id" supervisor.
     *
     * @param id the id of the supervisorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supervisorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SupervisorDTO> getSupervisor(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Supervisor : {}", id);
        Optional<SupervisorDTO> supervisorDTO = supervisorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supervisorDTO);
    }

    /**
     * {@code DELETE  /supervisors/:id} : delete the "id" supervisor.
     *
     * @param id the id of the supervisorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupervisor(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Supervisor : {}", id);
        supervisorService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
