package com.contract.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contract_performance_report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractPerformanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contract_id", precision = 20, scale = 2)
    private BigDecimal contractId;

    @Column(name = "contract_title", length = 500)
    private String contractTitle;

    @Column(name = "total_budget")
    private Long totalBudget;

    @Column(name = "start_date", precision = 20, scale = 2)
    private BigDecimal startDate;

    @Column(name = "end_date", precision = 20, scale = 2)
    private BigDecimal endDate;

    @Column(name = "sum_allocated_budget", precision = 20, scale = 2)
    private BigDecimal sumAllocatedBudget;

    @Column(name = "sum_actual_cost", precision = 20, scale = 2)
    private BigDecimal sumActualCost;

    @Column(name = "total_hours_worked", precision = 10, scale = 2)
    private BigDecimal totalHoursWorked;

    @Column(name = "sum_program_progress", precision = 10, scale = 2)
    private BigDecimal sumProgramProgress;

    @Column(name = "sum_reported_progress", precision = 10, scale = 2)
    private BigDecimal sumReportedProgress;

    @Column(name = "sum_verified_progress", precision = 10, scale = 2)
    private BigDecimal sumVerifiedProgress;

    @Column(name = "sum_earned_value", precision = 20, scale = 2)
    private BigDecimal sumEarnedValue;

    @Column(name = "sum_present_value", precision = 20, scale = 2)
    private BigDecimal sumPresentValue;

    @Column(name = "cpi", precision = 10, scale = 4)
    private BigDecimal cpi;

    @Column(name = "spi", precision = 10, scale = 4)
    private BigDecimal spi;

    @Column(name = "cv", precision = 20, scale = 2)
    private BigDecimal cv;

    @Column(name = "sv", precision = 20, scale = 2)
    private BigDecimal sv;

    // هزینه‌های تفکیکی
    @Column(name = "labor_cost", precision = 20, scale = 2)
    private BigDecimal laborCost;

    @Column(name = "equipment_cost", precision = 20, scale = 2)
    private BigDecimal equipmentCost;

    @Column(name = "outsourcing_cost", precision = 20, scale = 2)
    private BigDecimal outsourcingCost;

    @Column(name = "overhead_cost", precision = 20, scale = 2)
    private BigDecimal overheadCost;

    // بودجه‌های تفکیکی
    @Column(name = "labor_budget", precision = 20, scale = 2)
    private BigDecimal laborBudget;

    @Column(name = "equipment_budget", precision = 20, scale = 2)
    private BigDecimal equipmentBudget;

    @Column(name = "outsourcing_budget", precision = 20, scale = 2)
    private BigDecimal outsourcingBudget;

    @Column(name = "overhead_budget", precision = 20, scale = 2)
    private BigDecimal overheadBudget;

    // محاسبات زمانی
    @Column(name = "project_month_count", precision = 10, scale = 2)
    private BigDecimal projectMonthCount;

    @Column(name = "elapsed_month_count", precision = 10, scale = 2)
    private BigDecimal elapsedMonthCount;

    // محاسبات پیشرفته
    @Column(name = "bac", precision = 20, scale = 2)
    private BigDecimal bac;

    @Column(name = "eac", precision = 20, scale = 2)
    private BigDecimal eac;

    @Column(name = "etc", precision = 20, scale = 2)
    private BigDecimal etc;

    @Column(name = "vac", precision = 20, scale = 2)
    private BigDecimal vac;

    @Column(name = "tcpi", precision = 10, scale = 4)
    private BigDecimal tcpi;

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
        if (totalBudget != null && totalBudget > 0) {
            return sumActualCost.divide(new BigDecimal(totalBudget), 4, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getProgressEfficiency() {
        if (sumProgramProgress != null && sumProgramProgress.compareTo(BigDecimal.ZERO) > 0) {
            return sumVerifiedProgress.divide(sumProgramProgress, 4, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}