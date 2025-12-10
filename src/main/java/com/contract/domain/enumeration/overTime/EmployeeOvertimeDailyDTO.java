package com.contract.domain.enumeration.overTime;

// EmployeeOvertimeDailyDTO.java
public interface EmployeeOvertimeDailyDTO {
    Long getEmployeeId();
    String getEmployeeName();
    Integer getYear();
    Integer getMonth();
    Integer getDay();
    Double getTotalDailyHours();
    Double getOvertimeHours();
}

