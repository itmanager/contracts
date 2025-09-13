package com.contract.service;

import com.contract.domain.MonthlyPhaseProgress;
import com.contract.repository.MonthlyPhaseProgressRepository;
import com.contract.service.dto.MonthlyPhaseProgressDTO;
import com.contract.service.mapper.MonthlyPhaseProgressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MonthlyPhaseProgress}.
 */
@Service
@Transactional
public class MonthlyPhaseProgressService {

    private static final Logger LOG = LoggerFactory.getLogger(MonthlyPhaseProgressService.class);

    private final MonthlyPhaseProgressRepository monthlyPhaseProgressRepository;

    private final MonthlyPhaseProgressMapper monthlyPhaseProgressMapper;

    public MonthlyPhaseProgressService(
        MonthlyPhaseProgressRepository monthlyPhaseProgressRepository,
        MonthlyPhaseProgressMapper monthlyPhaseProgressMapper
    ) {
        this.monthlyPhaseProgressRepository = monthlyPhaseProgressRepository;
        this.monthlyPhaseProgressMapper = monthlyPhaseProgressMapper;
    }

    /**
     * Save a monthlyPhaseProgress.
     *
     * @param monthlyPhaseProgressDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPhaseProgressDTO save(MonthlyPhaseProgressDTO monthlyPhaseProgressDTO) {
        LOG.debug("Request to save MonthlyPhaseProgress : {}", monthlyPhaseProgressDTO);
        MonthlyPhaseProgress monthlyPhaseProgress = monthlyPhaseProgressMapper.toEntity(monthlyPhaseProgressDTO);
        monthlyPhaseProgress = monthlyPhaseProgressRepository.save(monthlyPhaseProgress);
        return monthlyPhaseProgressMapper.toDto(monthlyPhaseProgress);
    }

    /**
     * Update a monthlyPhaseProgress.
     *
     * @param monthlyPhaseProgressDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPhaseProgressDTO update(MonthlyPhaseProgressDTO monthlyPhaseProgressDTO) {
        LOG.debug("Request to update MonthlyPhaseProgress : {}", monthlyPhaseProgressDTO);
        MonthlyPhaseProgress monthlyPhaseProgress = monthlyPhaseProgressMapper.toEntity(monthlyPhaseProgressDTO);
        monthlyPhaseProgress = monthlyPhaseProgressRepository.save(monthlyPhaseProgress);
        return monthlyPhaseProgressMapper.toDto(monthlyPhaseProgress);
    }

    /**
     * Partially update a monthlyPhaseProgress.
     *
     * @param monthlyPhaseProgressDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MonthlyPhaseProgressDTO> partialUpdate(MonthlyPhaseProgressDTO monthlyPhaseProgressDTO) {
        LOG.debug("Request to partially update MonthlyPhaseProgress : {}", monthlyPhaseProgressDTO);

        return monthlyPhaseProgressRepository
            .findById(monthlyPhaseProgressDTO.getId())
            .map(existingMonthlyPhaseProgress -> {
                monthlyPhaseProgressMapper.partialUpdate(existingMonthlyPhaseProgress, monthlyPhaseProgressDTO);

                return existingMonthlyPhaseProgress;
            })
            .map(monthlyPhaseProgressRepository::save)
            .map(monthlyPhaseProgressMapper::toDto);
    }

    /**
     * Get all the monthlyPhaseProgresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyPhaseProgressDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all MonthlyPhaseProgresses");
        return monthlyPhaseProgressRepository.findAll(pageable).map(monthlyPhaseProgressMapper::toDto);
    }

    /**
     * Get one monthlyPhaseProgress by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MonthlyPhaseProgressDTO> findOne(Long id) {
        LOG.debug("Request to get MonthlyPhaseProgress : {}", id);
        return monthlyPhaseProgressRepository.findById(id).map(monthlyPhaseProgressMapper::toDto);
    }

    /**
     * Delete the monthlyPhaseProgress by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete MonthlyPhaseProgress : {}", id);
        monthlyPhaseProgressRepository.deleteById(id);
    }
}
