package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeMonthlyOvertimeDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double overtimeHours;

    public EmployeeMonthlyOvertimeDTO(Long employeeId, String employeeName, Integer year, Integer month,
                                      String yearMonth, Double overtimeHours) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.overtimeHours = overtimeHours;
    }

    // Getters and Setters
}
