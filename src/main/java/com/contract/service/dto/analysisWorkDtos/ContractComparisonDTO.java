package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractComparisonDTO implements Serializable {
    private Long contractId;
    private String contractNumber;
    private String contractTitle;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double actualHours;
    private Double averageHours;
    private Double difference;

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
