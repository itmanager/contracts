package com.contract.domain.enumeration.overTime;

// ProjectOvertimeMonthlyDTO.java
public interface ProjectOvertimeMonthlyDTO {
    Long getContractId();

    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getTotalOvertime();

    Long getEmployeeCount();
}
