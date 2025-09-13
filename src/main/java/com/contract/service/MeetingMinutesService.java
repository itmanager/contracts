package com.contract.service;

import com.contract.domain.MeetingMinutes;
import com.contract.repository.MeetingMinutesRepository;
import com.contract.service.dto.MeetingMinutesDTO;
import com.contract.service.mapper.MeetingMinutesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MeetingMinutes}.
 */
@Service
@Transactional
public class MeetingMinutesService {

    private static final Logger LOG = LoggerFactory.getLogger(MeetingMinutesService.class);

    private final MeetingMinutesRepository meetingMinutesRepository;

    private final MeetingMinutesMapper meetingMinutesMapper;

    public MeetingMinutesService(MeetingMinutesRepository meetingMinutesRepository, MeetingMinutesMapper meetingMinutesMapper) {
        this.meetingMinutesRepository = meetingMinutesRepository;
        this.meetingMinutesMapper = meetingMinutesMapper;
    }

    /**
     * Save a meetingMinutes.
     *
     * @param meetingMinutesDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingMinutesDTO save(MeetingMinutesDTO meetingMinutesDTO) {
        LOG.debug("Request to save MeetingMinutes : {}", meetingMinutesDTO);
        MeetingMinutes meetingMinutes = meetingMinutesMapper.toEntity(meetingMinutesDTO);
        meetingMinutes = meetingMinutesRepository.save(meetingMinutes);
        return meetingMinutesMapper.toDto(meetingMinutes);
    }

    /**
     * Update a meetingMinutes.
     *
     * @param meetingMinutesDTO the entity to save.
     * @return the persisted entity.
     */
    public MeetingMinutesDTO update(MeetingMinutesDTO meetingMinutesDTO) {
        LOG.debug("Request to update MeetingMinutes : {}", meetingMinutesDTO);
        MeetingMinutes meetingMinutes = meetingMinutesMapper.toEntity(meetingMinutesDTO);
        meetingMinutes = meetingMinutesRepository.save(meetingMinutes);
        return meetingMinutesMapper.toDto(meetingMinutes);
    }

    /**
     * Partially update a meetingMinutes.
     *
     * @param meetingMinutesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MeetingMinutesDTO> partialUpdate(MeetingMinutesDTO meetingMinutesDTO) {
        LOG.debug("Request to partially update MeetingMinutes : {}", meetingMinutesDTO);

        return meetingMinutesRepository
            .findById(meetingMinutesDTO.getId())
            .map(existingMeetingMinutes -> {
                meetingMinutesMapper.partialUpdate(existingMeetingMinutes, meetingMinutesDTO);

                return existingMeetingMinutes;
            })
            .map(meetingMinutesRepository::save)
            .map(meetingMinutesMapper::toDto);
    }

    /**
     * Get all the meetingMinutes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MeetingMinutesDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all MeetingMinutes");
        return meetingMinutesRepository.findAll(pageable).map(meetingMinutesMapper::toDto);
    }

    /**
     * Get one meetingMinutes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MeetingMinutesDTO> findOne(Long id) {
        LOG.debug("Request to get MeetingMinutes : {}", id);
        return meetingMinutesRepository.findById(id).map(meetingMinutesMapper::toDto);
    }

    /**
     * Delete the meetingMinutes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete MeetingMinutes : {}", id);
        meetingMinutesRepository.deleteById(id);
    }
}
