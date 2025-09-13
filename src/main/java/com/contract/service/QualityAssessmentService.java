package com.contract.service;

import com.contract.domain.QualityAssessment;
import com.contract.repository.QualityAssessmentRepository;
import com.contract.service.dto.QualityAssessmentDTO;
import com.contract.service.mapper.QualityAssessmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QualityAssessment}.
 */
@Service
@Transactional
public class QualityAssessmentService {

    private static final Logger LOG = LoggerFactory.getLogger(QualityAssessmentService.class);

    private final QualityAssessmentRepository qualityAssessmentRepository;

    private final QualityAssessmentMapper qualityAssessmentMapper;

    public QualityAssessmentService(
        QualityAssessmentRepository qualityAssessmentRepository,
        QualityAssessmentMapper qualityAssessmentMapper
    ) {
        this.qualityAssessmentRepository = qualityAssessmentRepository;
        this.qualityAssessmentMapper = qualityAssessmentMapper;
    }

    /**
     * Save a qualityAssessment.
     *
     * @param qualityAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityAssessmentDTO save(QualityAssessmentDTO qualityAssessmentDTO) {
        LOG.debug("Request to save QualityAssessment : {}", qualityAssessmentDTO);
        QualityAssessment qualityAssessment = qualityAssessmentMapper.toEntity(qualityAssessmentDTO);
        qualityAssessment = qualityAssessmentRepository.save(qualityAssessment);
        return qualityAssessmentMapper.toDto(qualityAssessment);
    }

    /**
     * Update a qualityAssessment.
     *
     * @param qualityAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityAssessmentDTO update(QualityAssessmentDTO qualityAssessmentDTO) {
        LOG.debug("Request to update QualityAssessment : {}", qualityAssessmentDTO);
        QualityAssessment qualityAssessment = qualityAssessmentMapper.toEntity(qualityAssessmentDTO);
        qualityAssessment = qualityAssessmentRepository.save(qualityAssessment);
        return qualityAssessmentMapper.toDto(qualityAssessment);
    }

    /**
     * Partially update a qualityAssessment.
     *
     * @param qualityAssessmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QualityAssessmentDTO> partialUpdate(QualityAssessmentDTO qualityAssessmentDTO) {
        LOG.debug("Request to partially update QualityAssessment : {}", qualityAssessmentDTO);

        return qualityAssessmentRepository
            .findById(qualityAssessmentDTO.getId())
            .map(existingQualityAssessment -> {
                qualityAssessmentMapper.partialUpdate(existingQualityAssessment, qualityAssessmentDTO);

                return existingQualityAssessment;
            })
            .map(qualityAssessmentRepository::save)
            .map(qualityAssessmentMapper::toDto);
    }

    /**
     * Get all the qualityAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QualityAssessmentDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all QualityAssessments");
        return qualityAssessmentRepository.findAll(pageable).map(qualityAssessmentMapper::toDto);
    }

    /**
     * Get one qualityAssessment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QualityAssessmentDTO> findOne(Long id) {
        LOG.debug("Request to get QualityAssessment : {}", id);
        return qualityAssessmentRepository.findById(id).map(qualityAssessmentMapper::toDto);
    }

    /**
     * Delete the qualityAssessment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QualityAssessment : {}", id);
        qualityAssessmentRepository.deleteById(id);
    }
}
