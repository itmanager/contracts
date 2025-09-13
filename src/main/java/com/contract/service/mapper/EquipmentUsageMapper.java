package com.contract.service.mapper;

import com.contract.domain.Equipment;
import com.contract.domain.EquipmentUsage;
import com.contract.domain.GanttActivity;
import com.contract.service.dto.EquipmentDTO;
import com.contract.service.dto.EquipmentUsageDTO;
import com.contract.service.dto.GanttActivityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EquipmentUsage} and its DTO {@link EquipmentUsageDTO}.
 */
@Mapper(componentModel = "spring")
public interface EquipmentUsageMapper extends EntityMapper<EquipmentUsageDTO, EquipmentUsage> {
    @Mapping(target = "equipment", source = "equipment", qualifiedByName = "equipmentId")
    @Mapping(target = "ganttActivity", source = "ganttActivity", qualifiedByName = "ganttActivityId")
    EquipmentUsageDTO toDto(EquipmentUsage s);

    @Named("equipmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EquipmentDTO toDtoEquipmentId(Equipment equipment);

    @Named("ganttActivityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GanttActivityDTO toDtoGanttActivityId(GanttActivity ganttActivity);
}
