package com.contract.service.mapper;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.QualityAssessment;
import com.contract.domain.QualityImprovement;
import com.contract.service.dto.ContractDTO;
import com.contract.service.dto.ContractPhaseDTO;
import com.contract.service.dto.QualityAssessmentDTO;
import com.contract.service.dto.QualityImprovementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link QualityImprovement} and its DTO {@link QualityImprovementDTO}.
 */
@Mapper(componentModel = "spring")
public interface QualityImprovementMapper extends EntityMapper<QualityImprovementDTO, QualityImprovement> {
    @Mapping(target = "contract", source = "contract", qualifiedByName = "contractId")
    @Mapping(target = "contractPhase", source = "contractPhase", qualifiedByName = "contractPhaseId")
    @Mapping(target = "qualityAssessment", source = "qualityAssessment", qualifiedByName = "qualityAssessmentId")
    QualityImprovementDTO toDto(QualityImprovement s);

    @Named("contractId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractDTO toDtoContractId(Contract contract);

    @Named("contractPhaseId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ContractPhaseDTO toDtoContractPhaseId(ContractPhase contractPhase);

    @Named("qualityAssessmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    QualityAssessmentDTO toDtoQualityAssessmentId(QualityAssessment qualityAssessment);
}
