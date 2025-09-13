package com.contract.service.mapper;

import com.contract.domain.Employee;
import com.contract.domain.GanttActivity;
import com.contract.domain.Supervisor;
import com.contract.domain.WorkTimeEntry;
import com.contract.service.dto.EmployeeDTO;
import com.contract.service.dto.GanttActivityDTO;
import com.contract.service.dto.SupervisorDTO;
import com.contract.service.dto.WorkTimeEntryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkTimeEntry} and its DTO {@link WorkTimeEntryDTO}.
 */
@Mapper(componentModel = "spring")
public interface WorkTimeEntryMapper extends EntityMapper<WorkTimeEntryDTO, WorkTimeEntry> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeId")
    @Mapping(target = "ganttActivity", source = "ganttActivity", qualifiedByName = "ganttActivityId")
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "supervisorId")
    WorkTimeEntryDTO toDto(WorkTimeEntry s);

    @Named("employeeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EmployeeDTO toDtoEmployeeId(Employee employee);

    @Named("ganttActivityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    GanttActivityDTO toDtoGanttActivityId(GanttActivity ganttActivity);

    @Named("supervisorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SupervisorDTO toDtoSupervisorId(Supervisor supervisor);
}
