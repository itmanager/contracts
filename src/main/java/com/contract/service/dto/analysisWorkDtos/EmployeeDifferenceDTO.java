package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeDifferenceDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double actualHours;
    public Double targetHours;
    public Double difference;

    public EmployeeDifferenceDTO(Long employeeId, String employeeName, Integer year, Integer month,
                                 String yearMonth, Double actualHours, Double targetHours, Double difference) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.actualHours = actualHours;
        this.targetHours = targetHours;
        this.difference = difference;
    }

    // Getters and Setters
}
