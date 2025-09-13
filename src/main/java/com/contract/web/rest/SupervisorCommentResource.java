package com.contract.web.rest;

import com.contract.repository.SupervisorCommentRepository;
import com.contract.service.SupervisorCommentService;
import com.contract.service.dto.SupervisorCommentDTO;
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
 * REST controller for managing {@link com.contract.domain.SupervisorComment}.
 */
@RestController
@RequestMapping("/api/supervisor-comments")
public class SupervisorCommentResource {

    private static final Logger LOG = LoggerFactory.getLogger(SupervisorCommentResource.class);

    private static final String ENTITY_NAME = "supervisorComment";

    @Value("${spring.application.name}")
    private String applicationName;

    private final SupervisorCommentService supervisorCommentService;

    private final SupervisorCommentRepository supervisorCommentRepository;

    public SupervisorCommentResource(
        SupervisorCommentService supervisorCommentService,
        SupervisorCommentRepository supervisorCommentRepository
    ) {
        this.supervisorCommentService = supervisorCommentService;
        this.supervisorCommentRepository = supervisorCommentRepository;
    }

    /**
     * {@code POST  /supervisor-comments} : Create a new supervisorComment.
     *
     * @param supervisorCommentDTO the supervisorCommentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new supervisorCommentDTO, or with status {@code 400 (Bad Request)} if the supervisorComment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SupervisorCommentDTO> createSupervisorComment(@Valid @RequestBody SupervisorCommentDTO supervisorCommentDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save SupervisorComment : {}", supervisorCommentDTO);
        if (supervisorCommentDTO.getId() != null) {
            throw new BadRequestAlertException("A new supervisorComment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        supervisorCommentDTO = supervisorCommentService.save(supervisorCommentDTO);
        return ResponseEntity.created(new URI("/api/supervisor-comments/" + supervisorCommentDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, supervisorCommentDTO.getId().toString()))
            .body(supervisorCommentDTO);
    }

    /**
     * {@code PUT  /supervisor-comments/:id} : Updates an existing supervisorComment.
     *
     * @param id the id of the supervisorCommentDTO to save.
     * @param supervisorCommentDTO the supervisorCommentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supervisorCommentDTO,
     * or with status {@code 400 (Bad Request)} if the supervisorCommentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the supervisorCommentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SupervisorCommentDTO> updateSupervisorComment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SupervisorCommentDTO supervisorCommentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update SupervisorComment : {}, {}", id, supervisorCommentDTO);
        if (supervisorCommentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supervisorCommentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supervisorCommentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        supervisorCommentDTO = supervisorCommentService.update(supervisorCommentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supervisorCommentDTO.getId().toString()))
            .body(supervisorCommentDTO);
    }

    /**
     * {@code PATCH  /supervisor-comments/:id} : Partial updates given fields of an existing supervisorComment, field will ignore if it is null
     *
     * @param id the id of the supervisorCommentDTO to save.
     * @param supervisorCommentDTO the supervisorCommentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated supervisorCommentDTO,
     * or with status {@code 400 (Bad Request)} if the supervisorCommentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the supervisorCommentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the supervisorCommentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SupervisorCommentDTO> partialUpdateSupervisorComment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SupervisorCommentDTO supervisorCommentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update SupervisorComment partially : {}, {}", id, supervisorCommentDTO);
        if (supervisorCommentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, supervisorCommentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!supervisorCommentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SupervisorCommentDTO> result = supervisorCommentService.partialUpdate(supervisorCommentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, supervisorCommentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /supervisor-comments} : get all the supervisorComments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of supervisorComments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<SupervisorCommentDTO>> getAllSupervisorComments(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of SupervisorComments");
        Page<SupervisorCommentDTO> page = supervisorCommentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /supervisor-comments/:id} : get the "id" supervisorComment.
     *
     * @param id the id of the supervisorCommentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the supervisorCommentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SupervisorCommentDTO> getSupervisorComment(@PathVariable("id") Long id) {
        LOG.debug("REST request to get SupervisorComment : {}", id);
        Optional<SupervisorCommentDTO> supervisorCommentDTO = supervisorCommentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(supervisorCommentDTO);
    }

    /**
     * {@code DELETE  /supervisor-comments/:id} : delete the "id" supervisorComment.
     *
     * @param id the id of the supervisorCommentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupervisorComment(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete SupervisorComment : {}", id);
        supervisorCommentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
