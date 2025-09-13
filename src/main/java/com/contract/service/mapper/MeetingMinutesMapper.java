package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.MeetingMinutes;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.MeetingMinutesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MeetingMinutes} and its DTO {@link MeetingMinutesDTO}.
 */
@Mapper(componentModel = "spring")
public interface MeetingMinutesMapper extends EntityMapper<MeetingMinutesDTO, MeetingMinutes> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    MeetingMinutesDTO toDto(MeetingMinutes s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
