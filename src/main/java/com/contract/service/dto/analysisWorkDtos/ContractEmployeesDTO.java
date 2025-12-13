package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractEmployeesDTO implements Serializable {
    public Long contractId;
    public String contractNumber;
    public String contractTitle;
    public Long employeeId;
    public String employeeName;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double totalHours;

    public ContractEmployeesDTO(Long contractId, String contractNumber, String contractTitle,
                               Long employeeId, String employeeName, Integer year, Integer month,
                               String yearMonth, Double totalHours) {
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractTitle = contractTitle;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.totalHours = totalHours;
    }

    // Getters and Setters
}
