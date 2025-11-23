package com.contract.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryContractModel {
    // پارامترهای فیلتر قراردادها
    private List<Long> pContractIds;
    private List<Long> pEmployerIds;
    private List<Long> pContractorIds;
    private List<Long> pUserIds;
    private List<Long> pSupervisorIds;
    private Long pFromDate;
    private Long pToDate;
    private List<Long> pFiveYearPlanIds;
    private List<Long> pAnnualPlanIds;
    private List<Long> pContractTypeIds;
    private List<String> pOutSourceTypes;

    // پارامترهای گزارش عملکرد
    private Long pDateFrom;
    private Long pDateTo;
    private Long pDateNow;
    private Long jYearFrom;
    private Long jMonthFrom;
    private Long jYearTo;
    private Long jMonthTo;
    private Long jYearNow;
    private Long jMonthNow;
    private List<String> pContractStatuses;
    private BigDecimal pMinContractPrice;
    private BigDecimal pMaxContractPrice;
    private BigDecimal pMinAllocatedBudget;
    private BigDecimal pMaxAllocatedBudget;
    private BigDecimal pMinCost;
    private BigDecimal pMaxCost;
    private BigDecimal pMinCpi;
    private BigDecimal pMaxCpi;
    private BigDecimal pMinSpi;
    private BigDecimal pMaxSpi;
    private BigDecimal pMinCv;
    private BigDecimal pMaxCv;
    private BigDecimal pMinSv;
    private BigDecimal pMaxSv;
    private BigDecimal pMinActualHours;
    private BigDecimal pMaxActualHours;
    private BigDecimal pMinEstimatedHours;
    private BigDecimal pMaxEstimatedHours;
}