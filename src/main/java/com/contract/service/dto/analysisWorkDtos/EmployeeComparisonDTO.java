package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeComparisonDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double actualHours;
    public Double averageHours;
    public Double difference;

    public EmployeeComparisonDTO(Long employeeId, String employeeName, Integer year, Integer month,
                                 String yearMonth, Double actualHours, Double averageHours, Double difference) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.actualHours = actualHours;
        this.averageHours = averageHours;
        this.difference = difference;
    }

    // Getters and Setters
}
