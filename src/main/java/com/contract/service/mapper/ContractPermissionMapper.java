package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPermission;
import com.contract.domain.ContractPhase;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPermissionDTO;
import com.contract.service.dto.ContractPhaseDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link ContractPermission} and its DTO {@link ContractPermissionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractPermissionMapper extends EntityMapper<ContractPermissionDTO, ContractPermission> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    ContractPermissionDTO toDto(ContractPermission s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}
