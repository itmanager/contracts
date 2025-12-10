package com.contract.web.rest;

import com.contract.domain.enumeration.monthlyWorkPerformance.*;
import com.contract.service.WorkTimeAnalysisService;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/work-time-analysis")
@RequiredArgsConstructor
public class WorkTimeAnalysisController {
    
    private  final WorkTimeAnalysisService workTimeAnalysisService;

    @GetMapping("/monthly/project")
    public List<MonthlyProjectHoursDTO> getMonthlyHoursPerProject() {
        return workTimeAnalysisService.getMonthlyHoursPerProject();
    }
    
    @GetMapping("/monthly/employee")
    public List<MonthlyEmployeeHoursDTO> getMonthlyHoursPerEmployee() {
        return workTimeAnalysisService.getMonthlyHoursPerEmployee();
    }
    
    @GetMapping("/monthly/employee-project")
    public List<MonthlyEmployeeProjectHoursDTO> getMonthlyHoursPerEmployeePerProject() {
        return workTimeAnalysisService.getMonthlyHoursPerEmployeePerProject();
    }
    
    @GetMapping("/employee/{employeeId}/project/{contractId}/monthly")
    public List<Map<String, Object>> getEmployeeProjectMonthlyHours(
            @PathVariable Long employeeId,
            @PathVariable Long contractId) {
        return workTimeAnalysisService.getMonthlyHoursForSpecificEmployeeAndProject(employeeId, contractId);
    }
    
    @GetMapping("/employee/{employeeId}/monthly")
    public List<Map<String, Object>> getEmployeeMonthlyHours(@PathVariable Long employeeId) {
        return workTimeAnalysisService.getMonthlyHoursForEmployeeAllProjects(employeeId);
    }
    
    @GetMapping("/project/{contractId}/monthly/employees")
    public List<Map<String, Object>> getProjectMonthlyHoursWithEmployees(@PathVariable Long contractId) {
        return workTimeAnalysisService.getMonthlyHoursForProjectWithEmployees(contractId);
    }
    
    @GetMapping("/monthly/all-employees")
    public List<Map<String, Object>> getAllEmployeesMonthlyHours() {
        return workTimeAnalysisService.getMonthlyHoursAllEmployees();
    }
    
    @GetMapping("/monthly/all-projects")
    public List<Map<String, Object>> getAllProjectsMonthlyHours() {
        return workTimeAnalysisService.getMonthlyHoursAllProjects();
    }
    
    @GetMapping("/monthly/average-all-employees")
    public List<Map<String, Object>> getAverageMonthlyHoursAllEmployees() {
        return workTimeAnalysisService.getAverageMonthlyHoursAllEmployees();
    }
    
    @GetMapping("/deviation/own-average")
    public List<EmployeeDeviationDTO> getEmployeeDeviationFromOwnAverage() {
        return workTimeAnalysisService.getEmployeeDeviationFromOwnAverage();
    }
    
    @GetMapping("/deviation/from-160")
    public List<Map<String, Object>> getEmployeeDeviationFrom160Hours() {
        return workTimeAnalysisService.getEmployeeDeviationFrom160Hours();
    }
    
    @GetMapping("/deviation/overall-average")
    public List<Map<String, Object>> getEmployeeDeviationFromOverallAverage() {
        return workTimeAnalysisService.getEmployeeDeviationFromOverallAverage();
    }
    
    @GetMapping("/categorize/employees")
    public List<EmployeeCategoryDTO> categorizeEmployeesByWorkHours() {
        return workTimeAnalysisService.categorizeEmployeesByWorkHours();
    }
    
    @GetMapping("/months/farvardin-ordibehesht/projects")
    public List<Map<String, Object>> getFarvardinOrdibeheshtHoursForProjects() {
        return workTimeAnalysisService.getHoursForFarvardinOrdibeheshtAllProjects();
    }
    
    @GetMapping("/months/farvardin-ordibehesht/employees")
    public List<Map<String, Object>> getFarvardinOrdibeheshtHoursForEmployees() {
        return workTimeAnalysisService.getHoursForFarvardinOrdibeheshtAllEmployees();
    }
    
    @GetMapping("/monthly/comparison")
    public List<MonthComparisonDTO> getMonthlyComparison() {
        return workTimeAnalysisService.getMonthlyComparisonAllMonths();
    }
    
    @GetMapping("/advanced-analysis")
    public Map<String, Object> getAdvancedAnalysis(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month) {
        return workTimeAnalysisService.getAdvancedAnalysis(year, month);
    }
}