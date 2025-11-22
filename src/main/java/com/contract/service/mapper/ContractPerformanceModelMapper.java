package com.contract.service.mapper;

import com.contract.domain.ContractPerformanceModel;
import com.contract.service.dto.ContractPerformanceModelDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContractPerformanceModelMapper  extends EntityMapper<ContractPerformanceModelDTO, ContractPerformanceModel>{
}