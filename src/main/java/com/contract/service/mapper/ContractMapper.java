package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.Supervisor;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.SupervisorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContractMapper extends EntityMapper<ContractDTO, Contract> {
    @Mapping(target = "supervisor", source = "supervisor", qualifiedByName = "supervisorId")
    ContractDTO toDto(Contract s);

    @Named("supervisorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SupervisorDTO toDtoSupervisorId(Supervisor supervisor);
}
