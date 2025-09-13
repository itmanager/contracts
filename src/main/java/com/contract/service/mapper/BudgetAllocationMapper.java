package com.contract.service.mapper;

import com.contract.domain.BudgetAllocation;
import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.service.dto.BudgetAllocationDTO;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BudgetAllocation} and its DTO {@link BudgetAllocationDTO}.
 */
@Mapper(componentModel = "spring")
public interface BudgetAllocationMapper extends EntityMapper<BudgetAllocationDTO, BudgetAllocation> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    BudgetAllocationDTO toDto(BudgetAllocation s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
