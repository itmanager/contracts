package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class MonthlyDelayDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double delayHours;

    public MonthlyDelayDTO(Long employeeId, String employeeName, Integer year, Integer month,
                          String yearMonth, Double delayHours) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.delayHours = delayHours;
    }

    // Getters and Setters
}
