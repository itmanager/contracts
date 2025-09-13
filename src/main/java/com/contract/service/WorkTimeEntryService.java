package com.contract.service;

import com.contract.domain.WorkTimeEntry;
import com.contract.repository.WorkTimeEntryRepository;
import com.contract.service.dto.WorkTimeEntryDTO;
import com.contract.service.mapper.WorkTimeEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkTimeEntry}.
 */
@Service
@Transactional
public class WorkTimeEntryService {

    private static final Logger LOG = LoggerFactory.getLogger(WorkTimeEntryService.class);

    private final WorkTimeEntryRepository workTimeEntryRepository;

    private final WorkTimeEntryMapper workTimeEntryMapper;

    public WorkTimeEntryService(WorkTimeEntryRepository workTimeEntryRepository, WorkTimeEntryMapper workTimeEntryMapper) {
        this.workTimeEntryRepository = workTimeEntryRepository;
        this.workTimeEntryMapper = workTimeEntryMapper;
    }

    /**
     * Save a workTimeEntry.
     *
     * @param workTimeEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkTimeEntryDTO save(WorkTimeEntryDTO workTimeEntryDTO) {
        LOG.debug("Request to save WorkTimeEntry : {}", workTimeEntryDTO);
        WorkTimeEntry workTimeEntry = workTimeEntryMapper.toEntity(workTimeEntryDTO);
        workTimeEntry = workTimeEntryRepository.save(workTimeEntry);
        return workTimeEntryMapper.toDto(workTimeEntry);
    }

    /**
     * Update a workTimeEntry.
     *
     * @param workTimeEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public WorkTimeEntryDTO update(WorkTimeEntryDTO workTimeEntryDTO) {
        LOG.debug("Request to update WorkTimeEntry : {}", workTimeEntryDTO);
        WorkTimeEntry workTimeEntry = workTimeEntryMapper.toEntity(workTimeEntryDTO);
        workTimeEntry = workTimeEntryRepository.save(workTimeEntry);
        return workTimeEntryMapper.toDto(workTimeEntry);
    }

    /**
     * Partially update a workTimeEntry.
     *
     * @param workTimeEntryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<WorkTimeEntryDTO> partialUpdate(WorkTimeEntryDTO workTimeEntryDTO) {
        LOG.debug("Request to partially update WorkTimeEntry : {}", workTimeEntryDTO);

        return workTimeEntryRepository
            .findById(workTimeEntryDTO.getId())
            .map(existingWorkTimeEntry -> {
                workTimeEntryMapper.partialUpdate(existingWorkTimeEntry, workTimeEntryDTO);

                return existingWorkTimeEntry;
            })
            .map(workTimeEntryRepository::save)
            .map(workTimeEntryMapper::toDto);
    }

    /**
     * Get all the workTimeEntries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkTimeEntryDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all WorkTimeEntries");
        return workTimeEntryRepository.findAll(pageable).map(workTimeEntryMapper::toDto);
    }

    /**
     * Get one workTimeEntry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<WorkTimeEntryDTO> findOne(Long id) {
        LOG.debug("Request to get WorkTimeEntry : {}", id);
        return workTimeEntryRepository.findById(id).map(workTimeEntryMapper::toDto);
    }

    /**
     * Delete the workTimeEntry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete WorkTimeEntry : {}", id);
        workTimeEntryRepository.deleteById(id);
    }
}
