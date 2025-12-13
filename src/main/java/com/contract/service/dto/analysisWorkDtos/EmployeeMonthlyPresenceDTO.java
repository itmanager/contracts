package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeMonthlyPresenceDTO implements Serializable {
    private Long employeeId;
    private String employeeName;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double totalHours;
    private Integer contractCount;
    private Integer entryCount;

    public EmployeeMonthlyPresenceDTO(Long employeeId, String employeeName, Integer year, Integer month, 
                                     String yearMonth, Double totalHours, Integer contractCount, Integer entryCount) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.totalHours = totalHours;
        this.contractCount = contractCount;
        this.entryCount = entryCount;
    }
    
    // Getters and Setters
}

