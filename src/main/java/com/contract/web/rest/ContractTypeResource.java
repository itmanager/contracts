package com.contract.web.rest;

import com.contract.repository.ContractTypeRepository;
import com.contract.service.ContractTypeService;
import com.contract.service.dto.ContractTypeDTO;
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
 * REST controller for managing {@link com.contract.domain.Contract}.
 */
@RestController
@RequestMapping("/api/contract-types")
public class ContractTypeResource {

    private static final Logger LOG = LoggerFactory.getLogger(ContractTypeResource.class);

    private static final String ENTITY_NAME = "contract";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ContractTypeService contractTypeService;

    private final ContractTypeRepository contractTypeRepository;

    public ContractTypeResource(ContractTypeService contractTypeService, ContractTypeRepository contractTypeRepository) {
        this.contractTypeService = contractTypeService;
        this.contractTypeRepository = contractTypeRepository;
    }

    /**
     * {@code POST  /contracts} : Create a new contract.
     *
     * @param contractTypeDTO the contractTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contractTypeDTO, or with status {@code 400 (Bad Request)} if the contract has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ContractTypeDTO> createContract(@Valid @RequestBody ContractTypeDTO contractTypeDTO) throws URISyntaxException {
        LOG.debug("REST request to save Contract : {}", contractTypeDTO);
        if (contractTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new contract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contractTypeDTO = contractTypeService.save(contractTypeDTO);
        return ResponseEntity.created(new URI("/api/contracts/" + contractTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, contractTypeDTO.getId().toString()))
            .body(contractTypeDTO);
    }

    /**
     * {@code PUT  /contracts/:id} : Updates an existing contract.
     *
     * @param id the id of the contractTypeDTO to save.
     * @param contractTypeDTO the contractTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractTypeDTO,
     * or with status {@code 400 (Bad Request)} if the contractTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contractTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> updateContract(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContractTypeDTO contractTypeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Contract : {}, {}", id, contractTypeDTO);
        if (contractTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        contractTypeDTO = contractTypeService.update(contractTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractTypeDTO.getId().toString()))
            .body(contractTypeDTO);
    }

    /**
     * {@code PATCH  /contracts/:id} : Partial updates given fields of an existing contract, field will ignore if it is null
     *
     * @param id the id of the contractTypeDTO to save.
     * @param contractTypeDTO the contractTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contractTypeDTO,
     * or with status {@code 400 (Bad Request)} if the contractTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contractTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contractTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContractTypeDTO> partialUpdateContract(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContractTypeDTO contractTypeDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Contract partially : {}, {}", id, contractTypeDTO);
        if (contractTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contractTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contractTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContractTypeDTO> result = contractTypeService.partialUpdate(contractTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, contractTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /contracts} : get all the contracts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contracts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ContractTypeDTO>> getAllContracts(Pageable pageable) {
        LOG.debug("REST request to get a page of Contracts");
        Page<ContractTypeDTO> page = contractTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /contracts/:id} : get the "id" contract.
     *
     * @param id the id of the contractTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contractTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContractTypeDTO> getContract(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Contract : {}", id);
        Optional<ContractTypeDTO> contractTypeDTO = contractTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contractTypeDTO);
    }

    /**
     * {@code DELETE  /contracts/:id} : delete the "id" contract.
     *
     * @param id the id of the contractTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContract(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Contract : {}", id);
        contractTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /contracts} : get all the contracts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contracts in body.
     */
    @GetMapping("/findAll")
    public ResponseEntity<List<ContractTypeDTO>> findAllContracts() {
        LOG.debug("REST request to get a page of Contracts");
        List<ContractTypeDTO> page = contractTypeService.findAll();
        return ResponseEntity.ok().body(page);
    }
}
