package com.contract.service;

import com.contract.domain.ContractPermission;
import com.contract.repository.ContractPermissionRepository;
import com.contract.service.dto.ContractPermissionDTO;
import com.contract.service.mapper.ContractPermissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ContractPermission}.
 */
@Service
@Transactional
public class ContractPermissionService {

    private static final Logger LOG = LoggerFactory.getLogger(ContractPermissionService.class);

    private final ContractPermissionRepository contractPermissionRepository;

    private final ContractPermissionMapper contractPermissionMapper;

    public ContractPermissionService(ContractPermissionRepository contractPermissionRepository, ContractPermissionMapper contractPermissionMapper) {
        this.contractPermissionRepository = contractPermissionRepository;
        this.contractPermissionMapper = contractPermissionMapper;
    }

    /**
     * Save a contractPermission.
     *
     * @param contractPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPermissionDTO save(ContractPermissionDTO contractPermissionDTO) {
        LOG.debug("Request to save ContractPermission : {}", contractPermissionDTO);
        ContractPermission contractPermission = contractPermissionMapper.toEntity(contractPermissionDTO);
        contractPermission = contractPermissionRepository.save(contractPermission);
        return contractPermissionMapper.toDto(contractPermission);
    }

    /**
     * Update a contractPermission.
     *
     * @param contractPermissionDTO the entity to save.
     * @return the persisted entity.
     */
    public ContractPermissionDTO update(ContractPermissionDTO contractPermissionDTO) {
        LOG.debug("Request to update ContractPermission : {}", contractPermissionDTO);
        ContractPermission contractPermission = contractPermissionMapper.toEntity(contractPermissionDTO);
        contractPermission = contractPermissionRepository.save(contractPermission);
        return contractPermissionMapper.toDto(contractPermission);
    }

    /**
     * Partially update a contractPermission.
     *
     * @param contractPermissionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ContractPermissionDTO> partialUpdate(ContractPermissionDTO contractPermissionDTO) {
        LOG.debug("Request to partially update ContractPermission : {}", contractPermissionDTO);

        return contractPermissionRepository
            .findById(contractPermissionDTO.getId())
            .map(existingContractPermission -> {
                contractPermissionMapper.partialUpdate(existingContractPermission, contractPermissionDTO);

                return existingContractPermission;
            })
            .map(contractPermissionRepository::save)
            .map(contractPermissionMapper::toDto);
    }

    /**
     * Get all the contractPermissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractPermissionDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all ContractPermissions");
        return contractPermissionRepository.findAll(pageable).map(contractPermissionMapper::toDto);
    }

    /**
     * Get one contractPermission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ContractPermissionDTO> findOne(Long id) {
        LOG.debug("Request to get ContractPermission : {}", id);
        return contractPermissionRepository.findById(id).map(contractPermissionMapper::toDto);
    }

    /**
     * Delete the contractPermission by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete ContractPermission : {}", id);
        contractPermissionRepository.deleteById(id);
    }

    public List<ContractPermissionDTO> findByContractId(Long contractId) {
        return contractPermissionRepository.findByContractId(contractId).stream().map(contractPermissionMapper::toDto).collect(Collectors.toList());
    }
}
