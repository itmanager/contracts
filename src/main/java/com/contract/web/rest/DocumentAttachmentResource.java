package com.contract.web.rest;

import com.contract.repository.DocumentAttachmentRepository;
import com.contract.service.DocumentAttachmentService;
import com.contract.service.dto.DocumentAttachmentDTO;
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
 * REST controller for managing {@link com.contract.domain.DocumentAttachment}.
 */
@RestController
@RequestMapping("/api/document-attachments")
public class DocumentAttachmentResource {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentAttachmentResource.class);

    private static final String ENTITY_NAME = "documentAttachment";

    @Value("${spring.application.name}")
    private String applicationName;

    private final DocumentAttachmentService documentAttachmentService;

    private final DocumentAttachmentRepository documentAttachmentRepository;

    public DocumentAttachmentResource(
        DocumentAttachmentService documentAttachmentService,
        DocumentAttachmentRepository documentAttachmentRepository
    ) {
        this.documentAttachmentService = documentAttachmentService;
        this.documentAttachmentRepository = documentAttachmentRepository;
    }

    /**
     * {@code POST  /document-attachments} : Create a new documentAttachment.
     *
     * @param documentAttachmentDTO the documentAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentAttachmentDTO, or with status {@code 400 (Bad Request)} if the documentAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DocumentAttachmentDTO> createDocumentAttachment(@Valid @RequestBody DocumentAttachmentDTO documentAttachmentDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save DocumentAttachment : {}", documentAttachmentDTO);
        if (documentAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        documentAttachmentDTO = documentAttachmentService.save(documentAttachmentDTO);
        return ResponseEntity.created(new URI("/api/document-attachments/" + documentAttachmentDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, documentAttachmentDTO.getId().toString()))
            .body(documentAttachmentDTO);
    }

    /**
     * {@code PUT  /document-attachments/:id} : Updates an existing documentAttachment.
     *
     * @param id the id of the documentAttachmentDTO to save.
     * @param documentAttachmentDTO the documentAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the documentAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DocumentAttachmentDTO> updateDocumentAttachment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DocumentAttachmentDTO documentAttachmentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update DocumentAttachment : {}, {}", id, documentAttachmentDTO);
        if (documentAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentAttachmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentAttachmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        documentAttachmentDTO = documentAttachmentService.update(documentAttachmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentAttachmentDTO.getId().toString()))
            .body(documentAttachmentDTO);
    }

    /**
     * {@code PATCH  /document-attachments/:id} : Partial updates given fields of an existing documentAttachment, field will ignore if it is null
     *
     * @param id the id of the documentAttachmentDTO to save.
     * @param documentAttachmentDTO the documentAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the documentAttachmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentAttachmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DocumentAttachmentDTO> partialUpdateDocumentAttachment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DocumentAttachmentDTO documentAttachmentDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update DocumentAttachment partially : {}, {}", id, documentAttachmentDTO);
        if (documentAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentAttachmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentAttachmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DocumentAttachmentDTO> result = documentAttachmentService.partialUpdate(documentAttachmentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, documentAttachmentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /document-attachments} : get all the documentAttachments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentAttachments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<DocumentAttachmentDTO>> getAllDocumentAttachments(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of DocumentAttachments");
        Page<DocumentAttachmentDTO> page = documentAttachmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /document-attachments/:id} : get the "id" documentAttachment.
     *
     * @param id the id of the documentAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentAttachmentDTO> getDocumentAttachment(@PathVariable("id") Long id) {
        LOG.debug("REST request to get DocumentAttachment : {}", id);
        Optional<DocumentAttachmentDTO> documentAttachmentDTO = documentAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentAttachmentDTO);
    }

    /**
     * {@code DELETE  /document-attachments/:id} : delete the "id" documentAttachment.
     *
     * @param id the id of the documentAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentAttachment(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete DocumentAttachment : {}", id);
        documentAttachmentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
