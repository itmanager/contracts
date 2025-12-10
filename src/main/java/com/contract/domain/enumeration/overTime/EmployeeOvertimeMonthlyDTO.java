package com.contract.domain.enumeration.overTime;

// EmployeeOvertimeMonthlyDTO.java
public interface EmployeeOvertimeMonthlyDTO {
    Long getEmployeeId();

    String getEmployeeName();

    Integer getYear();

    Integer getMonth();

    Double getTotalOvertime();
}
