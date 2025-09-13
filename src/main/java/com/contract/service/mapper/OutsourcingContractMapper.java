package com.contract.service.mapper;

import com.contract.domain.OutsourcingContract;
import com.contract.service.dto.OutsourcingContractDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OutsourcingContract} and its DTO {@link OutsourcingContractDTO}.
 */
@Mapper(componentModel = "spring")
public interface OutsourcingContractMapper extends EntityMapper<OutsourcingContractDTO, OutsourcingContract> {}
