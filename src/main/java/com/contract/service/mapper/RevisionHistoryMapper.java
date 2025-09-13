package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.RevisionHistory;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.RevisionHistoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RevisionHistory} and its DTO {@link RevisionHistoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface RevisionHistoryMapper extends EntityMapper<RevisionHistoryDTO, RevisionHistory> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    RevisionHistoryDTO toDto(RevisionHistory s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
