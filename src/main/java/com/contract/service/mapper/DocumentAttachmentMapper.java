package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.DocumentAttachment;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.DocumentAttachmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocumentAttachment} and its DTO {@link DocumentAttachmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocumentAttachmentMapper extends EntityMapper<DocumentAttachmentDTO, DocumentAttachment> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    DocumentAttachmentDTO toDto(DocumentAttachment s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
