package com.contract.service;

import com.contract.domain.GanttActivity;
import com.contract.repository.GanttActivityRepository;
import com.contract.service.dto.GanttActivityDTO;
import com.contract.service.mapper.GanttActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GanttActivity}.
 */
@Service
@Transactional
public class GanttActivityService {

    private static final Logger LOG = LoggerFactory.getLogger(GanttActivityService.class);

    private final GanttActivityRepository ganttActivityRepository;

    private final GanttActivityMapper ganttActivityMapper;

    public GanttActivityService(GanttActivityRepository ganttActivityRepository, GanttActivityMapper ganttActivityMapper) {
        this.ganttActivityRepository = ganttActivityRepository;
        this.ganttActivityMapper = ganttActivityMapper;
    }

    /**
     * Save a ganttActivity.
     *
     * @param ganttActivityDTO the entity to save.
     * @return the persisted entity.
     */
    public GanttActivityDTO save(GanttActivityDTO ganttActivityDTO) {
        LOG.debug("Request to save GanttActivity : {}", ganttActivityDTO);
        GanttActivity ganttActivity = ganttActivityMapper.toEntity(ganttActivityDTO);
        ganttActivity = ganttActivityRepository.save(ganttActivity);
        return ganttActivityMapper.toDto(ganttActivity);
    }

    /**
     * Update a ganttActivity.
     *
     * @param ganttActivityDTO the entity to save.
     * @return the persisted entity.
     */
    public GanttActivityDTO update(GanttActivityDTO ganttActivityDTO) {
        LOG.debug("Request to update GanttActivity : {}", ganttActivityDTO);
        GanttActivity ganttActivity = ganttActivityMapper.toEntity(ganttActivityDTO);
        ganttActivity = ganttActivityRepository.save(ganttActivity);
        return ganttActivityMapper.toDto(ganttActivity);
    }

    /**
     * Partially update a ganttActivity.
     *
     * @param ganttActivityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<GanttActivityDTO> partialUpdate(GanttActivityDTO ganttActivityDTO) {
        LOG.debug("Request to partially update GanttActivity : {}", ganttActivityDTO);

        return ganttActivityRepository
            .findById(ganttActivityDTO.getId())
            .map(existingGanttActivity -> {
                ganttActivityMapper.partialUpdate(existingGanttActivity, ganttActivityDTO);

                return existingGanttActivity;
            })
            .map(ganttActivityRepository::save)
            .map(ganttActivityMapper::toDto);
    }

    /**
     * Get all the ganttActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GanttActivityDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all GanttActivities");
        return ganttActivityRepository.findAll(pageable).map(ganttActivityMapper::toDto);
    }

    /**
     * Get one ganttActivity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GanttActivityDTO> findOne(Long id) {
        LOG.debug("Request to get GanttActivity : {}", id);
        return ganttActivityRepository.findById(id).map(ganttActivityMapper::toDto);
    }

    /**
     * Delete the ganttActivity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete GanttActivity : {}", id);
        ganttActivityRepository.deleteById(id);
    }
}
