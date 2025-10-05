package com.contract.service;

import com.contract.domain.ContractPhase;
import com.contract.repository.ContractPhaseRepository;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.mapper.ContractPhaseMapper;
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
 * Service Implementation for managing {@link ContractPhase}.
 */
@Service
@Transactional
public class ContractPhaseService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractPhaseService.class);

    private final ContractPhaseRepository contractPhaseRepository;

    private final ContractPhaseMapper contractPhaseMapper;

    public ContractPhaseService(ContractPhaseRepository contractPhaseRepository, ContractPhaseMapper contractPhaseMapper) {
        this.contractPhaseRepository = contractPhaseRepository;
        this.contractPhaseMapper = contractPhaseMapper;
    }

    /**
     * Save a contractPhase.
     *
     * @param contractPhaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPhaseDTO save(ContractPhaseDTO contractPhaseDTO) {
        LOG.debug("Request to save ContractPhase : {}", contractPhaseDTO);
        ContractPhase contractPhase = contractPhaseMapper.toEntity(contractPhaseDTO);
        contractPhase = contractPhaseRepository.save(contractPhase);
        return contractPhaseMapper.toDto(contractPhase);
    }

    /**
     * Update a contractPhase.
     *
     * @param contractPhaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPhaseDTO update(ContractPhaseDTO contractPhaseDTO) {
        LOG.debug("Request to update ContractPhase : {}", contractPhaseDTO);
        ContractPhase contractPhase = contractPhaseMapper.toEntity(contractPhaseDTO);
        contractPhase = contractPhaseRepository.save(contractPhase);
        return contractPhaseMapper.toDto(contractPhase);
    }

    /**
     * Partially update a contractPhase.
     *
     * @param contractPhaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractPhaseDTO> partialUpdate(ContractPhaseDTO contractPhaseDTO) {
        LOG.debug("Request to partially update ContractPhase : {}", contractPhaseDTO);

        return contractPhaseRepository
            .findById(contractPhaseDTO.getId())
            .map(existingContractPhase -> {
                contractPhaseMapper.partialUpdate(existingContractPhase, contractPhaseDTO);

                return existingContractPhase;
            })
            .map(contractPhaseRepository::save)
            .map(contractPhaseMapper::toDto);
    }

    /**
     * Get all the contractPhases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractPhaseDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ContractPhases");
        return contractPhaseRepository.findAll(pageable).map(contractPhaseMapper::toDto);
    }

    /**
     * Get one contractPhase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractPhaseDTO> findOne(Long id) {
        LOG.debug("Request to get ContractPhase : {}", id);
        return contractPhaseRepository.findById(id).map(contractPhaseMapper::toDto);
    }

    /**
     * Delete the contractPhase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ContractPhase : {}", id);
        contractPhaseRepository.deleteById(id);
    }

    public List<ContractPhaseDTO> findByContractId(Long contractId) {
        return contractPhaseRepository.findByContractId(contractId).stream().map(contractPhaseMapper::toDto).collect(Collectors.toList());
    }
}
