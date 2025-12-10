package com.contract.domain.enumeration.monthlyWorkPerformance;

// EmployeeDeviationDTO.java
public interface EmployeeDeviationDTO {
    Long getEmployeeId();

    String getEmployeeName();

    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getMonthlyHours();

    Double getEmployeeAvg();

    Double getEmployeeStdDev();

    Double getDeviationFrom160();

    Double getDeviationFromOverallAvg();
}
