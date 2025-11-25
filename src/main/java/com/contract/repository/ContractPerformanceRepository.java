package com.contract.repository;

import com.contract.service.dto.ContractPerformanceModelDTO;
import com.contract.service.dto.QueryContractModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContractPerformanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ContractPerformanceModelDTO> getContractPerformanceReportCombined(
            QueryContractModel criteria) {

        String sql = "SELECT * FROM get_contract_performance_report_combined(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.query(sql, new Object[]{
                criteria.getPContractIds(),
                criteria.getPEmployerIds(),
                criteria.getPContractorIds(),
                criteria.getPUserIds(),
                criteria.getPSupervisorIds(),
                criteria.getPFromDate(),
                criteria.getPToDate(),
                criteria.getPFiveYearPlanIds(),
                criteria.getPAnnualPlanIds(),
                criteria.getPContractTypeIds(),
                criteria.getPOutSourceTypes(),
                criteria.getPDateFrom(),
                criteria.getPDateTo(),
                criteria.getPDateNow(),
                criteria.getJYearFrom(),
                criteria.getJMonthFrom(),
                criteria.getJYearTo(),
                criteria.getJMonthTo(),
                criteria.getJYearNow(),
                criteria.getJMonthNow(),
                criteria.getPContractStatuses(),
                criteria.getPMinContractPrice(),
                criteria.getPMaxContractPrice(),
                criteria.getPMinAllocatedBudget(),
                criteria.getPMaxAllocatedBudget(),
                criteria.getPMinCost(),
                criteria.getPMaxCost(),
                criteria.getPMinCpi(),
                criteria.getPMaxCpi(),
                criteria.getPMinSpi(),
                criteria.getPMaxSpi(),
                criteria.getPMinCv(),
                criteria.getPMaxCv(),
                criteria.getPMinSv(),
                criteria.getPMaxSv(),
                criteria.getPMinActualHours(),
                criteria.getPMaxActualHours(),
                criteria.getPMinEstimatedHours(),
                criteria.getPMaxEstimatedHours()
        }, new ContractPerformanceRowMapper());
    }

    private static class ContractPerformanceRowMapper implements RowMapper<ContractPerformanceModelDTO> {
        @Override
        public ContractPerformanceModelDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContractPerformanceModelDTO dto = new ContractPerformanceModelDTO();
            // Basic contract information
            dto.setContractId(rs.getBigDecimal("contract_id"));
            dto.setContractTitle(rs.getString("contract_title"));
            dto.setTotalBudget(rs.getLong("total_budget"));
            dto.setStartDate(rs.getBigDecimal("start_date"));
            dto.setEndDate(rs.getBigDecimal("end_date"));

            // Budget and cost information
            dto.setSumAllocatedBudget(rs.getBigDecimal("sum_allocated_budget"));
            dto.setSumActualCost(rs.getBigDecimal("sum_actual_cost"));
            dto.setTotalHoursWorked(rs.getBigDecimal("total_hours_worked"));

            // Progress information
            dto.setSumProgramProgress(rs.getBigDecimal("sum_program_progress"));
            dto.setSumReportedProgress(rs.getBigDecimal("sum_reported_progress"));
            dto.setSumVerifiedProgress(rs.getBigDecimal("sum_verified_progress"));

            // Earned value management
            dto.setSumEarnedValue(rs.getBigDecimal("sum_earned_value"));
            dto.setSumPresentValue(rs.getBigDecimal("sum_present_value"));

            // Performance indices
            dto.setCpi(rs.getBigDecimal("cpi"));
            dto.setSpi(rs.getBigDecimal("spi"));
            dto.setCv(rs.getBigDecimal("cv"));
            dto.setSv(rs.getBigDecimal("sv"));

            // Detailed cost breakdown
            dto.setLaborCost(rs.getBigDecimal("labor_cost"));
            dto.setEquipmentCost(rs.getBigDecimal("equipment_cost"));
            dto.setOutsourcingCost(rs.getBigDecimal("outsourcing_cost"));
            dto.setOverheadCost(rs.getBigDecimal("overhead_cost"));

            // Detailed budget breakdown
            dto.setLaborBudget(rs.getBigDecimal("labor_budget"));
            dto.setEquipmentBudget(rs.getBigDecimal("equipment_budget"));
            dto.setOutsourcingBudget(rs.getBigDecimal("outsourcing_budget"));
            dto.setOverheadBudget(rs.getBigDecimal("overhead_budget"));

            // Time calculations
            dto.setProjectMonthCount(rs.getBigDecimal("project_month_count"));
            dto.setElapsedMonthCount(rs.getBigDecimal("elapsed_month_count"));

            // Advanced project management calculations
            dto.setBac(rs.getBigDecimal("bac"));
            dto.setEac(rs.getBigDecimal("eac"));
            dto.setEtc(rs.getBigDecimal("etc"));
            dto.setVac(rs.getBigDecimal("vac"));
            dto.setTcpi(rs.getBigDecimal("tcpi"));

            return dto;
        }
    }
}