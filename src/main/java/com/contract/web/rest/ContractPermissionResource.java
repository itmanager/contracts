package com.contract.web.rest;

import com.contract.repository.ContractPermissionRepository;
import com.contract.service.ContractPermissionService;
import com.contract.service.dto.ContractPermissionDTO;
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
 * REST controller for managing {@link com.contract.domain.ContractPermission}.
 */
@RestController
@RequestMapping("/api/contract-permissions")
public class ContractPermissionResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContractPermissionResource.class);

    private static final String ENTITY_NAME = "contractPermission";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ContractPermissionService contractPermissionService;

    private final ContractPermissionRepository contractPermissionRepository;

    public ContractPermissionResource(ContractPermissionService contractPermissionService, ContractPermissionRepository contractPermissionRepository) {
        this.contractPermissionService = contractPermissionService;
        this.contractPermissionRepository = contractPermissionRepository;
    }

    /**
     * {@code POST  /contract-permissions} : Create a new contractPermission.
     *
     * @param contractPermissionDTO the contractPermissionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractPermissionDTO, or with status {@code 400 (Bad Request)} if the contractPermission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContractPermissionDTO> createContractPermission(@Valid @RequestBody ContractPermissionDTO contractPermissionDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save ContractPermission : {}", contractPermissionDTO);
        if (contractPermissionDTO.getId() != null) {
            throw new BadRequestAlertException("A new contractPermission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractPermissionDTO = contractPermissionService.save(contractPermissionDTO);
        return ResponseEntity.created(new URI("/api/contract-permissions/" + contractPermissionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, contractPermissionDTO.getId().toString()))
            .body(contractPermissionDTO);
    }

    /**
     * {@code PUT  /contract-permissions/:id} : Updates an existing contractPermission.
     *
     * @param id the id of the contractPermissionDTO to save.
     * @param contractPermissionDTO the contractPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the contractPermissionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractPermissionDTO> updateContractPermission(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractPermissionDTO contractPermissionDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update ContractPermission : {}, {}", id, contractPermissionDTO);
        if (contractPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractPermissionDTO = contractPermissionService.update(contractPermissionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractPermissionDTO.getId().toString()))
            .body(contractPermissionDTO);
    }

    /**
     * {@code PATCH  /contract-permissions/:id} : Partial updates given fields of an existing contractPermission, field will ignore if it is null
     *
     * @param id the id of the contractPermissionDTO to save.
     * @param contractPermissionDTO the contractPermissionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractPermissionDTO,
     * or with status {@code 400 (Bad Request)} if the contractPermissionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractPermissionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractPermissionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractPermissionDTO> partialUpdateContractPermission(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractPermissionDTO contractPermissionDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update ContractPermission partially : {}, {}", id, contractPermissionDTO);
        if (contractPermissionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractPermissionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractPermissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractPermissionDTO> result = contractPermissionService.partialUpdate(contractPermissionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractPermissionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contract-permissions} : get all the contractPermissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contractPermissions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ContractPermissionDTO>> getAllContractPermissions(Pageable pageable) {
        LOG.debug("REST request to get a page of ContractPermissions");
        Page<ContractPermissionDTO> page = contractPermissionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contract-permissions/:id} : get the "id" contractPermission.
     *
     * @param id the id of the contractPermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractPermissionDTO> getContractPermission(@PathVariable("id") Long id) {
        LOG.debug("REST request to get ContractPermission : {}", id);
        Optional<ContractPermissionDTO> contractPermissionDTO = contractPermissionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractPermissionDTO);
    }

    /**
     * {@code DELETE  /contract-permissions/:id} : delete the "id" contractPermission.
     *
     * @param id the id of the contractPermissionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContractPermission(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete ContractPermission : {}", id);
        contractPermissionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }


    /**
     * {@code GET  /contract-permissions/:id} : get the "id" contractPermission.
     *
     * @param contractId the id of the contractPermissionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractPermissionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<ContractPermissionDTO>> getContractPermissionByContractId(@PathVariable("contractId") Long contractId) {
        LOG.debug("REST request to get ContractPermission : {}", contractId);
        List<ContractPermissionDTO> page = contractPermissionService.findByContractId(contractId);
        return ResponseEntity.ok().body(page);
    }
}
