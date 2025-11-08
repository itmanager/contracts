package com.contract.web.rest;

import com.contract.service.ContractMetaDataService;
import com.contract.service.dto.ContractMetaDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ContractMetaDataResource {

    private final Logger log = LoggerFactory.getLogger(ContractMetaDataResource.class);

    private static final String ENTITY_NAME = "contractMetaData";

    private final ContractMetaDataService contractMetaDataService;

    public ContractMetaDataResource(ContractMetaDataService contractMetaDataService) {
        this.contractMetaDataService = contractMetaDataService;
    }

    /**
     * POST  /contract-meta-data : Create a new contractMetaData.
     */
    @PostMapping("/contract-meta-data")
    public ResponseEntity<ContractMetaDataDTO> createContractMetaData(@Valid @RequestBody ContractMetaDataDTO contractMetaDataDTO) throws URISyntaxException {
        log.debug("REST request to save ContractMetaData : {}", contractMetaDataDTO);
        
        if (contractMetaDataDTO.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        ContractMetaDataDTO result = contractMetaDataService.save(contractMetaDataDTO);
        return ResponseEntity.created(new URI("/api/contract-meta-data/" + result.getId()))
            .body(result);
    }

    /**
     * PUT  /contract-meta-data : Updates an existing contractMetaData.
     */
    @PutMapping("/contract-meta-data")
    public ResponseEntity<ContractMetaDataDTO> updateContractMetaData(@Valid @RequestBody ContractMetaDataDTO contractMetaDataDTO) throws URISyntaxException {
        log.debug("REST request to update ContractMetaData : {}", contractMetaDataDTO);
        
        if (contractMetaDataDTO.getId() == null) {
            return createContractMetaData(contractMetaDataDTO);
        }

        ContractMetaDataDTO result = contractMetaDataService.update(contractMetaDataDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    /**
     * GET  /contract-meta-data : get all the contractMetaData.
     */
    @GetMapping("/contract-meta-data")
    public ResponseEntity<List<ContractMetaDataDTO>> getAllContractMetaData(Pageable pageable) {
        log.debug("REST request to get a page of ContractMetaData");
        Page<ContractMetaDataDTO> page = contractMetaDataService.findAll(pageable);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contract-meta-data/:id : get the "id" contractMetaData.
     */
    @GetMapping("/contract-meta-data/{id}")
    public ResponseEntity<ContractMetaDataDTO> getContractMetaData(@PathVariable Long id) {
        log.debug("REST request to get ContractMetaData : {}", id);
        Optional<ContractMetaDataDTO> contractMetaDataDTO = contractMetaDataService.findOne(id);
        return contractMetaDataDTO.map(response -> ResponseEntity.ok().body(response))
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET  /contract-meta-data/contract/:contractId : get contractMetaData by contract id.
     */
    @GetMapping("/contract-meta-data/contract/{contractId}")
    public ResponseEntity<ContractMetaDataDTO> getContractMetaDataByContractId(@PathVariable Long contractId) {
        log.debug("REST request to get ContractMetaData by contract id : {}", contractId);
        Optional<ContractMetaDataDTO> contractMetaDataDTO = contractMetaDataService.findByContractId(contractId);
        return contractMetaDataDTO.map(response -> ResponseEntity.ok().body(response))
            .orElse(ResponseEntity.notFound().build());
    }

    /**
     * DELETE  /contract-meta-data/:id : delete the "id" contractMetaData.
     */
    @DeleteMapping("/contract-meta-data/{id}")
    public ResponseEntity<Void> deleteContractMetaData(@PathVariable Long id) {
        log.debug("REST request to delete ContractMetaData : {}", id);
        contractMetaDataService.delete(id);
        return ResponseEntity.ok().build();
    }



    /**
     * GET  /contract-meta-data/contract-parent/:contractParentId : get contractMetaData by contract parent id.
     */
    @GetMapping("/contract-meta-data/contract-parent/{contractParentId}")
    public ResponseEntity<List<ContractMetaDataDTO>> getContractMetaDataByContractParentId(@PathVariable Long contractParentId, Pageable pageable) {
        log.debug("REST request to get ContractMetaData by contract parent id : {}", contractParentId);
        Page<ContractMetaDataDTO> page = contractMetaDataService.findByContractParentId(contractParentId, pageable);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /contract-meta-data/type/:typeId : get contractMetaData by type id.
     */
    @GetMapping("/contract-meta-data/type/{typeId}")
    public ResponseEntity<List<ContractMetaDataDTO>> getContractMetaDataByTypeId(@PathVariable Long typeId, Pageable pageable) {
        log.debug("REST request to get ContractMetaData by type id : {}", typeId);
        Page<ContractMetaDataDTO> page = contractMetaDataService.findByTypeId(typeId, pageable);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}