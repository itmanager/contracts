package com.contract.domain.enumeration.monthlyWorkPerformance;

// MonthlyEmployeeProjectHoursDTO.java
public interface MonthlyEmployeeProjectHoursDTO {
    Long getEmployeeId();

    String getEmployeeName();

    Long getContractId();

    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getTotalHours();

    Long getEntryCount();
}
