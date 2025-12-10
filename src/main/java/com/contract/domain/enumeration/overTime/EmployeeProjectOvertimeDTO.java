package com.contract.domain.enumeration.overTime;

// EmployeeProjectOvertimeDTO.java
public interface EmployeeProjectOvertimeDTO {
    Long getEmployeeId();

    String getEmployeeName();

    Long getContractId();

    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getProjectOvertime();

    Double getProjectTotalHours();

    Double getOvertimePercentage();
}
