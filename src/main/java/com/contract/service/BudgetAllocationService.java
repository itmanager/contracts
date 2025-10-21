package com.contract.service;

import com.contract.domain.BudgetAllocation;
import com.contract.repository.BudgetAllocationRepository;
import com.contract.service.dto.BudgetAllocationDTO;
import com.contract.service.mapper.BudgetAllocationMapper;
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
 * Service Implementation for managing {@link BudgetAllocation}.
 */
@Service
@Transactional
public class BudgetAllocationService {

    private static final Logger LOG = LoggerFactory.getLogger(BudgetAllocationService.class);

    private final BudgetAllocationRepository budgetAllocationRepository;

    private final BudgetAllocationMapper budgetAllocationMapper;

    public BudgetAllocationService(BudgetAllocationRepository budgetAllocationRepository, BudgetAllocationMapper budgetAllocationMapper) {
        this.budgetAllocationRepository = budgetAllocationRepository;
        this.budgetAllocationMapper = budgetAllocationMapper;
    }

    /**
     * Save a budgetAllocation.
     *
     * @param budgetAllocationDTO the entity to save.
     * @return the persisted entity.
     */
    public BudgetAllocationDTO save(BudgetAllocationDTO budgetAllocationDTO) {
        LOG.debug("Request to save BudgetAllocation : {}", budgetAllocationDTO);
        BudgetAllocation budgetAllocation = budgetAllocationMapper.toEntity(budgetAllocationDTO);
        budgetAllocation = budgetAllocationRepository.save(budgetAllocation);
        return budgetAllocationMapper.toDto(budgetAllocation);
    }

    /**
     * Update a budgetAllocation.
     *
     * @param budgetAllocationDTO the entity to save.
     * @return the persisted entity.
     */
    public BudgetAllocationDTO update(BudgetAllocationDTO budgetAllocationDTO) {
        LOG.debug("Request to update BudgetAllocation : {}", budgetAllocationDTO);
        BudgetAllocation budgetAllocation = budgetAllocationMapper.toEntity(budgetAllocationDTO);
        budgetAllocation = budgetAllocationRepository.save(budgetAllocation);
        return budgetAllocationMapper.toDto(budgetAllocation);
    }

    /**
     * Partially update a budgetAllocation.
     *
     * @param budgetAllocationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BudgetAllocationDTO> partialUpdate(BudgetAllocationDTO budgetAllocationDTO) {
        LOG.debug("Request to partially update BudgetAllocation : {}", budgetAllocationDTO);

        return budgetAllocationRepository
            .findById(budgetAllocationDTO.getId())
            .map(existingBudgetAllocation -> {
                budgetAllocationMapper.partialUpdate(existingBudgetAllocation, budgetAllocationDTO);

                return existingBudgetAllocation;
            })
            .map(budgetAllocationRepository::save)
            .map(budgetAllocationMapper::toDto);
    }

    /**
     * Get all the budgetAllocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BudgetAllocationDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all BudgetAllocations");
        return budgetAllocationRepository.findAll(pageable).map(budgetAllocationMapper::toDto);
    }

    /**
     * Get one budgetAllocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BudgetAllocationDTO> findOne(Long id) {
        LOG.debug("Request to get BudgetAllocation : {}", id);
        return budgetAllocationRepository.findById(id).map(budgetAllocationMapper::toDto);
    }

    /**
     * Delete the budgetAllocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete BudgetAllocation : {}", id);
        budgetAllocationRepository.deleteById(id);
    }

    public List<BudgetAllocationDTO> findByContractPhaseId(Long contractPhaseId) {
        return budgetAllocationRepository.findAllByContractPhaseId(contractPhaseId).
                stream().map(budgetAllocationMapper::toDto).collect(Collectors.toList());
    }
}
