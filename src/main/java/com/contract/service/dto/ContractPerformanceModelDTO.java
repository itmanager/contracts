package com.contract.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractPerformanceModelDTO {
    private Long id;
    private BigDecimal contractId;
    private String contractTitle;
    private Long totalBudget;
    private BigDecimal startDate;
    private BigDecimal endDate;
    private BigDecimal sumAllocatedBudget;
    private BigDecimal sumActualCost;
    private BigDecimal totalHoursWorked;
    private BigDecimal sumProgramProgress;
    private BigDecimal sumReportedProgress;
    private BigDecimal sumVerifiedProgress;
    private BigDecimal sumEarnedValue;
    private BigDecimal sumPresentValue;
    private BigDecimal cpi;
    private BigDecimal spi;
    private BigDecimal cv;
    private BigDecimal sv;
    private BigDecimal laborCost;
    private BigDecimal equipmentCost;
    private BigDecimal outsourcingCost;
    private BigDecimal overheadCost;
    private BigDecimal laborBudget;
    private BigDecimal equipmentBudget;
    private BigDecimal outsourcingBudget;
    private BigDecimal overheadBudget;
    private BigDecimal projectMonthCount;
    private BigDecimal elapsedMonthCount;
    private BigDecimal bac;
    private BigDecimal eac;
    private BigDecimal etc;
    private BigDecimal vac;
    private BigDecimal tcpi;

    private String annualPlanName;
    private String fiveYearPlanName;
    private String outSource;
    private String contractTypeTitle;
    private BigDecimal estimatedLaborHours;
    private String contractorName;
    private String employerName;
    private String supervisorName;
    private String userName;


    // متدهای کمکی برای تحلیل
    public String getPerformanceStatus() {
        if (cpi == null || spi == null) {
            return "نامشخص";
        }

        if (cpi.compareTo(BigDecimal.ONE) >= 0 && spi.compareTo(BigDecimal.ONE) >= 0) {
            return "مطلوب";
        } else if (cpi.compareTo(new BigDecimal("0.9")) >= 0 && spi.compareTo(new BigDecimal("0.9")) >= 0) {
            return "قابل قبول";
        } else {
            return "نیاز به توجه";
        }
    }

    public BigDecimal getBudgetUtilization() {
        if (totalBudget != null && totalBudget > 0 && sumActualCost != null) {
            return sumActualCost.divide(new BigDecimal(totalBudget), 4, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getProgressEfficiency() {
        if (sumProgramProgress != null &&
                sumProgramProgress.compareTo(BigDecimal.ZERO) > 0 &&
                sumVerifiedProgress != null) {
            return sumVerifiedProgress.divide(sumProgramProgress, 4, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}