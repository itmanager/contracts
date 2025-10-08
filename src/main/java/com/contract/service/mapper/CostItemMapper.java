package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.CostCategory;
import com.contract.domain.CostItem;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.CostCategoryDTO;
import com.contract.service.dto.CostItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CostItem} and its DTO {@link CostItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface CostItemMapper extends EntityMapper<CostItemDTO, CostItem> {
    @Mapping(target = "costCategory", source = "costCategory", qualifiedByName = "costCategoryId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    CostItemDTO toDto(CostItem s);

    @Named("costCategoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CostCategoryDTO toDtoCostCategoryId(CostCategory costCategory);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractPhaseId(Contract contract);

}
