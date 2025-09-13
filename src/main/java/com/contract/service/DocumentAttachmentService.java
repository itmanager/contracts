package com.contract.service;

import com.contract.domain.DocumentAttachment;
import com.contract.repository.DocumentAttachmentRepository;
import com.contract.service.dto.DocumentAttachmentDTO;
import com.contract.service.mapper.DocumentAttachmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DocumentAttachment}.
 */
@Service
@Transactional
public class DocumentAttachmentService {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentAttachmentService.class);

    private final DocumentAttachmentRepository documentAttachmentRepository;

    private final DocumentAttachmentMapper documentAttachmentMapper;

    public DocumentAttachmentService(
        DocumentAttachmentRepository documentAttachmentRepository,
        DocumentAttachmentMapper documentAttachmentMapper
    ) {
        this.documentAttachmentRepository = documentAttachmentRepository;
        this.documentAttachmentMapper = documentAttachmentMapper;
    }

    /**
     * Save a documentAttachment.
     *
     * @param documentAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public DocumentAttachmentDTO save(DocumentAttachmentDTO documentAttachmentDTO) {
        LOG.debug("Request to save DocumentAttachment : {}", documentAttachmentDTO);
        DocumentAttachment documentAttachment = documentAttachmentMapper.toEntity(documentAttachmentDTO);
        documentAttachment = documentAttachmentRepository.save(documentAttachment);
        return documentAttachmentMapper.toDto(documentAttachment);
    }

    /**
     * Update a documentAttachment.
     *
     * @param documentAttachmentDTO the entity to save.
     * @return the persisted entity.
     */
    public DocumentAttachmentDTO update(DocumentAttachmentDTO documentAttachmentDTO) {
        LOG.debug("Request to update DocumentAttachment : {}", documentAttachmentDTO);
        DocumentAttachment documentAttachment = documentAttachmentMapper.toEntity(documentAttachmentDTO);
        documentAttachment = documentAttachmentRepository.save(documentAttachment);
        return documentAttachmentMapper.toDto(documentAttachment);
    }

    /**
     * Partially update a documentAttachment.
     *
     * @param documentAttachmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DocumentAttachmentDTO> partialUpdate(DocumentAttachmentDTO documentAttachmentDTO) {
        LOG.debug("Request to partially update DocumentAttachment : {}", documentAttachmentDTO);

        return documentAttachmentRepository
            .findById(documentAttachmentDTO.getId())
            .map(existingDocumentAttachment -> {
                documentAttachmentMapper.partialUpdate(existingDocumentAttachment, documentAttachmentDTO);

                return existingDocumentAttachment;
            })
            .map(documentAttachmentRepository::save)
            .map(documentAttachmentMapper::toDto);
    }

    /**
     * Get all the documentAttachments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DocumentAttachmentDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all DocumentAttachments");
        return documentAttachmentRepository.findAll(pageable).map(documentAttachmentMapper::toDto);
    }

    /**
     * Get one documentAttachment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DocumentAttachmentDTO> findOne(Long id) {
        LOG.debug("Request to get DocumentAttachment : {}", id);
        return documentAttachmentRepository.findById(id).map(documentAttachmentMapper::toDto);
    }

    /**
     * Delete the documentAttachment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete DocumentAttachment : {}", id);
        documentAttachmentRepository.deleteById(id);
    }
}
