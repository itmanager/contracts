package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class FilteredPresenceDTO implements Serializable {
    private Long employeeId;
    private String employeeName;
    private Long contractId;
    private String contractNumber;
    private Integer year;
    private Integer month;
    private String yearMonth;
    private Double totalHours;
    private String employerName;
    private String contractorName;
    private String userName;

    public FilteredPresenceDTO(Long employeeId, String employeeName, Long contractId, String contractNumber,
                               Integer year, Integer month, String yearMonth, Double totalHours,
                               String employerName, String contractorName, String userName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.contractId = contractId;
        this.contractNumber = contractNumber;
        this.year = year;
        this.month = month;
        this.yearMonth = yearMonth;
        this.totalHours = totalHours;
        this.employerName = employerName;
        this.contractorName = contractorName;
        this.userName = userName;
    }

    // Getters and Setters
}
