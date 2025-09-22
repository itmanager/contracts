package com.contract.service.mapper;

import com.contract.domain.ThirdPartyEntity;
import com.contract.service.dto.ThirdPartyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ThirdPartyMapper {

    ThirdPartyMapper INSTANCE = Mappers.getMapper(ThirdPartyMapper.class);

    @Mapping(source = "isActive", target = "isActive")
    ThirdPartyDTO entityToDto(ThirdPartyEntity entity);

    @Mapping(source = "isActive", target = "isActive")
    ThirdPartyEntity dtoToEntity(ThirdPartyDTO dto);

    List<ThirdPartyDTO> entitiesToDtos(List<ThirdPartyEntity> entities);

    List<ThirdPartyEntity> dtosToEntities(List<ThirdPartyDTO> dtos);
}