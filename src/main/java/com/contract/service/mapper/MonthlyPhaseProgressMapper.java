package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.MonthlyPhaseProgress;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.MonthlyPhaseProgressDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyPhaseProgress} and its DTO {@link MonthlyPhaseProgressDTO}.
 */
@Mapper(componentModel = "spring")
public interface MonthlyPhaseProgressMapper extends EntityMapper<MonthlyPhaseProgressDTO, MonthlyPhaseProgress> {
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    MonthlyPhaseProgressDTO toDto(MonthlyPhaseProgress s);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);


    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}
