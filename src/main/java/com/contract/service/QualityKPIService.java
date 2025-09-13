package com.contract.service;

import com.contract.domain.QualityKPI;
import com.contract.repository.QualityKPIRepository;
import com.contract.service.dto.QualityKPIDTO;
import com.contract.service.mapper.QualityKPIMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QualityKPI}.
 */
@Service
@Transactional
public class QualityKPIService {

    private static final Logger LOG = LoggerFactory.getLogger(QualityKPIService.class);

    private final QualityKPIRepository qualityKPIRepository;

    private final QualityKPIMapper qualityKPIMapper;

    public QualityKPIService(QualityKPIRepository qualityKPIRepository, QualityKPIMapper qualityKPIMapper) {
        this.qualityKPIRepository = qualityKPIRepository;
        this.qualityKPIMapper = qualityKPIMapper;
    }

    /**
     * Save a qualityKPI.
     *
     * @param qualityKPIDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityKPIDTO save(QualityKPIDTO qualityKPIDTO) {
        LOG.debug("Request to save QualityKPI : {}", qualityKPIDTO);
        QualityKPI qualityKPI = qualityKPIMapper.toEntity(qualityKPIDTO);
        qualityKPI = qualityKPIRepository.save(qualityKPI);
        return qualityKPIMapper.toDto(qualityKPI);
    }

    /**
     * Update a qualityKPI.
     *
     * @param qualityKPIDTO the entity to save.
     * @return the persisted entity.
     */
    public QualityKPIDTO update(QualityKPIDTO qualityKPIDTO) {
        LOG.debug("Request to update QualityKPI : {}", qualityKPIDTO);
        QualityKPI qualityKPI = qualityKPIMapper.toEntity(qualityKPIDTO);
        qualityKPI = qualityKPIRepository.save(qualityKPI);
        return qualityKPIMapper.toDto(qualityKPI);
    }

    /**
     * Partially update a qualityKPI.
     *
     * @param qualityKPIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<QualityKPIDTO> partialUpdate(QualityKPIDTO qualityKPIDTO) {
        LOG.debug("Request to partially update QualityKPI : {}", qualityKPIDTO);

        return qualityKPIRepository
            .findById(qualityKPIDTO.getId())
            .map(existingQualityKPI -> {
                qualityKPIMapper.partialUpdate(existingQualityKPI, qualityKPIDTO);

                return existingQualityKPI;
            })
            .map(qualityKPIRepository::save)
            .map(qualityKPIMapper::toDto);
    }

    /**
     * Get all the qualityKPIS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QualityKPIDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all QualityKPIS");
        return qualityKPIRepository.findAll(pageable).map(qualityKPIMapper::toDto);
    }

    /**
     * Get one qualityKPI by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QualityKPIDTO> findOne(Long id) {
        LOG.debug("Request to get QualityKPI : {}", id);
        return qualityKPIRepository.findById(id).map(qualityKPIMapper::toDto);
    }

    /**
     * Delete the qualityKPI by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete QualityKPI : {}", id);
        qualityKPIRepository.deleteById(id);
    }
}
