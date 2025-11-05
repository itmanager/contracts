package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractType;
import com.contract.domain.Supervisor;
import com.contract.domain.ThirdPartyEntity;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractTypeDTO;
import com.contract.service.dto.SupervisorDTO;
import com.contract.service.dto.ThirdPartyDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractTypeMapper extends EntityMapper<ContractTypeDTO, ContractType> {

}
