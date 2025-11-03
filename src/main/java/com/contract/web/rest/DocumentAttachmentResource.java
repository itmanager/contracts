package com.contract.web.rest;

import com.contract.domain.DocumentAttachment;
import com.contract.service.DocumentAttachmentService;
import com.contract.service.dto.DocumentAttachmentDTO;
import com.contract.service.mapper.DocumentAttachmentMapper;
import com.contract.web.utils.HeaderUtil;
import com.contract.web.utils.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST controller for managing {@link com.contract.domain.DocumentAttachment}.
 */
@RestController
@RequestMapping("/api/document-attachments")
public class DocumentAttachmentResource {
    private static final Logger LOG = LoggerFactory.getLogger(ContractPhaseResource.class);

    private static final String ENTITY_NAME = "documentAttachments";

    @Value("${spring.application.name}")
    private String applicationName;
    private final DocumentAttachmentService documentAttachmentService;
    private final DocumentAttachmentMapper documentAttachmentMapper;

    public DocumentAttachmentResource(DocumentAttachmentService documentAttachmentService, DocumentAttachmentMapper documentAttachmentMapper) {
        this.documentAttachmentService = documentAttachmentService;
        this.documentAttachmentMapper = documentAttachmentMapper;
    }

    @PostMapping()
    public ResponseEntity<?> createDocumentAttachment(
            @RequestPart("documentAttachment") @Valid DocumentAttachmentDTO documentAttachmentDTO,
            @RequestPart("file") MultipartFile file) {

        try {
            DocumentAttachment createdAttachment = documentAttachmentService
                    .createDocumentAttachment(documentAttachmentDTO, file);

            DocumentAttachmentDTO responseDTO = documentAttachmentMapper.toDto(createdAttachment);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to create document attachment: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateDocumentAttachment(
            @PathVariable Long id,
            @RequestPart("documentAttachment") @Valid DocumentAttachmentDTO documentAttachmentDTO,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        try {
            DocumentAttachment updatedAttachment = documentAttachmentService
                    .updateDocumentAttachment(id, documentAttachmentDTO, file);

            DocumentAttachmentDTO responseDTO = documentAttachmentMapper.toDto(updatedAttachment);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to update document attachment: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DocumentAttachmentDTO>> getAllDocumentAttachments(Pageable pageable) {

        Page<DocumentAttachment> attachments = documentAttachmentService.getAllDocumentAttachments(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), attachments);

        Page<DocumentAttachmentDTO> attachmentDTOs = attachments.map(documentAttachmentMapper::toDto);

        return new ResponseEntity<>(attachmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDocumentAttachmentById(@PathVariable Long id) throws URISyntaxException {
        DocumentAttachmentDTO attachment = documentAttachmentService.getDocumentAttachmentById(id);

        if (attachment.getId()!=null) {
            return ResponseEntity.created(new URI("/api/document-attachments/" + attachment))
                    .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, attachment.getId().toString()))
                    .body(attachment);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "DocumentAttachment not found with id: " + id);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDocumentAttachment(@PathVariable Long id) {
        try {
            documentAttachmentService.deleteDocumentAttachment(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "DocumentAttachment deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to delete document attachment: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/contract/{contractId}")
    public ResponseEntity<List<DocumentAttachmentDTO>> getDocumentAttachmentsByContractId(
            @PathVariable Long contractId,Pageable pageable) {

        Page<DocumentAttachmentDTO> page = documentAttachmentService
                .getDocumentAttachmentsByContractId(contractId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) {
        try {
            Resource resource = documentAttachmentService.downloadFile(id);
            DocumentAttachmentDTO attachment = documentAttachmentService.getDocumentAttachmentById(id);

            String contentType = attachment.getFileContentType();
            String filename = attachment.getOriginalFileName();

            ResponseEntity<Resource> body = ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .body(resource);
            return body;
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
