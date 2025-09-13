package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.OverheadCost;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.OverheadCostDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OverheadCost} and its DTO {@link OverheadCostDTO}.
 */
@Mapper(componentModel = "spring")
public interface OverheadCostMapper extends EntityMapper<OverheadCostDTO, OverheadCost> {
    @Mapping(target = "allocatedTo", source = "allocatedTo", qualifiedByName = "contractId")
    @Mapping(target = "allocatedToPhase", source = "allocatedToPhase", qualifiedByName = "contractPhaseId")
    OverheadCostDTO toDto(OverheadCost s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
