package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class ContractMonthlyOvertimeDTO implements Serializable {
    private Long contractId;
    private String contractNumber;
    private String contractTitle;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double overtimeHours;

    public ContractMonthlyOvertimeDTO(Long contractId, String contractNumber, String contractTitle,
                                      Integer year, Integer month, String yearMonth, Double overtimeHours) {
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.contractTitle = contractTitle;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.overtimeHours = overtimeHours;
    }

    // Getters and Setters
}
