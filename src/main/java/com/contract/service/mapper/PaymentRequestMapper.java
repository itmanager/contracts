package com.contract.service.mapper;

import com.contract.domain.ContractPhase;
import com.contract.domain.PaymentRequest;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.PaymentRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentRequest} and its DTO {@link PaymentRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface PaymentRequestMapper extends EntityMapper<PaymentRequestDTO, PaymentRequest> {
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    PaymentRequestDTO toDto(PaymentRequest s);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
