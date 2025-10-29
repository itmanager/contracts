package com.contract.web.rest;

import com.contract.repository.EquipmentUsageRepository;
import com.contract.service.EquipmentUsageService;
import com.contract.service.dto.EquipmentUsageDTO;
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
 * REST controller for managing {@link com.contract.domain.EquipmentUsage}.
 */
@RestController
@RequestMapping("/api/equipment-usages")
public class EquipmentUsageResource {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentUsageResource.class);

    private static final String ENTITY_NAME = "equipmentUsage";

    @Value("${spring.application.name}")
    private String applicationName;

    private final EquipmentUsageService equipmentUsageService;

    private final EquipmentUsageRepository equipmentUsageRepository;

    public EquipmentUsageResource(EquipmentUsageService equipmentUsageService, EquipmentUsageRepository equipmentUsageRepository) {
        this.equipmentUsageService = equipmentUsageService;
        this.equipmentUsageRepository = equipmentUsageRepository;
    }

    /**
     * {@code POST  /equipment-usages} : Create a new equipmentUsage.
     *
     * @param equipmentUsageDTO the equipmentUsageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new equipmentUsageDTO, or with status {@code 400 (Bad Request)} if the equipmentUsage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EquipmentUsageDTO> createEquipmentUsage(@Valid @RequestBody EquipmentUsageDTO equipmentUsageDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save EquipmentUsage : {}", equipmentUsageDTO);
        if (equipmentUsageDTO.getId() != null) {
            throw new BadRequestAlertException("A new equipmentUsage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        equipmentUsageDTO = equipmentUsageService.save(equipmentUsageDTO);
        return ResponseEntity.created(new URI("/api/equipment-usages/" + equipmentUsageDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, equipmentUsageDTO.getId().toString()))
            .body(equipmentUsageDTO);
    }

    /**
     * {@code PUT  /equipment-usages/:id} : Updates an existing equipmentUsage.
     *
     * @param id the id of the equipmentUsageDTO to save.
     * @param equipmentUsageDTO the equipmentUsageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipmentUsageDTO,
     * or with status {@code 400 (Bad Request)} if the equipmentUsageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the equipmentUsageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentUsageDTO> updateEquipmentUsage(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EquipmentUsageDTO equipmentUsageDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update EquipmentUsage : {}, {}", id, equipmentUsageDTO);
        if (equipmentUsageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, equipmentUsageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!equipmentUsageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        equipmentUsageDTO = equipmentUsageService.update(equipmentUsageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipmentUsageDTO.getId().toString()))
            .body(equipmentUsageDTO);
    }

    /**
     * {@code PATCH  /equipment-usages/:id} : Partial updates given fields of an existing equipmentUsage, field will ignore if it is null
     *
     * @param id the id of the equipmentUsageDTO to save.
     * @param equipmentUsageDTO the equipmentUsageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated equipmentUsageDTO,
     * or with status {@code 400 (Bad Request)} if the equipmentUsageDTO is not valid,
     * or with status {@code 404 (Not Found)} if the equipmentUsageDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the equipmentUsageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EquipmentUsageDTO> partialUpdateEquipmentUsage(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EquipmentUsageDTO equipmentUsageDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update EquipmentUsage partially : {}, {}", id, equipmentUsageDTO);
        if (equipmentUsageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, equipmentUsageDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!equipmentUsageRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EquipmentUsageDTO> result = equipmentUsageService.partialUpdate(equipmentUsageDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, equipmentUsageDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /equipment-usages} : get all the equipmentUsages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipmentUsages in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EquipmentUsageDTO>> getAllEquipmentUsages(
        Pageable pageable
    ) {
        LOG.debug("REST request to get a page of EquipmentUsages");
        Page<EquipmentUsageDTO> page = equipmentUsageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /equipment-usages/:id} : get the "id" equipmentUsage.
     *
     * @param id the id of the equipmentUsageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the equipmentUsageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentUsageDTO> getEquipmentUsage(@PathVariable("id") Long id) {
        LOG.debug("REST request to get EquipmentUsage : {}", id);
        Optional<EquipmentUsageDTO> equipmentUsageDTO = equipmentUsageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(equipmentUsageDTO);
    }

    /**
     * {@code DELETE  /equipment-usages/:id} : delete the "id" equipmentUsage.
     *
     * @param id the id of the equipmentUsageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipmentUsage(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete EquipmentUsage : {}", id);
        equipmentUsageService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code GET  /equipment-usages/contract/:contractId} : get equipmentUsages by contract.
     *
     * @param contractId the contract id.
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of equipmentUsages in body.
     */
    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<EquipmentUsageDTO>> getEquipmentUsagesByContract(@PathVariable Long contractId, Pageable pageable) {
        LOG.debug("REST request to get EquipmentUsages by contract id : {}", contractId);
        Page<EquipmentUsageDTO> page = equipmentUsageService.findByContractId(contractId, pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
