package com.contract.service.mapper;

import com.contract.domain.*;
import com.contract.service.dto.*;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkTimeEntry} and its DTO {@link WorkTimeEntryDTO}.
 */
@Mapper(componentModel = "spring")
public interface WorkTimeEntryMapper extends EntityMapper<WorkTimeEntryDTO, WorkTimeEntry> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    @Mapping(target = "ganttActivity", source = "ganttActivity", qualifiedByName = "ganttActivityId")
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "supervisorId")
    WorkTimeEntryDTO toDto(WorkTimeEntry s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);

    @Named("ganttActivityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GanttActivityDTO toDtoGanttActivityId(GanttActivity ganttActivity);

    @Named("supervisorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SupervisorDTO toDtoSupervisorId(Supervisor supervisor);
}
