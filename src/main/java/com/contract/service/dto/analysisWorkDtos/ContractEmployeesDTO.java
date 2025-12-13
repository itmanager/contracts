package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractEmployeesDTO implements Serializable {
    private Long contractId;
    private String contractNumber;
    private String contractTitle;
    private Long employeeId;
    private String employeeName;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double totalHours;

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
