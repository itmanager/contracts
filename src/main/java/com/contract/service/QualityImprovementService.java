package com.contract.service;

import com.contract.domain.QualityImprovement;
import com.contract.repository.QualityImprovementRepository;
import com.contract.service.dto.QualityImprovementDTO;
import com.contract.service.mapper.QualityImprovementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QualityImprovement}.
 */
@Service
@Transactional
public class QualityImprovementService {

    private static final Logger LOG = LoggerFactory.getLogger(QualityImprovementService.class);

    private final QualityImprovementRepository qualityImprovementRepository;

    private final QualityImprovementMapper qualityImprovementMapper;

    public QualityImprovementService(
        QualityImprovementRepository qualityImprovementRepository,
        QualityImprovementMapper qualityImprovementMapper
    ) {
        this.qualityImprovementRepository = qualityImprovementRepository;
        this.qualityImprovementMapper = qualityImprovementMapper;
    }

    /**
     * Save a qualityImprovement.
     *
     * @param qualityImprovementDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityImprovementDTO save(QualityImprovementDTO qualityImprovementDTO) {
        LOG.debug("Request to save QualityImprovement : {}", qualityImprovementDTO);
        QualityImprovement qualityImprovement = qualityImprovementMapper.toEntity(qualityImprovementDTO);
        qualityImprovement = qualityImprovementRepository.save(qualityImprovement);
        return qualityImprovementMapper.toDto(qualityImprovement);
    }

    /**
     * Update a qualityImprovement.
     *
     * @param qualityImprovementDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityImprovementDTO update(QualityImprovementDTO qualityImprovementDTO) {
        LOG.debug("Request to update QualityImprovement : {}", qualityImprovementDTO);
        QualityImprovement qualityImprovement = qualityImprovementMapper.toEntity(qualityImprovementDTO);
        qualityImprovement = qualityImprovementRepository.save(qualityImprovement);
        return qualityImprovementMapper.toDto(qualityImprovement);
    }

    /**
     * Partially update a qualityImprovement.
     *
     * @param qualityImprovementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QualityImprovementDTO> partialUpdate(QualityImprovementDTO qualityImprovementDTO) {
        LOG.debug("Request to partially update QualityImprovement : {}", qualityImprovementDTO);

        return qualityImprovementRepository
            .findById(qualityImprovementDTO.getId())
            .map(existingQualityImprovement -> {
                qualityImprovementMapper.partialUpdate(existingQualityImprovement, qualityImprovementDTO);

                return existingQualityImprovement;
            })
            .map(qualityImprovementRepository::save)
            .map(qualityImprovementMapper::toDto);
    }

    /**
     * Get all the qualityImprovements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QualityImprovementDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all QualityImprovements");
        return qualityImprovementRepository.findAll(pageable).map(qualityImprovementMapper::toDto);
    }

    /**
     * Get one qualityImprovement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QualityImprovementDTO> findOne(Long id) {
        LOG.debug("Request to get QualityImprovement : {}", id);
        return qualityImprovementRepository.findById(id).map(qualityImprovementMapper::toDto);
    }

    /**
     * Delete the qualityImprovement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QualityImprovement : {}", id);
        qualityImprovementRepository.deleteById(id);
    }
}
