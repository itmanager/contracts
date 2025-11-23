package com.contract.service;


import com.contract.domain.ContractType;
import com.contract.repository.ContractTypeRepository;
import com.contract.service.dto.ContractTypeDTO;
import com.contract.service.mapper.ContractTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ContractType}.
 */
@Service
@Transactional
public class ContractTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractTypeService.class);

    private final ContractTypeRepository contractTypeRepository;

    private final ContractTypeMapper contractTypeMapper;

    public ContractTypeService(ContractTypeRepository contractTypeRepository, ContractTypeMapper contractTypeMapper) {
        this.contractTypeRepository = contractTypeRepository;
        this.contractTypeMapper = contractTypeMapper;
    }

    /**
     * Save a ContractType.
     *
     * @param contractTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractTypeDTO save(ContractTypeDTO contractTypeDTO) {
        LOG.debug("Request to save ContractType : {}", contractTypeDTO);
        ContractType ContractType = contractTypeMapper.toEntity(contractTypeDTO);
        ContractType = contractTypeRepository.save(ContractType);
        return contractTypeMapper.toDto(ContractType);
    }

    /**
     * Update a ContractType.
     *
     * @param contractTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractTypeDTO update(ContractTypeDTO contractTypeDTO) {
        LOG.debug("Request to update ContractType : {}", contractTypeDTO);
        ContractType ContractType = contractTypeMapper.toEntity(contractTypeDTO);
        ContractType = contractTypeRepository.save(ContractType);
        return contractTypeMapper.toDto(ContractType);
    }

    /**
     * Partially update a ContractType.
     *
     * @param contractTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractTypeDTO> partialUpdate(ContractTypeDTO contractTypeDTO) {
        LOG.debug("Request to partially update ContractType : {}", contractTypeDTO);

        return contractTypeRepository
            .findById(contractTypeDTO.getId())
            .map(existingContract -> {
                contractTypeMapper.partialUpdate(existingContract, contractTypeDTO);

                return existingContract;
            })
            .map(contractTypeRepository::save)
            .map(contractTypeMapper::toDto);
    }

    /**
     * Get all the contracts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractTypeDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Contracts");
        return contractTypeRepository.findAll(pageable).map(contractTypeMapper::toDto);
    }

    /**
     * Get one ContractType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractTypeDTO> findOne(Long id) {
        LOG.debug("Request to get ContractType : {}", id);
        return contractTypeRepository.findById(id).map(contractTypeMapper::toDto);
    }

    /**
     * Delete the ContractType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ContractType : {}", id);
        contractTypeRepository.deleteById(id);
    }

    public List<ContractTypeDTO> findAll() {
        return contractTypeRepository.findAll().stream().map(contractTypeMapper::toDto).collect(Collectors.toList());
    }
}
