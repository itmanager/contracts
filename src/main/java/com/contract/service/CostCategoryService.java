package com.contract.service;

import com.contract.domain.CostCategory;
import com.contract.repository.CostCategoryRepository;
import com.contract.service.dto.CostCategoryDTO;
import com.contract.service.mapper.CostCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CostCategory}.
 */
@Service
@Transactional
public class CostCategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CostCategoryService.class);

    private final CostCategoryRepository costCategoryRepository;

    private final CostCategoryMapper costCategoryMapper;

    public CostCategoryService(CostCategoryRepository costCategoryRepository, CostCategoryMapper costCategoryMapper) {
        this.costCategoryRepository = costCategoryRepository;
        this.costCategoryMapper = costCategoryMapper;
    }

    /**
     * Save a costCategory.
     *
     * @param costCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public CostCategoryDTO save(CostCategoryDTO costCategoryDTO) {
        LOG.debug("Request to save CostCategory : {}", costCategoryDTO);
        CostCategory costCategory = costCategoryMapper.toEntity(costCategoryDTO);
        costCategory = costCategoryRepository.save(costCategory);
        return costCategoryMapper.toDto(costCategory);
    }

    /**
     * Update a costCategory.
     *
     * @param costCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public CostCategoryDTO update(CostCategoryDTO costCategoryDTO) {
        LOG.debug("Request to update CostCategory : {}", costCategoryDTO);
        CostCategory costCategory = costCategoryMapper.toEntity(costCategoryDTO);
        costCategory = costCategoryRepository.save(costCategory);
        return costCategoryMapper.toDto(costCategory);
    }

    /**
     * Partially update a costCategory.
     *
     * @param costCategoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CostCategoryDTO> partialUpdate(CostCategoryDTO costCategoryDTO) {
        LOG.debug("Request to partially update CostCategory : {}", costCategoryDTO);

        return costCategoryRepository
            .findById(costCategoryDTO.getId())
            .map(existingCostCategory -> {
                costCategoryMapper.partialUpdate(existingCostCategory, costCategoryDTO);

                return existingCostCategory;
            })
            .map(costCategoryRepository::save)
            .map(costCategoryMapper::toDto);
    }

    /**
     * Get all the costCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CostCategoryDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all CostCategories");
        return costCategoryRepository.findAll(pageable).map(costCategoryMapper::toDto);
    }

    /**
     * Get one costCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CostCategoryDTO> findOne(Long id) {
        LOG.debug("Request to get CostCategory : {}", id);
        return costCategoryRepository.findById(id).map(costCategoryMapper::toDto);
    }

    /**
     * Delete the costCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete CostCategory : {}", id);
        costCategoryRepository.deleteById(id);
    }
}
