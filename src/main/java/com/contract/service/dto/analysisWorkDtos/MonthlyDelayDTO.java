package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class MonthlyDelayDTO implements Serializable {
    private Long employeeId;
    private String employeeName;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double delayHours;

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
