package com.contract.service;

import com.contract.domain.ThirdPartyEntity;
import com.contract.domain.enumeration.ThirdPartyType;
import com.contract.repository.ThirdPartyRepository;
import com.contract.service.dto.ThirdPartyDTO;
import com.contract.service.mapper.ThirdPartyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThirdPartyService{

    private final Logger log = LoggerFactory.getLogger(ThirdPartyService.class);

    private final ThirdPartyRepository thirdPartyRepository;
    
    private final ThirdPartyMapper thirdPartyMapper;

    public ThirdPartyService(ThirdPartyRepository thirdPartyRepository, ThirdPartyMapper thirdPartyMapper) {
        this.thirdPartyRepository = thirdPartyRepository;
        this.thirdPartyMapper = thirdPartyMapper;
    }

    public ThirdPartyDTO create(ThirdPartyDTO thirdPartyDTO) {
        log.debug("Request to create ThirdParty: {}", thirdPartyDTO);
        
        ThirdPartyEntity thirdPartyEntity = thirdPartyMapper.dtoToEntity(thirdPartyDTO);
        thirdPartyEntity = thirdPartyRepository.save(thirdPartyEntity);
        
        return thirdPartyMapper.entityToDto(thirdPartyEntity);
    }

    public ThirdPartyDTO update(Long id, ThirdPartyDTO thirdPartyDTO) {
        log.debug("Request to update ThirdParty: {}", thirdPartyDTO);
        
        ThirdPartyEntity existingEntity = thirdPartyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ThirdParty not found with id: " + id));
        
        // Update fields
        existingEntity.setName(thirdPartyDTO.getName());
        existingEntity.setType(thirdPartyDTO.getType());
        existingEntity.setAddress(thirdPartyDTO.getAddress());
        existingEntity.setPhone(thirdPartyDTO.getPhone());
        existingEntity.setEmail(thirdPartyDTO.getEmail());
        existingEntity.setContactPerson(thirdPartyDTO.getContactPerson());
        existingEntity.setTaxId(thirdPartyDTO.getTaxId());
        existingEntity.setRegistrationNumber(thirdPartyDTO.getRegistrationNumber());
        existingEntity.setDescription(thirdPartyDTO.getDescription());
        existingEntity.setIsActive(thirdPartyDTO.getIsActive());
        
        existingEntity = thirdPartyRepository.save(existingEntity);
        
        return thirdPartyMapper.entityToDto(existingEntity);
    }

    @Transactional(readOnly = true)
    public Page<ThirdPartyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ThirdParties");
        return thirdPartyRepository.findAll(pageable)
                .map(thirdPartyMapper::entityToDto);
    }

    @Transactional(readOnly = true)
    public Optional<ThirdPartyDTO> findOne(Long id) {
        log.debug("Request to get ThirdParty: {}", id);
        return thirdPartyRepository.findById(id)
                .map(thirdPartyMapper::entityToDto);
    }

    public void delete(Long id) {
        log.debug("Request to delete ThirdParty: {}", id);
        thirdPartyRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ThirdPartyDTO> findByType(ThirdPartyType type) {
        log.debug("Request to get ThirdParties by type: {}", type);
        return thirdPartyRepository.findByType(type).stream()
                .map(thirdPartyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ThirdPartyDTO> findByIsActive(Boolean isActive) {
        log.debug("Request to get ThirdParties by isActive: {}", isActive);
        return thirdPartyRepository.findByIsActive(isActive).stream()
                .map(thirdPartyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ThirdPartyDTO> searchByName(String name) {
        log.debug("Request to search ThirdParties by name: {}", name);
        return thirdPartyRepository.findByNameContainingIgnoreCase(name).stream()
                .map(thirdPartyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ThirdPartyDTO> findByTaxId(String taxId) {
        log.debug("Request to get ThirdParty by taxId: {}", taxId);
        return thirdPartyRepository.findByTaxId(taxId)
                .map(thirdPartyMapper::entityToDto);
    }

    @Transactional(readOnly = true)
    public Optional<ThirdPartyDTO> findByRegistrationNumber(String registrationNumber) {
        log.debug("Request to get ThirdParty by registrationNumber: {}", registrationNumber);
        return thirdPartyRepository.findByRegistrationNumber(registrationNumber)
                .map(thirdPartyMapper::entityToDto);
    }

    public List<ThirdPartyDTO> findAll() {
        log.debug("Request to get All ThirdParty : {}");
        return thirdPartyRepository.findAll().stream()
                .map(thirdPartyMapper::entityToDto).collect(Collectors.toList());
    }
}