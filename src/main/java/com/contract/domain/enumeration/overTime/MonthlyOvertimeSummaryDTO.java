package com.contract.domain.enumeration.overTime;

// MonthlyOvertimeSummaryDTO.java
public interface MonthlyOvertimeSummaryDTO {
    Integer getYear();

    Integer getMonth();

    String getMonthName();

    Double getTotalOvertime();

    Long getEmployeeCount();

    Long getOvertimeDaysCount();
}
