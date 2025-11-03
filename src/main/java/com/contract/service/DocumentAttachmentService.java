package com.contract.service;

import com.contract.domain.DocumentAttachment;
import com.contract.repository.DocumentAttachmentRepository;
import com.contract.service.dto.DocumentAttachmentDTO;
import com.contract.service.mapper.DocumentAttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
@Transactional
public class DocumentAttachmentService {

    private final DocumentAttachmentRepository documentAttachmentRepository;
    private final FileStorageService fileStorageService;
    private final DocumentAttachmentMapper documentAttachmentMapper;

    @Autowired
    public DocumentAttachmentService(DocumentAttachmentRepository documentAttachmentRepository,
                                     FileStorageService fileStorageService, DocumentAttachmentMapper documentAttachmentMapper) {
        this.documentAttachmentRepository = documentAttachmentRepository;
        this.fileStorageService = fileStorageService;
        this.documentAttachmentMapper = documentAttachmentMapper;
    }

    public DocumentAttachment createDocumentAttachment(DocumentAttachmentDTO documentAttachmentDTO,
                                                       MultipartFile file) {
        // Generate file path based on contract/phase/type structure
        String customPath = generateFilePath(documentAttachmentDTO);
        String storedFileName = fileStorageService.storeFile(file, customPath);

        DocumentAttachment documentAttachment = new DocumentAttachment();
        documentAttachment = documentAttachmentMapper.toEntity(documentAttachmentDTO);
        
        documentAttachment.setFilePath(storedFileName);
        documentAttachment.setOriginalFileName(file.getOriginalFilename());
        documentAttachment.setFileSize(file.getSize());
        documentAttachment.setFileContentType(file.getContentType());
        documentAttachment.setUploadDate(LocalDateTime.now());

        return documentAttachmentRepository.save(documentAttachment);
    }

    public DocumentAttachment updateDocumentAttachment(Long id, DocumentAttachmentDTO documentAttachmentDTO, 
                                                     MultipartFile file) {
        Optional<DocumentAttachment> existingAttachment = documentAttachmentRepository.findById(id);
        
        if (existingAttachment.isPresent()) {
            DocumentAttachment documentAttachment = existingAttachment.get();
            
            // If new file is provided, update the file
            if (file != null && !file.isEmpty()) {
                // Delete old file
                fileStorageService.deleteFile(documentAttachment.getFilePath());
                
                // Store new file
                String customPath = generateFilePath(documentAttachmentDTO);
                String storedFileName = fileStorageService.storeFile(file, customPath);
                
                documentAttachment.setFilePath(storedFileName);
                documentAttachment.setOriginalFileName(file.getOriginalFilename());
                documentAttachment.setFileSize(file.getSize());
                documentAttachment.setFileContentType(file.getContentType());
            }

            return documentAttachmentRepository.save(documentAttachment);
        }
        
        throw new RuntimeException("DocumentAttachment not found with id: " + id);
    }

    public Page<DocumentAttachment> getAllDocumentAttachments( Pageable pageable) {
        return documentAttachmentRepository.findAll( pageable);
    }

    public DocumentAttachmentDTO getDocumentAttachmentById(Long id) {
        return documentAttachmentRepository.findById(id).map(documentAttachmentMapper::toDto).get();
    }

    public void deleteDocumentAttachment(Long id) {
        Optional<DocumentAttachment> documentAttachment = documentAttachmentRepository.findById(id);
        
        if (documentAttachment.isPresent()) {
            // Delete physical file
            fileStorageService.deleteFile(documentAttachment.get().getFilePath());
            // Delete database record
            documentAttachmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("DocumentAttachment not found with id: " + id);
        }
    }

    public Page<DocumentAttachment> searchDocumentAttachments(String query, Pageable pageable) {
        //return documentAttachmentRepository.search(query, pageable);
        return null;
    }

    public Page<DocumentAttachmentDTO> getDocumentAttachmentsByContractId(Long contractId, Pageable pageable) {
        return documentAttachmentRepository.findByContractId(contractId, pageable).map(documentAttachmentMapper::toDto);
    }

    public Page<DocumentAttachment> getDocumentAttachmentsByContractPhaseId(Long contractPhaseId, Pageable pageable) {
        return documentAttachmentRepository.findByContractPhaseId(contractPhaseId, pageable);
    }

    public Resource downloadFile(Long id) {
        Optional<DocumentAttachment> documentAttachment = documentAttachmentRepository.findById(id);
        
        if (documentAttachment.isPresent()) {
            return fileStorageService.loadFileAsResource(documentAttachment.get().getFilePath());
        }
        
        throw new RuntimeException("DocumentAttachment not found with id: " + id);
    }

    private String generateFilePath(DocumentAttachmentDTO dto) {
        Long contractId = dto.getContract().getId() != null ? dto.getContract().getId() : 0L;
        Long phaseId = dto.getContractPhase().getId() != null ? dto.getContractPhase().getId() : 0L;
        String type = dto.getAttachmentType() != null ? dto.getAttachmentType().name() : "OTHER";
        
        return String.format("contracts/%d/phases/%d/types/%s", contractId, phaseId, type);
    }


}