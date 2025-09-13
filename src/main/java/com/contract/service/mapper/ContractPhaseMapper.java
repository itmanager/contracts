package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContractPhase} and its DTO {@link ContractPhaseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractPhaseMapper extends EntityMapper<ContractPhaseDTO, ContractPhase> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    ContractPhaseDTO toDto(ContractPhase s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}
