package com.contract.service;

import com.contract.domain.OutsourcingContract;
import com.contract.repository.OutsourcingContractRepository;
import com.contract.service.dto.OutsourcingContractDTO;
import com.contract.service.mapper.OutsourcingContractMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link OutsourcingContract}.
 */
@Service
@Transactional
public class OutsourcingContractService {

    private static final Logger LOG = LoggerFactory.getLogger(OutsourcingContractService.class);

    private final OutsourcingContractRepository outsourcingContractRepository;

    private final OutsourcingContractMapper outsourcingContractMapper;

    public OutsourcingContractService(
        OutsourcingContractRepository outsourcingContractRepository,
        OutsourcingContractMapper outsourcingContractMapper
    ) {
        this.outsourcingContractRepository = outsourcingContractRepository;
        this.outsourcingContractMapper = outsourcingContractMapper;
    }

    /**
     * Save a outsourcingContract.
     *
     * @param outsourcingContractDTO the entity to save.
     * @return the persisted entity.
     */
    public OutsourcingContractDTO save(OutsourcingContractDTO outsourcingContractDTO) {
        LOG.debug("Request to save OutsourcingContract : {}", outsourcingContractDTO);
        OutsourcingContract outsourcingContract = outsourcingContractMapper.toEntity(outsourcingContractDTO);
        outsourcingContract = outsourcingContractRepository.save(outsourcingContract);
        return outsourcingContractMapper.toDto(outsourcingContract);
    }

    /**
     * Update a outsourcingContract.
     *
     * @param outsourcingContractDTO the entity to save.
     * @return the persisted entity.
     */
    public OutsourcingContractDTO update(OutsourcingContractDTO outsourcingContractDTO) {
        LOG.debug("Request to update OutsourcingContract : {}", outsourcingContractDTO);
        OutsourcingContract outsourcingContract = outsourcingContractMapper.toEntity(outsourcingContractDTO);
        outsourcingContract = outsourcingContractRepository.save(outsourcingContract);
        return outsourcingContractMapper.toDto(outsourcingContract);
    }

    /**
     * Partially update a outsourcingContract.
     *
     * @param outsourcingContractDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OutsourcingContractDTO> partialUpdate(OutsourcingContractDTO outsourcingContractDTO) {
        LOG.debug("Request to partially update OutsourcingContract : {}", outsourcingContractDTO);

        return outsourcingContractRepository
            .findById(outsourcingContractDTO.getId())
            .map(existingOutsourcingContract -> {
                outsourcingContractMapper.partialUpdate(existingOutsourcingContract, outsourcingContractDTO);

                return existingOutsourcingContract;
            })
            .map(outsourcingContractRepository::save)
            .map(outsourcingContractMapper::toDto);
    }

    /**
     * Get all the outsourcingContracts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OutsourcingContractDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all OutsourcingContracts");
        return outsourcingContractRepository.findAll(pageable).map(outsourcingContractMapper::toDto);
    }

    /**
     * Get one outsourcingContract by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OutsourcingContractDTO> findOne(Long id) {
        LOG.debug("Request to get OutsourcingContract : {}", id);
        return outsourcingContractRepository.findById(id).map(outsourcingContractMapper::toDto);
    }

    /**
     * Delete the outsourcingContract by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete OutsourcingContract : {}", id);
        outsourcingContractRepository.deleteById(id);
    }
}
