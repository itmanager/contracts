package com.contract.service.mapper;

import com.contract.domain.*;
import com.contract.service.dto.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ContractMetaDataMapper extends EntityMapper<ContractMetaDataDTO, ContractMetaData>{

    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractType", source = "contractType", qualifiedByName = "contractTypeToId")
    @Mapping(target = "contractParent", source = "contractParent", qualifiedByName = "contractParentId")
    ContractMetaDataDTO toDto(ContractMetaData contractMetaData);

    @Named("contractTypeToId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractType toContractTypeId(ContractType contractType);

    @Named("contractParentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractParentId(Contract contractParent);

    @Named("contractTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractTypeDTO toDtoContractTypeId(ContractType contractType);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);
}