package com.contract.service;

import com.contract.domain.Supervisor;
import com.contract.repository.SupervisorRepository;
import com.contract.service.dto.SupervisorDTO;
import com.contract.service.mapper.SupervisorMapper;
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
 * Service Implementation for managing {@link Supervisor}.
 */
@Service
@Transactional
public class SupervisorService {

    private static final Logger LOG = LoggerFactory.getLogger(SupervisorService.class);

    private final SupervisorRepository supervisorRepository;

    private final SupervisorMapper supervisorMapper;

    public SupervisorService(SupervisorRepository supervisorRepository, SupervisorMapper supervisorMapper) {
        this.supervisorRepository = supervisorRepository;
        this.supervisorMapper = supervisorMapper;
    }

    /**
     * Save a supervisor.
     *
     * @param supervisorDTO the entity to save.
     * @return the persisted entity.
     */
    public SupervisorDTO save(SupervisorDTO supervisorDTO) {
        LOG.debug("Request to save Supervisor : {}", supervisorDTO);
        Supervisor supervisor = supervisorMapper.toEntity(supervisorDTO);
        supervisor = supervisorRepository.save(supervisor);
        return supervisorMapper.toDto(supervisor);
    }

    /**
     * Update a supervisor.
     *
     * @param supervisorDTO the entity to save.
     * @return the persisted entity.
     */
    public SupervisorDTO update(SupervisorDTO supervisorDTO) {
        LOG.debug("Request to update Supervisor : {}", supervisorDTO);
        Supervisor supervisor = supervisorMapper.toEntity(supervisorDTO);
        supervisor = supervisorRepository.save(supervisor);
        return supervisorMapper.toDto(supervisor);
    }

    /**
     * Partially update a supervisor.
     *
     * @param supervisorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SupervisorDTO> partialUpdate(SupervisorDTO supervisorDTO) {
        LOG.debug("Request to partially update Supervisor : {}", supervisorDTO);

        return supervisorRepository
            .findById(supervisorDTO.getId())
            .map(existingSupervisor -> {
                supervisorMapper.partialUpdate(existingSupervisor, supervisorDTO);

                return existingSupervisor;
            })
            .map(supervisorRepository::save)
            .map(supervisorMapper::toDto);
    }

    /**
     * Get all the supervisors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SupervisorDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Supervisors");
        return supervisorRepository.findAll(pageable).map(supervisorMapper::toDto);
    }

    /**
     * Get one supervisor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SupervisorDTO> findOne(Long id) {
        LOG.debug("Request to get Supervisor : {}", id);
        return supervisorRepository.findById(id).map(supervisorMapper::toDto);
    }

    /**
     * Delete the supervisor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Supervisor : {}", id);
        supervisorRepository.deleteById(id);
    }

    public List<SupervisorDTO> findAll() {
        LOG.debug("Request to get All ThirdParty : {}");
        return supervisorRepository.findAll().stream()
                .map(supervisorMapper::toDto).collect(Collectors.toList());
    }
}
