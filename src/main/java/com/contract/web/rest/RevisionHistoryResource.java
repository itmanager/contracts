package com.contract.web.rest;

import com.contract.repository.RevisionHistoryRepository;
import com.contract.service.RevisionHistoryService;
import com.contract.service.dto.RevisionHistoryDTO;
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
 * REST controller for managing {@link com.contract.domain.RevisionHistory}.
 */
@RestController
@RequestMapping("/api/revision-histories")
public class RevisionHistoryResource {

    private static final Logger LOG = LoggerFactory.getLogger(RevisionHistoryResource.class);

    private static final String ENTITY_NAME = "revisionHistory";

    @Value("${spring.application.name}")
    private String applicationName;

    private final RevisionHistoryService revisionHistoryService;

    private final RevisionHistoryRepository revisionHistoryRepository;

    public RevisionHistoryResource(RevisionHistoryService revisionHistoryService, RevisionHistoryRepository revisionHistoryRepository) {
        this.revisionHistoryService = revisionHistoryService;
        this.revisionHistoryRepository = revisionHistoryRepository;
    }

    /**
     * {@code POST  /revision-histories} : Create a new revisionHistory.
     *
     * @param revisionHistoryDTO the revisionHistoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new revisionHistoryDTO, or with status {@code 400 (Bad Request)} if the revisionHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<RevisionHistoryDTO> createRevisionHistory(@Valid @RequestBody RevisionHistoryDTO revisionHistoryDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save RevisionHistory : {}", revisionHistoryDTO);
        if (revisionHistoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new revisionHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        revisionHistoryDTO = revisionHistoryService.save(revisionHistoryDTO);
        return ResponseEntity.created(new URI("/api/revision-histories/" + revisionHistoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, revisionHistoryDTO.getId().toString()))
            .body(revisionHistoryDTO);
    }

    /**
     * {@code PUT  /revision-histories/:id} : Updates an existing revisionHistory.
     *
     * @param id the id of the revisionHistoryDTO to save.
     * @param revisionHistoryDTO the revisionHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated revisionHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the revisionHistoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the revisionHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RevisionHistoryDTO> updateRevisionHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RevisionHistoryDTO revisionHistoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update RevisionHistory : {}, {}", id, revisionHistoryDTO);
        if (revisionHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, revisionHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!revisionHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        revisionHistoryDTO = revisionHistoryService.update(revisionHistoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, revisionHistoryDTO.getId().toString()))
            .body(revisionHistoryDTO);
    }

    /**
     * {@code PATCH  /revision-histories/:id} : Partial updates given fields of an existing revisionHistory, field will ignore if it is null
     *
     * @param id the id of the revisionHistoryDTO to save.
     * @param revisionHistoryDTO the revisionHistoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated revisionHistoryDTO,
     * or with status {@code 400 (Bad Request)} if the revisionHistoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the revisionHistoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the revisionHistoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RevisionHistoryDTO> partialUpdateRevisionHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RevisionHistoryDTO revisionHistoryDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update RevisionHistory partially : {}, {}", id, revisionHistoryDTO);
        if (revisionHistoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, revisionHistoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!revisionHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RevisionHistoryDTO> result = revisionHistoryService.partialUpdate(revisionHistoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, revisionHistoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /revision-histories} : get all the revisionHistories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of revisionHistories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<RevisionHistoryDTO>> getAllRevisionHistories(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of RevisionHistories");
        Page<RevisionHistoryDTO> page = revisionHistoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /revision-histories/:id} : get the "id" revisionHistory.
     *
     * @param id the id of the revisionHistoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the revisionHistoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RevisionHistoryDTO> getRevisionHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to get RevisionHistory : {}", id);
        Optional<RevisionHistoryDTO> revisionHistoryDTO = revisionHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(revisionHistoryDTO);
    }

    /**
     * {@code DELETE  /revision-histories/:id} : delete the "id" revisionHistory.
     *
     * @param id the id of the revisionHistoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevisionHistory(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete RevisionHistory : {}", id);
        revisionHistoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
