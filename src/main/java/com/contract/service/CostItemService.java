package com.contract.service;

import com.contract.domain.CostItem;
import com.contract.repository.CostItemRepository;
import com.contract.service.dto.CostItemDTO;
import com.contract.service.mapper.CostItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CostItem}.
 */
@Service
@Transactional
public class CostItemService {

    private static final Logger LOG = LoggerFactory.getLogger(CostItemService.class);

    private final CostItemRepository costItemRepository;

    private final CostItemMapper costItemMapper;

    public CostItemService(CostItemRepository costItemRepository, CostItemMapper costItemMapper) {
        this.costItemRepository = costItemRepository;
        this.costItemMapper = costItemMapper;
    }

    /**
     * Save a costItem.
     *
     * @param costItemDTO the entity to save.
     * @return the persisted entity.
     */
    public CostItemDTO save(CostItemDTO costItemDTO) {
        LOG.debug("Request to save CostItem : {}", costItemDTO);
        CostItem costItem = costItemMapper.toEntity(costItemDTO);
        costItem = costItemRepository.save(costItem);
        return costItemMapper.toDto(costItem);
    }

    /**
     * Update a costItem.
     *
     * @param costItemDTO the entity to save.
     * @return the persisted entity.
     */
    public CostItemDTO update(CostItemDTO costItemDTO) {
        LOG.debug("Request to update CostItem : {}", costItemDTO);
        CostItem costItem = costItemMapper.toEntity(costItemDTO);
        costItem = costItemRepository.save(costItem);
        return costItemMapper.toDto(costItem);
    }

    /**
     * Partially update a costItem.
     *
     * @param costItemDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CostItemDTO> partialUpdate(CostItemDTO costItemDTO) {
        LOG.debug("Request to partially update CostItem : {}", costItemDTO);

        return costItemRepository
            .findById(costItemDTO.getId())
            .map(existingCostItem -> {
                costItemMapper.partialUpdate(existingCostItem, costItemDTO);

                return existingCostItem;
            })
            .map(costItemRepository::save)
            .map(costItemMapper::toDto);
    }

    /**
     * Get all the costItems.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CostItemDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all CostItems");
        return costItemRepository.findAll(pageable).map(costItemMapper::toDto);
    }

    /**
     * Get one costItem by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CostItemDTO> findOne(Long id) {
        LOG.debug("Request to get CostItem : {}", id);
        return costItemRepository.findById(id).map(costItemMapper::toDto);
    }

    /**
     * Delete the costItem by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete CostItem : {}", id);
        costItemRepository.deleteById(id);
    }
}
