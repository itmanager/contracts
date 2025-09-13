package com.contract.service.mapper;

import com.contract.domain.ContractPhase;
import com.contract.domain.GanttActivity;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.GanttActivityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link GanttActivity} and its DTO {@link GanttActivityDTO}.
 */
@Mapper(componentModel = "spring")
public interface GanttActivityMapper extends EntityMapper<GanttActivityDTO, GanttActivity> {
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    GanttActivityDTO toDto(GanttActivity s);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
