package com.contract.service;

import com.contract.domain.SupervisorComment;
import com.contract.repository.SupervisorCommentRepository;
import com.contract.service.dto.SupervisorCommentDTO;
import com.contract.service.mapper.SupervisorCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SupervisorComment}.
 */
@Service
@Transactional
public class SupervisorCommentService {

    private static final Logger LOG = LoggerFactory.getLogger(SupervisorCommentService.class);

    private final SupervisorCommentRepository supervisorCommentRepository;

    private final SupervisorCommentMapper supervisorCommentMapper;

    public SupervisorCommentService(
        SupervisorCommentRepository supervisorCommentRepository,
        SupervisorCommentMapper supervisorCommentMapper
    ) {
        this.supervisorCommentRepository = supervisorCommentRepository;
        this.supervisorCommentMapper = supervisorCommentMapper;
    }

    /**
     * Save a supervisorComment.
     *
     * @param supervisorCommentDTO the entity to save.
     * @return the persisted entity.
     */
    public SupervisorCommentDTO save(SupervisorCommentDTO supervisorCommentDTO) {
        LOG.debug("Request to save SupervisorComment : {}", supervisorCommentDTO);
        SupervisorComment supervisorComment = supervisorCommentMapper.toEntity(supervisorCommentDTO);
        supervisorComment = supervisorCommentRepository.save(supervisorComment);
        return supervisorCommentMapper.toDto(supervisorComment);
    }

    /**
     * Update a supervisorComment.
     *
     * @param supervisorCommentDTO the entity to save.
     * @return the persisted entity.
     */
    public SupervisorCommentDTO update(SupervisorCommentDTO supervisorCommentDTO) {
        LOG.debug("Request to update SupervisorComment : {}", supervisorCommentDTO);
        SupervisorComment supervisorComment = supervisorCommentMapper.toEntity(supervisorCommentDTO);
        supervisorComment = supervisorCommentRepository.save(supervisorComment);
        return supervisorCommentMapper.toDto(supervisorComment);
    }

    /**
     * Partially update a supervisorComment.
     *
     * @param supervisorCommentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SupervisorCommentDTO> partialUpdate(SupervisorCommentDTO supervisorCommentDTO) {
        LOG.debug("Request to partially update SupervisorComment : {}", supervisorCommentDTO);

        return supervisorCommentRepository
            .findById(supervisorCommentDTO.getId())
            .map(existingSupervisorComment -> {
                supervisorCommentMapper.partialUpdate(existingSupervisorComment, supervisorCommentDTO);

                return existingSupervisorComment;
            })
            .map(supervisorCommentRepository::save)
            .map(supervisorCommentMapper::toDto);
    }

    /**
     * Get all the supervisorComments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SupervisorCommentDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all SupervisorComments");
        return supervisorCommentRepository.findAll(pageable).map(supervisorCommentMapper::toDto);
    }

    /**
     * Get one supervisorComment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SupervisorCommentDTO> findOne(Long id) {
        LOG.debug("Request to get SupervisorComment : {}", id);
        return supervisorCommentRepository.findById(id).map(supervisorCommentMapper::toDto);
    }

    /**
     * Delete the supervisorComment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete SupervisorComment : {}", id);
        supervisorCommentRepository.deleteById(id);
    }
}
