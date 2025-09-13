package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.TestReport;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.TestReportDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TestReport} and its DTO {@link TestReportDTO}.
 */
@Mapper(componentModel = "spring")
public interface TestReportMapper extends EntityMapper<TestReportDTO, TestReport> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    TestReportDTO toDto(TestReport s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);
}
