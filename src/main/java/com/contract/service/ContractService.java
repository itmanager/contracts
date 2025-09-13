package com.contract.service;

import com.contract.domain.Contract;
import com.contract.repository.ContractRepository;
import com.contract.service.dto.ContractDTO;
import com.contract.service.mapper.ContractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Contract}.
 */
@Service
@Transactional
public class ContractService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractService.class);

    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    public ContractService(ContractRepository contractRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    /**
     * Save a contract.
     *
     * @param contractDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractDTO save(ContractDTO contractDTO) {
        LOG.debug("Request to save Contract : {}", contractDTO);
        Contract contract = contractMapper.toEntity(contractDTO);
        contract = contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    /**
     * Update a contract.
     *
     * @param contractDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractDTO update(ContractDTO contractDTO) {
        LOG.debug("Request to update Contract : {}", contractDTO);
        Contract contract = contractMapper.toEntity(contractDTO);
        contract = contractRepository.save(contract);
        return contractMapper.toDto(contract);
    }

    /**
     * Partially update a contract.
     *
     * @param contractDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractDTO> partialUpdate(ContractDTO contractDTO) {
        LOG.debug("Request to partially update Contract : {}", contractDTO);

        return contractRepository
            .findById(contractDTO.getId())
            .map(existingContract -> {
                contractMapper.partialUpdate(existingContract, contractDTO);

                return existingContract;
            })
            .map(contractRepository::save)
            .map(contractMapper::toDto);
    }

    /**
     * Get all the contracts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Contracts");
        return contractRepository.findAll(pageable).map(contractMapper::toDto);
    }

    /**
     * Get one contract by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractDTO> findOne(Long id) {
        LOG.debug("Request to get Contract : {}", id);
        return contractRepository.findById(id).map(contractMapper::toDto);
    }

    /**
     * Delete the contract by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Contract : {}", id);
        contractRepository.deleteById(id);
    }
}
