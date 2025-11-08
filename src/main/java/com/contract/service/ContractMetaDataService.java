package com.contract.service;


import com.contract.domain.ContractMetaData;
import com.contract.repository.ContractMetaDataRepository;
import com.contract.service.dto.ContractMetaDataDTO;
import com.contract.service.mapper.ContractMetaDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ContractMetaDataService {

    private final Logger log = LoggerFactory.getLogger(ContractMetaDataService.class);

    private final ContractMetaDataRepository contractMetaDataRepository;
    private final ContractMetaDataMapper contractMetaDataMapper;
    private final ContractService contractService;
    private final ContractTypeService contractTypeService;

    public ContractMetaDataService(ContractMetaDataRepository contractMetaDataRepository,
                                  ContractMetaDataMapper contractMetaDataMapper,
                                  ContractService contractService,
                                  ContractTypeService contractTypeService) {
        this.contractMetaDataRepository = contractMetaDataRepository;
        this.contractMetaDataMapper = contractMetaDataMapper;
        this.contractService = contractService;
        this.contractTypeService = contractTypeService;
    }

    /**
     * Save a contractMetaData.
     */
    public ContractMetaDataDTO save(ContractMetaDataDTO contractMetaDataDTO) {
        log.debug("Request to save ContractMetaData : {}", contractMetaDataDTO);
        
        // Validate contract exists
        if (contractMetaDataDTO.getContract() != null) {
            contractService.findOne(contractMetaDataDTO.getContract().getId())
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + contractMetaDataDTO.getContract().getId()));
        }

        // Validate contract type exists if provided
        if (contractMetaDataDTO.getContractType().getId() != null) {
            contractTypeService.findOne(contractMetaDataDTO.getContractType().getId())
                .orElseThrow(() -> new RuntimeException("ContractType not found with id: " + contractMetaDataDTO.getContractType().getId()));
        }

        // Validate contract parent exists if provided
        if (contractMetaDataDTO.getContractParent() != null) {
            contractService.findOne(contractMetaDataDTO.getContractParent().getId())
                .orElseThrow(() -> new RuntimeException("ContractParent not found with id: " + contractMetaDataDTO.getContractParent().getId()));
        }



        ContractMetaData contractMetaData = contractMetaDataMapper.toEntity(contractMetaDataDTO);
        contractMetaData = contractMetaDataRepository.save(contractMetaData);
        return contractMetaDataMapper.toDto(contractMetaData);
    }

    /**
     * Update a contractMetaData.
     */
    public ContractMetaDataDTO update(ContractMetaDataDTO contractMetaDataDTO) {
        log.debug("Request to update ContractMetaData : {}", contractMetaDataDTO);
        
        if (contractMetaDataDTO.getId() == null) {
            throw new RuntimeException("Id cannot be null for update");
        }

        return save(contractMetaDataDTO);
    }

    /**
     * Get all the contractMetaData.
     */
    @Transactional(readOnly = true)
    public Page<ContractMetaDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContractMetaData");
        return contractMetaDataRepository.findAll(pageable)
            .map(contractMetaDataMapper::toDto);
    }

    /**
     * Get one contractMetaData by id.
     */
    @Transactional(readOnly = true)
    public Optional<ContractMetaDataDTO> findOne(Long id) {
        log.debug("Request to get ContractMetaData : {}", id);
        return contractMetaDataRepository.findById(id)
            .map(contractMetaDataMapper::toDto);
    }

    /**
     * Get contractMetaData by contract id.
     */
    @Transactional(readOnly = true)
    public Optional<ContractMetaDataDTO> findByContractId(Long contractId) {
        log.debug("Request to get ContractMetaData by contract id: {}", contractId);
        return contractMetaDataRepository.findByContractId(contractId)
            .map(contractMetaDataMapper::toDto);
    }

    /**
     * Delete the contractMetaData by id.
     */
    public void delete(Long id) {
        log.debug("Request to delete ContractMetaData : {}", id);
        contractMetaDataRepository.deleteById(id);
    }





    /**
     * Get contractMetaData by contract parent id.
     */
    @Transactional(readOnly = true)
    public Page<ContractMetaDataDTO> findByContractParentId(Long contractParentId, Pageable pageable) {
        log.debug("Request to get ContractMetaData by contract parent id: {}", contractParentId);
        /*return contractMetaDataRepository.findByContractParentId(contractParentId, pageable)
            .map(contractMetaDataMapper::toDto);*/
        return null;
    }

    /**
     * Get contractMetaData by type id.
     */
    @Transactional(readOnly = true)
    public Page<ContractMetaDataDTO> findByTypeId(Long typeId, Pageable pageable) {
        log.debug("Request to get ContractMetaData by type id: {}", typeId);
       /* return contractMetaDataRepository.findByTypeId(typeId, pageable)
            .map(contractMetaDataMapper::toDto);*/
        return null;
    }
}