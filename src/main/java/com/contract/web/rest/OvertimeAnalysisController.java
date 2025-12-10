package com.contract.web.rest;

import com.contract.domain.enumeration.overTime.*;
import com.contract.service.OvertimeAnalysisService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/overtime-analysis")
@RequiredArgsConstructor
public class OvertimeAnalysisController {
    
    private final OvertimeAnalysisService overtimeAnalysisService;
    
    @GetMapping("/daily")
    public List<EmployeeOvertimeDailyDTO> getDailyOvertime() {
        return overtimeAnalysisService.getDailyOvertime();
    }
    
    @GetMapping("/monthly/employees")
    public List<EmployeeOvertimeMonthlyDTO> getMonthlyOvertimePerEmployee() {
        return overtimeAnalysisService.getMonthlyOvertimePerEmployee();
    }
    
    @GetMapping("/monthly/projects")
    public List<EmployeeProjectOvertimeDTO> getProjectOvertimePerEmployee() {
        return overtimeAnalysisService.getProjectOvertimePerEmployee();
    }
    
    @GetMapping("/farvardin/summary")
    public List<MonthlyOvertimeSummaryDTO> getFarvardinOvertimeSummary() {
        return overtimeAnalysisService.getFarvardinOvertimeSummary();
    }
    
    @GetMapping("/ordibehesht/summary")
    public List<MonthlyOvertimeSummaryDTO> getOrdibeheshtOvertimeSummary() {
        return overtimeAnalysisService.getOrdibeheshtOvertimeSummary();
    }
    
    @GetMapping("/all-months/summary")
    public List<MonthlyOvertimeSummaryDTO> getMonthlyOvertimeSummaryAllMonths() {
        return overtimeAnalysisService.getMonthlyOvertimeSummaryAllMonths();
    }
    
    @GetMapping("/monthly/project-summary")
    public List<ProjectOvertimeMonthlyDTO> getMonthlyOvertimePerProject() {
        return overtimeAnalysisService.getMonthlyOvertimePerProject();
    }
    
    @GetMapping("/employee/{employeeId}/monthly")
    public List<EmployeeOvertimeMonthlyDTO> getEmployeeMonthlyOvertime(@PathVariable Long employeeId) {
        return overtimeAnalysisService.getMonthlyOvertimeByEmployeeId(employeeId);
    }
    
    @GetMapping("/project/{contractId}/monthly")
    public List<ProjectOvertimeMonthlyDTO> getProjectMonthlyOvertime(@PathVariable Long contractId) {
        return overtimeAnalysisService.getMonthlyOvertimeByContractId(contractId);
    }
    
    @GetMapping("/top10/{year}/{month}")
    public List<EmployeeOvertimeMonthlyDTO> getTop10OvertimeEmployees(
            @PathVariable Integer year,
            @PathVariable Integer month) {
        return overtimeAnalysisService.getTop10OvertimeEmployeesByMonth(year, month);
    }
    
    @GetMapping("/complete-report")
    public Map<String, Object> getCompleteOvertimeReport(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return overtimeAnalysisService.getCompleteOvertimeReport(year, month);
    }
    
    @GetMapping("/employee/{employeeId}/overtime-days")
    public List<Map<String, Object>> getEmployeeOvertimeDays(@PathVariable Long employeeId) {
        return overtimeAnalysisService.getEmployeeOvertimeDays(employeeId);
    }

}