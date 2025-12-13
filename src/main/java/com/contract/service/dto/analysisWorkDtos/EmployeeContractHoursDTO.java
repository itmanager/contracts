package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class EmployeeContractHoursDTO implements Serializable {
    private Long employeeId;
    private String employeeName;
    private Long contractId;
    private String contractNumber;
    private String contractTitle;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double totalHours;

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
