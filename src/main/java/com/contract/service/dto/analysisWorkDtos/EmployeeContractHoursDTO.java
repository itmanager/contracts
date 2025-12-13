package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeContractHoursDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Long contractId;
    public String contractNumber;
    public String contractTitle;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double totalHours;

    public EmployeeContractHoursDTO(Long employeeId, String employeeName, Long contractId,
                                   String contractNumber, String contractTitle, Integer year,
                                   Integer month, String yearMonth, Double totalHours) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractTitle = contractTitle;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.totalHours = totalHours;
    }

    // Getters and Setters
}
