package com.contract.service.mapper;

import com.contract.domain.Supervisor;
import com.contract.service.dto.SupervisorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Supervisor} and its DTO {@link SupervisorDTO}.
 */
@Mapper(componentModel = "spring")
public interface SupervisorMapper extends EntityMapper<SupervisorDTO, Supervisor> {}
