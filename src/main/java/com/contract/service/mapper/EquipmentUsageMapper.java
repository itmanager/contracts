package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.EquipmentUsage;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.EquipmentUsageDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link EquipmentUsage} and its DTO {@link EquipmentUsageDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentUsageMapper extends EntityMapper<EquipmentUsageDTO, EquipmentUsage> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    EquipmentUsageDTO toDto(EquipmentUsage s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

}
