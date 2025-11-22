package com.contract.repository;

import com.contract.domain.ContractPerformanceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ContractPerformanceRepository extends JpaRepository<ContractPerformanceModel, Long> {

    @Query(value = "SELECT * FROM get_contract_performance_report_combined(" +
            ":p_contract_ids, :p_employer_ids, :p_contractor_ids, :p_supervisor_ids, " +
            ":p_from_date, :p_to_date, :p_five_year_plan_ids, :p_annual_plan_ids, " +
            ":p_contract_type_ids, :p_out_source_types, " +
            ":p_date_from, :p_date_to, :p_date_now, " +
            ":j_year_from, :j_month_from, :j_year_to, :j_month_to, " +
            ":j_year_now, :j_month_now, " +
            ":p_contract_statuses, :p_min_contract_price, :p_max_contract_price, " +
            ":p_min_allocated_budget, :p_max_allocated_budget, " +
            ":p_min_cost, :p_max_cost, :p_min_cpi, :p_max_cpi, " +
            ":p_min_spi, :p_max_spi, :p_min_cv, :p_max_cv, " +
            ":p_min_sv, :p_max_sv, :p_min_actual_hours, :p_max_actual_hours, " +
            ":p_min_estimated_hours, :p_max_estimated_hours)",
            nativeQuery = true)
    List<ContractPerformanceModel> getContractPerformanceReport(
            @Param("p_contract_ids") List<Long> pContractIds,
            @Param("p_employer_ids") List<Long> pEmployerIds,
            @Param("p_contractor_ids") List<Long> pContractorIds,
            @Param("p_supervisor_ids") List<Long> pSupervisorIds,
            @Param("p_from_date") Long pFromDate,
            @Param("p_to_date") Long pToDate,
            @Param("p_five_year_plan_ids") List<Long> pFiveYearPlanIds,
            @Param("p_annual_plan_ids") List<Long> pAnnualPlanIds,
            @Param("p_contract_type_ids") List<Long> pContractTypeIds,
            @Param("p_out_source_types") List<String> pOutSourceTypes,
            @Param("p_date_from") Long pDateFrom,
            @Param("p_date_to") Long pDateTo,
            @Param("p_date_now") Long pDateNow,
            @Param("j_year_from") Long jYearFrom,
            @Param("j_month_from") Long jMonthFrom,
            @Param("j_year_to") Long jYearTo,
            @Param("j_month_to") Long jMonthTo,
            @Param("j_year_now") Long jYearNow,
            @Param("j_month_now") Long jMonthNow,
            @Param("p_contract_statuses") List<String> pContractStatuses,
            @Param("p_min_contract_price") BigDecimal pMinContractPrice,
            @Param("p_max_contract_price") BigDecimal pMaxContractPrice,
            @Param("p_min_allocated_budget") BigDecimal pMinAllocatedBudget,
            @Param("p_max_allocated_budget") BigDecimal pMaxAllocatedBudget,
            @Param("p_min_cost") BigDecimal pMinCost,
            @Param("p_max_cost") BigDecimal pMaxCost,
            @Param("p_min_cpi") BigDecimal pMinCpi,
            @Param("p_max_cpi") BigDecimal pMaxCpi,
            @Param("p_min_spi") BigDecimal pMinSpi,
            @Param("p_max_spi") BigDecimal pMaxSpi,
            @Param("p_min_cv") BigDecimal pMinCv,
            @Param("p_max_cv") BigDecimal pMaxCv,
            @Param("p_min_sv") BigDecimal pMinSv,
            @Param("p_max_sv") BigDecimal pMaxSv,
            @Param("p_min_actual_hours") BigDecimal pMinActualHours,
            @Param("p_max_actual_hours") BigDecimal pMaxActualHours,
            @Param("p_min_estimated_hours") BigDecimal pMinEstimatedHours,
            @Param("p_max_estimated_hours") BigDecimal pMaxEstimatedHours
    );
}