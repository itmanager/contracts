package com.contract.service;

import com.contract.domain.RevisionHistory;
import com.contract.repository.RevisionHistoryRepository;
import com.contract.service.dto.RevisionHistoryDTO;
import com.contract.service.mapper.RevisionHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RevisionHistory}.
 */
@Service
@Transactional
public class RevisionHistoryService {

    private static final Logger LOG = LoggerFactory.getLogger(RevisionHistoryService.class);

    private final RevisionHistoryRepository revisionHistoryRepository;

    private final RevisionHistoryMapper revisionHistoryMapper;

    public RevisionHistoryService(RevisionHistoryRepository revisionHistoryRepository, RevisionHistoryMapper revisionHistoryMapper) {
        this.revisionHistoryRepository = revisionHistoryRepository;
        this.revisionHistoryMapper = revisionHistoryMapper;
    }

    /**
     * Save a revisionHistory.
     *
     * @param revisionHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public RevisionHistoryDTO save(RevisionHistoryDTO revisionHistoryDTO) {
        LOG.debug("Request to save RevisionHistory : {}", revisionHistoryDTO);
        RevisionHistory revisionHistory = revisionHistoryMapper.toEntity(revisionHistoryDTO);
        revisionHistory = revisionHistoryRepository.save(revisionHistory);
        return revisionHistoryMapper.toDto(revisionHistory);
    }

    /**
     * Update a revisionHistory.
     *
     * @param revisionHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    public RevisionHistoryDTO update(RevisionHistoryDTO revisionHistoryDTO) {
        LOG.debug("Request to update RevisionHistory : {}", revisionHistoryDTO);
        RevisionHistory revisionHistory = revisionHistoryMapper.toEntity(revisionHistoryDTO);
        revisionHistory = revisionHistoryRepository.save(revisionHistory);
        return revisionHistoryMapper.toDto(revisionHistory);
    }

    /**
     * Partially update a revisionHistory.
     *
     * @param revisionHistoryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RevisionHistoryDTO> partialUpdate(RevisionHistoryDTO revisionHistoryDTO) {
        LOG.debug("Request to partially update RevisionHistory : {}", revisionHistoryDTO);

        return revisionHistoryRepository
            .findById(revisionHistoryDTO.getId())
            .map(existingRevisionHistory -> {
                revisionHistoryMapper.partialUpdate(existingRevisionHistory, revisionHistoryDTO);

                return existingRevisionHistory;
            })
            .map(revisionHistoryRepository::save)
            .map(revisionHistoryMapper::toDto);
    }

    /**
     * Get all the revisionHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RevisionHistoryDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all RevisionHistories");
        return revisionHistoryRepository.findAll(pageable).map(revisionHistoryMapper::toDto);
    }

    /**
     * Get one revisionHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RevisionHistoryDTO> findOne(Long id) {
        LOG.debug("Request to get RevisionHistory : {}", id);
        return revisionHistoryRepository.findById(id).map(revisionHistoryMapper::toDto);
    }

    /**
     * Delete the revisionHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete RevisionHistory : {}", id);
        revisionHistoryRepository.deleteById(id);
    }
}
