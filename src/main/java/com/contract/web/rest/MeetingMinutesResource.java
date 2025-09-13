package com.contract.web.rest;

import com.contract.repository.MeetingMinutesRepository;
import com.contract.service.MeetingMinutesService;
import com.contract.service.dto.MeetingMinutesDTO;
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
 * REST controller for managing {@link com.contract.domain.MeetingMinutes}.
 */
@RestController
@RequestMapping("/api/meeting-minutes")
public class MeetingMinutesResource {

    private static final Logger LOG = LoggerFactory.getLogger(MeetingMinutesResource.class);

    private static final String ENTITY_NAME = "meetingMinutes";

    @Value("${spring.application.name}")
    private String applicationName;

    private final MeetingMinutesService meetingMinutesService;

    private final MeetingMinutesRepository meetingMinutesRepository;

    public MeetingMinutesResource(MeetingMinutesService meetingMinutesService, MeetingMinutesRepository meetingMinutesRepository) {
        this.meetingMinutesService = meetingMinutesService;
        this.meetingMinutesRepository = meetingMinutesRepository;
    }

    /**
     * {@code POST  /meeting-minutes} : Create a new meetingMinutes.
     *
     * @param meetingMinutesDTO the meetingMinutesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingMinutesDTO, or with status {@code 400 (Bad Request)} if the meetingMinutes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MeetingMinutesDTO> createMeetingMinutes(@Valid @RequestBody MeetingMinutesDTO meetingMinutesDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save MeetingMinutes : {}", meetingMinutesDTO);
        if (meetingMinutesDTO.getId() != null) {
            throw new BadRequestAlertException("A new meetingMinutes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        meetingMinutesDTO = meetingMinutesService.save(meetingMinutesDTO);
        return ResponseEntity.created(new URI("/api/meeting-minutes/" + meetingMinutesDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, meetingMinutesDTO.getId().toString()))
            .body(meetingMinutesDTO);
    }

    /**
     * {@code PUT  /meeting-minutes/:id} : Updates an existing meetingMinutes.
     *
     * @param id the id of the meetingMinutesDTO to save.
     * @param meetingMinutesDTO the meetingMinutesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingMinutesDTO,
     * or with status {@code 400 (Bad Request)} if the meetingMinutesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingMinutesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MeetingMinutesDTO> updateMeetingMinutes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MeetingMinutesDTO meetingMinutesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update MeetingMinutes : {}, {}", id, meetingMinutesDTO);
        if (meetingMinutesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, meetingMinutesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!meetingMinutesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        meetingMinutesDTO = meetingMinutesService.update(meetingMinutesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingMinutesDTO.getId().toString()))
            .body(meetingMinutesDTO);
    }

    /**
     * {@code PATCH  /meeting-minutes/:id} : Partial updates given fields of an existing meetingMinutes, field will ignore if it is null
     *
     * @param id the id of the meetingMinutesDTO to save.
     * @param meetingMinutesDTO the meetingMinutesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingMinutesDTO,
     * or with status {@code 400 (Bad Request)} if the meetingMinutesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the meetingMinutesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the meetingMinutesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MeetingMinutesDTO> partialUpdateMeetingMinutes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MeetingMinutesDTO meetingMinutesDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update MeetingMinutes partially : {}, {}", id, meetingMinutesDTO);
        if (meetingMinutesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, meetingMinutesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!meetingMinutesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MeetingMinutesDTO> result = meetingMinutesService.partialUpdate(meetingMinutesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingMinutesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /meeting-minutes} : get all the meetingMinutes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingMinutes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MeetingMinutesDTO>> getAllMeetingMinutes(Pageable pageable) {
        LOG.debug("REST request to get a page of MeetingMinutes");
        Page<MeetingMinutesDTO> page = meetingMinutesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meeting-minutes/:id} : get the "id" meetingMinutes.
     *
     * @param id the id of the meetingMinutesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingMinutesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MeetingMinutesDTO> getMeetingMinutes(@PathVariable("id") Long id) {
        LOG.debug("REST request to get MeetingMinutes : {}", id);
        Optional<MeetingMinutesDTO> meetingMinutesDTO = meetingMinutesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetingMinutesDTO);
    }

    /**
     * {@code DELETE  /meeting-minutes/:id} : delete the "id" meetingMinutes.
     *
     * @param id the id of the meetingMinutesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeetingMinutes(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete MeetingMinutes : {}", id);
        meetingMinutesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
