package com.contract.service;

import com.contract.domain.OverheadCost;
import com.contract.repository.OverheadCostRepository;
import com.contract.service.dto.OverheadCostDTO;
import com.contract.service.mapper.OverheadCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OverheadCost}.
 */
@Service
@Transactional
public class OverheadCostService {

    private static final Logger LOG = LoggerFactory.getLogger(OverheadCostService.class);

    private final OverheadCostRepository overheadCostRepository;

    private final OverheadCostMapper overheadCostMapper;

    public OverheadCostService(OverheadCostRepository overheadCostRepository, OverheadCostMapper overheadCostMapper) {
        this.overheadCostRepository = overheadCostRepository;
        this.overheadCostMapper = overheadCostMapper;
    }

    /**
     * Save a overheadCost.
     *
     * @param overheadCostDTO the entity to save.
     * @return the persisted entity.
     */
    public OverheadCostDTO save(OverheadCostDTO overheadCostDTO) {
        LOG.debug("Request to save OverheadCost : {}", overheadCostDTO);
        OverheadCost overheadCost = overheadCostMapper.toEntity(overheadCostDTO);
        overheadCost = overheadCostRepository.save(overheadCost);
        return overheadCostMapper.toDto(overheadCost);
    }

    /**
     * Update a overheadCost.
     *
     * @param overheadCostDTO the entity to save.
     * @return the persisted entity.
     */
    public OverheadCostDTO update(OverheadCostDTO overheadCostDTO) {
        LOG.debug("Request to update OverheadCost : {}", overheadCostDTO);
        OverheadCost overheadCost = overheadCostMapper.toEntity(overheadCostDTO);
        overheadCost = overheadCostRepository.save(overheadCost);
        return overheadCostMapper.toDto(overheadCost);
    }

    /**
     * Partially update a overheadCost.
     *
     * @param overheadCostDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OverheadCostDTO> partialUpdate(OverheadCostDTO overheadCostDTO) {
        LOG.debug("Request to partially update OverheadCost : {}", overheadCostDTO);

        return overheadCostRepository
            .findById(overheadCostDTO.getId())
            .map(existingOverheadCost -> {
                overheadCostMapper.partialUpdate(existingOverheadCost, overheadCostDTO);

                return existingOverheadCost;
            })
            .map(overheadCostRepository::save)
            .map(overheadCostMapper::toDto);
    }

    /**
     * Get all the overheadCosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OverheadCostDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all OverheadCosts");
        return overheadCostRepository.findAll(pageable).map(overheadCostMapper::toDto);
    }

    /**
     * Get one overheadCost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OverheadCostDTO> findOne(Long id) {
        LOG.debug("Request to get OverheadCost : {}", id);
        return overheadCostRepository.findById(id).map(overheadCostMapper::toDto);
    }

    /**
     * Delete the overheadCost by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete OverheadCost : {}", id);
        overheadCostRepository.deleteById(id);
    }
}
