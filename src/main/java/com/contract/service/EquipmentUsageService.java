package com.contract.service;

import com.contract.domain.EquipmentUsage;
import com.contract.repository.EquipmentUsageRepository;
import com.contract.service.dto.EquipmentUsageDTO;
import com.contract.service.mapper.EquipmentUsageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EquipmentUsage}.
 */
@Service
@Transactional
public class EquipmentUsageService {

    private static final Logger LOG = LoggerFactory.getLogger(EquipmentUsageService.class);

    private final EquipmentUsageRepository equipmentUsageRepository;

    private final EquipmentUsageMapper equipmentUsageMapper;

    public EquipmentUsageService(EquipmentUsageRepository equipmentUsageRepository, EquipmentUsageMapper equipmentUsageMapper) {
        this.equipmentUsageRepository = equipmentUsageRepository;
        this.equipmentUsageMapper = equipmentUsageMapper;
    }

    /**
     * Save a equipmentUsage.
     *
     * @param equipmentUsageDTO the entity to save.
     * @return the persisted entity.
     */
    public EquipmentUsageDTO save(EquipmentUsageDTO equipmentUsageDTO) {
        LOG.debug("Request to save EquipmentUsage : {}", equipmentUsageDTO);
        EquipmentUsage equipmentUsage = equipmentUsageMapper.toEntity(equipmentUsageDTO);
        equipmentUsage = equipmentUsageRepository.save(equipmentUsage);
        return equipmentUsageMapper.toDto(equipmentUsage);
    }

    /**
     * Update a equipmentUsage.
     *
     * @param equipmentUsageDTO the entity to save.
     * @return the persisted entity.
     */
    public EquipmentUsageDTO update(EquipmentUsageDTO equipmentUsageDTO) {
        LOG.debug("Request to update EquipmentUsage : {}", equipmentUsageDTO);
        EquipmentUsage equipmentUsage = equipmentUsageMapper.toEntity(equipmentUsageDTO);
        equipmentUsage = equipmentUsageRepository.save(equipmentUsage);
        return equipmentUsageMapper.toDto(equipmentUsage);
    }

    /**
     * Partially update a equipmentUsage.
     *
     * @param equipmentUsageDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EquipmentUsageDTO> partialUpdate(EquipmentUsageDTO equipmentUsageDTO) {
        LOG.debug("Request to partially update EquipmentUsage : {}", equipmentUsageDTO);

        return equipmentUsageRepository
            .findById(equipmentUsageDTO.getId())
            .map(existingEquipmentUsage -> {
                equipmentUsageMapper.partialUpdate(existingEquipmentUsage, equipmentUsageDTO);

                return existingEquipmentUsage;
            })
            .map(equipmentUsageRepository::save)
            .map(equipmentUsageMapper::toDto);
    }

    /**
     * Get all the equipmentUsages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EquipmentUsageDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all EquipmentUsages");
        return equipmentUsageRepository.findAll(pageable).map(equipmentUsageMapper::toDto);
    }

    /**
     * Get one equipmentUsage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EquipmentUsageDTO> findOne(Long id) {
        LOG.debug("Request to get EquipmentUsage : {}", id);
        return equipmentUsageRepository.findById(id).map(equipmentUsageMapper::toDto);
    }

    /**
     * Delete the equipmentUsage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete EquipmentUsage : {}", id);
        equipmentUsageRepository.deleteById(id);
    }
}
