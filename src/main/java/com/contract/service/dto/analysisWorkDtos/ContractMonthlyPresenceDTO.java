package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractMonthlyPresenceDTO implements Serializable {
    public Long contractId;
    public String contractNumber;
    public String contractTitle;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double totalHours;
    public Double employeeCount;
    public Double entryCount;

    public ContractMonthlyPresenceDTO(Long contractId, String contractNumber, String contractTitle,
                                     Integer year, Integer month, String yearMonth, Double totalHours,
                                     Double employeeCount, Double entryCount) {
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
