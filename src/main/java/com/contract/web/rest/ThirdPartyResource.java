package com.contract.web.rest;


import com.contract.domain.enumeration.ThirdPartyType;
import com.contract.service.ThirdPartyService;
import com.contract.service.dto.SupervisorCommentDTO;
import com.contract.service.dto.ThirdPartyDTO;
import com.contract.web.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/third-parties")
public class ThirdPartyResource {

    private final Logger log = LoggerFactory.getLogger(ThirdPartyResource.class);

    private final ThirdPartyService thirdPartyService;

    public ThirdPartyResource(ThirdPartyService thirdPartyService) {
        this.thirdPartyService = thirdPartyService;
    }

    @PostMapping
    public ResponseEntity<ThirdPartyDTO> create(@RequestBody ThirdPartyDTO thirdPartyDTO) throws URISyntaxException {
        log.debug("REST request to save ThirdParty: {}", thirdPartyDTO);
        
        ThirdPartyDTO result = thirdPartyService.create(thirdPartyDTO);
        return ResponseEntity.created(new URI("/api/third-parties/" + result.getId()))
                .body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThirdPartyDTO> update(@PathVariable Long id, @RequestBody ThirdPartyDTO thirdPartyDTO) {
        log.debug("REST request to update ThirdParty: {}", thirdPartyDTO);
        
        ThirdPartyDTO result = thirdPartyService.update(id, thirdPartyDTO);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<ThirdPartyDTO>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of ThirdParties");
        Page<ThirdPartyDTO> page = thirdPartyService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @GetMapping("/all")
    public ResponseEntity<List<ThirdPartyDTO>> getAll() {
        log.debug("REST request to get a page of ThirdParties");
        List<ThirdPartyDTO> page = thirdPartyService.findAll();
        return ResponseEntity.ok().body(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ThirdPartyDTO> getOne(@PathVariable Long id) {
        log.debug("REST request to get ThirdParty: {}", id);
        
        Optional<ThirdPartyDTO> thirdPartyDTO = thirdPartyService.findOne(id);
        return thirdPartyDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete ThirdParty: {}", id);
        
        thirdPartyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ThirdPartyDTO>> getByType(@PathVariable ThirdPartyType type) {
        log.debug("REST request to get ThirdParties by type: {}", type);
        
        List<ThirdPartyDTO> result = thirdPartyService.findByType(type);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/active/{isActive}")
    public ResponseEntity<List<ThirdPartyDTO>> getByActiveStatus(@PathVariable Boolean isActive) {
        log.debug("REST request to get ThirdParties by isActive: {}", isActive);
        
        List<ThirdPartyDTO> result = thirdPartyService.findByIsActive(isActive);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ThirdPartyDTO>> searchByName(@RequestParam String name) {
        log.debug("REST request to search ThirdParties by name: {}", name);
        
        List<ThirdPartyDTO> result = thirdPartyService.searchByName(name);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/tax-id/{taxId}")
    public ResponseEntity<ThirdPartyDTO> getByTaxId(@PathVariable String taxId) {
        log.debug("REST request to get ThirdParty by taxId: {}", taxId);
        
        Optional<ThirdPartyDTO> thirdPartyDTO = thirdPartyService.findByTaxId(taxId);
        return thirdPartyDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/registration-number/{registrationNumber}")
    public ResponseEntity<ThirdPartyDTO> getByRegistrationNumber(@PathVariable String registrationNumber) {
        log.debug("REST request to get ThirdParty by registrationNumber: {}", registrationNumber);
        
        Optional<ThirdPartyDTO> thirdPartyDTO = thirdPartyService.findByRegistrationNumber(registrationNumber);
        return thirdPartyDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}