package com.contract.service.dto.analysisWorkDtos;

import java.io.Serializable;

public class FilteredPresenceDTO implements Serializable {
    public Long employeeId;
    public String employeeName;
    public Long contractId;
    public String contractNumber;
    public Integer year;
    public Integer month;
    public String yearMonth;
    public Double totalHours;
    public String employerName;
    public String contractorName;
    public String userName;

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
