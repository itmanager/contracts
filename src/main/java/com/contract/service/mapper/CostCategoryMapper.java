package com.contract.service.mapper;

import com.contract.domain.CostCategory;
import com.contract.service.dto.CostCategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CostCategory} and its DTO {@link CostCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CostCategoryMapper extends EntityMapper<CostCategoryDTO, CostCategory> {}
