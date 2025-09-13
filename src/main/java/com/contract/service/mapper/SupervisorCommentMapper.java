package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.Supervisor;
import com.contract.domain.SupervisorComment;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.SupervisorCommentDTO;
import com.contract.service.dto.SupervisorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SupervisorComment} and its DTO {@link SupervisorCommentDTO}.
 */
@Mapper(componentModel = "spring")
public interface SupervisorCommentMapper extends EntityMapper<SupervisorCommentDTO, SupervisorComment> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    @Mapping(target = "supervisor", source = "supervisor", qualifiedByName = "supervisorId")
    SupervisorCommentDTO toDto(SupervisorComment s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);

    @Named("supervisorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SupervisorDTO toDtoSupervisorId(Supervisor supervisor);
}
