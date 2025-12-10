package com.contract.domain.enumeration.monthlyWorkPerformance;

// MonthlyProjectHoursDTO.java
public   interface MonthlyProjectHoursDTO {
    Long getContractId();
    Integer getYear();
    Integer getMonth();
    String getYearMonth();
    Double getTotalHours();
    Long getEmployeeCount();
    Long getEntryCount();
}

