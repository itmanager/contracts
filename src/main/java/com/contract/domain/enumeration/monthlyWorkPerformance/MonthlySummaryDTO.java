package com.contract.domain.enumeration.monthlyWorkPerformance;

// MonthlySummaryDTO.java
public interface MonthlySummaryDTO {
    Integer getYear();

    Integer getMonth();

    String getYearMonth();

    Double getTotalHours();

    Long getTotalEmployees();

    Long getTotalProjects();

    Double getAvgHoursPerEmployee();

    Double getAvgHoursPerProject();
}
