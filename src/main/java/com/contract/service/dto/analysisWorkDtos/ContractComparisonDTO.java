package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractComparisonDTO implements Serializable {
    public Long contractId;
    public String contractNumber;
    public String contractTitle;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double actualHours;
    public Double averageHours;
    public Double difference;

    public ContractComparisonDTO(Long contractId, String contractNumber, String contractTitle,
                                 Integer year, Integer month, String yearMonth, Double actualHours,
                                 Double averageHours, Double difference) {
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractTitle = contractTitle;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.actualHours = actualHours;
        this.averageHours = averageHours;
        this.difference = difference;
    }

    // Getters and Setters
}
