package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractMonthlyPresenceDTO implements Serializable {
    private Long contractId;
    private String contractNumber;
    private String contractTitle;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double totalHours;
    private Integer employeeCount;
    private Integer entryCount;

    public ContractMonthlyPresenceDTO(Long contractId, String contractNumber, String contractTitle,
                                      Integer year, Integer month, String yearMonth, Double totalHours,
                                      Integer employeeCount, Integer entryCount) {
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractTitle = contractTitle;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.totalHours = totalHours;
        this.employeeCount = employeeCount;
        this.entryCount = entryCount;
    }

    // Getters and Setters
}
