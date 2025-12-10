package com.contract.domain.enumeration.monthlyWorkPerformance;

// MonthlyEmployeeHoursDTO.java
public interface MonthlyEmployeeHoursDTO {
    Long getEmployeeId();

    String getEmployeeName();

    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getTotalHours();

    Long getProjectCount();

    Long getEntryCount();
}
