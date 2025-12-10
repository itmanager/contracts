package com.contract.service;

import com.contract.domain.enumeration.monthlyWorkPerformance.*;
import com.contract.repository.WorkTimeEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkTimeAnalysisService {
    
    private final WorkTimeEntryRepository workTimeEntryRepository;
    
    // ۱. مجموع ساعات ماهانه برای هر پروژه
    public List<MonthlyProjectHoursDTO> getMonthlyHoursPerProject() {
        return workTimeEntryRepository.findMonthlyHoursPerProject();
    }
    
    // ۲. مجموع ساعات ماهانه برای هر کارمند
    public List<MonthlyEmployeeHoursDTO> getMonthlyHoursPerEmployee() {
        return workTimeEntryRepository.findMonthlyHoursPerEmployee();
    }
    
    // ۳. مجموع ساعات ماهانه برای هر کارمند در هر پروژه
    public List<MonthlyEmployeeProjectHoursDTO> getMonthlyHoursPerEmployeePerProject() {
        return workTimeEntryRepository.findMonthlyHoursPerEmployeePerProject();
    }
    
    // ۴. ساعات کار ماهانه یک کارمند خاص در یک پروژه خاص
    public List<Map<String, Object>> getMonthlyHoursForSpecificEmployeeAndProject(Long employeeId, Long contractId) {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursForSpecificEmployeeAndProject(employeeId, contractId);
        
        return results.stream()
            .map(row -> Map.of(
                "year", row[0],
                "month", row[1],
                "yearMonth", row[2],
                "totalHours", row[3],
                "avgMonthlyHours", row[4],
                "entryCount", row[5]
            ))
            .collect(Collectors.toList());
    }
    
    // ۵. ساعات کار ماهانه یک کارمند در تمام پروژه‌ها
    public List<Map<String, Object>> getMonthlyHoursForEmployeeAllProjects(Long employeeId) {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursForEmployeeAllProjects(employeeId);
        
        return results.stream()
            .map(row -> Map.of(
                "year", row[0],
                "month", row[1],
                "yearMonth", row[2],
                "totalHours", row[3],
                "projectCount", row[4],
                "contractIds", row[5],
                "entryCount", row[6]
            ))
            .collect(Collectors.toList());
    }
    
    // ۶. ساعات کار ماهانه یک پروژه با جزئیات کارمندان
    public List<Map<String, Object>> getMonthlyHoursForProjectWithEmployees(Long contractId) {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursForProjectWithEmployees(contractId);
        
        return results.stream()
            .map(row -> Map.of(
                "employeeId", row[0],
                "employeeName", row[1],
                "year", row[2],
                "month", row[3],
                "yearMonth", row[4],
                "totalHours", row[5],
                "entryCount", row[6]
            ))
            .collect(Collectors.toList());
    }
    
    // ۷. ساعت کار ماهانه همه افراد
    public List<Map<String, Object>> getMonthlyHoursAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursAllEmployees();
        
        return results.stream()
            .map(row -> Map.of(
                "year", row[0],
                "month", row[1],
                "yearMonth", row[2],
                "employeeCount", row[3],
                "totalHours", row[4],
                "avgHoursPerEmployee", row[5],
                "totalEntries", row[6]
            ))
            .collect(Collectors.toList());
    }
    
    // ۸. ساعت کار ماهانه همه پروژه‌ها
    public List<Map<String, Object>> getMonthlyHoursAllProjects() {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursAllProjects();
        
        return results.stream()
            .map(row -> Map.of(
                "year", row[0],
                "month", row[1],
                "yearMonth", row[2],
                "projectCount", row[3],
                "totalHours", row[4],
                "avgHoursPerProject", row[5],
                "totalEntries", row[6]
            ))
            .collect(Collectors.toList());
    }
    
    // ۹. محاسبه میانگین همه افراد ماهانه
    public List<Map<String, Object>> getAverageMonthlyHoursAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findAverageMonthlyHoursAllEmployees();
        
        return results.stream()
            .map(row -> Map.of(
                "year", row[0],
                "month", row[1],
                "yearMonth", row[2],
                "avgMonthlyHoursAllEmployees", row[3]
            ))
            .collect(Collectors.toList());
    }
    
    // ۱۰. محاسبه انحراف معیار هر فرد از میانگین خودش ماهانه
    public List<EmployeeDeviationDTO> getEmployeeDeviationFromOwnAverage() {
        return workTimeEntryRepository.findEmployeeDeviationFromOwnAverage();
    }
    
    // ۱۱. محاسبه انحراف معیار هر فرد از 160 ساعت ماهانه
    public List<Map<String, Object>> getEmployeeDeviationFrom160Hours() {
        List<Object[]> results = workTimeEntryRepository.findEmployeeDeviationFrom160Hours();
        
        return results.stream()
            .map(row -> Map.of(
                "employeeId", row[0],
                "employeeName", row[1],
                "year", row[2],
                "month", row[3],
                "yearMonth", row[4],
                "monthlyHours", row[5],
                "deviationFrom160", row[6],
                "absoluteDeviationFrom160", row[7]
            ))
            .collect(Collectors.toList());
    }
    
    // ۱۲. محاسبه انحراف معیار هر فرد از میانگین حضور همه افراد
    public List<Map<String, Object>> getEmployeeDeviationFromOverallAverage() {
        List<Object[]> results = workTimeEntryRepository.findEmployeeDeviationFromOverallAverage();
        
        return results.stream()
            .map(row -> Map.of(
                "employeeId", row[0],
                "employeeName", row[1],
                "year", row[2],
                "month", row[3],
                "yearMonth", row[4],
                "monthlyHours", row[5],
                "overallAverage", row[6],
                "overallStdDev", row[7],
                "deviationFromOverallAvg", row[8],
                "zScore", row[9]
            ))
            .collect(Collectors.toList());
    }
    
    // ۱۳. شناسایی افراد پرکار، کم کار و متوسط
    public List<EmployeeCategoryDTO> categorizeEmployeesByWorkHours() {
        return workTimeEntryRepository.categorizeEmployeesByWorkHours();
    }
    
    // ۱۴. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه پروژه‌ها
    public List<Map<String, Object>> getHoursForFarvardinOrdibeheshtAllProjects() {
        List<Object[]> results = workTimeEntryRepository.findHoursForFarvardinOrdibeheshtAllProjects();
        
        return results.stream()
            .map(row -> Map.of(
                "contractId", row[0],
                "year", row[1],
                "month", row[2],
                "monthName", row[3],
                "totalHours", row[4],
                "employeeCount", row[5],
                "entryCount", row[6]
            ))
            .collect(Collectors.toList());
    }
    
    // ۱۵. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه افراد
    public List<Map<String, Object>> getHoursForFarvardinOrdibeheshtAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findHoursForFarvardinOrdibeheshtAllEmployees();
        
        return results.stream()
            .map(row -> Map.of(
                "employeeId", row[0],
                "employeeName", row[1],
                "year", row[2],
                "month", row[3],
                "monthName", row[4],
                "totalHours", row[5],
                "projectCount", row[6],
                "entryCount", row[7]
            ))
            .collect(Collectors.toList());
    }
    
    // ۱۶. آمار کلی ماهانه برای همه ماه‌ها
    public List<MonthComparisonDTO> getMonthlyComparisonAllMonths() {
        return workTimeEntryRepository.findMonthlyComparisonAllMonths();
    }
    
    // تحلیل پیشرفته
    public Map<String, Object> getAdvancedAnalysis(Integer year, Integer month) {
        List<Object[]> monthlyStats = workTimeEntryRepository.findMonthlyHoursAllEmployees();
        List<EmployeeCategoryDTO> categories = workTimeEntryRepository.categorizeEmployeesByWorkHours();
        List<EmployeeDeviationDTO> deviations = workTimeEntryRepository.findEmployeeDeviationFromOwnAverage();
        
        // فیلتر بر اساس سال و ماه اگر مشخص شده باشد
        List<EmployeeCategoryDTO> filteredCategories = categories.stream()
            .filter(c -> (year == null || c.getYear().equals(year)) && 
                        (month == null || c.getMonth().equals(month)))
            .collect(Collectors.toList());
        
        long highPerformers = filteredCategories.stream()
            .filter(c -> c.getCategory().contains("پرکار"))
            .count();
        
        long lowPerformers = filteredCategories.stream()
            .filter(c -> c.getCategory().contains("کم کار"))
            .count();
        
        long averagePerformers = filteredCategories.stream()
            .filter(c -> c.getCategory().contains("متوسط"))
            .count();

        return Map.of(
            "totalEmployees", filteredCategories.stream().map(EmployeeCategoryDTO::getEmployeeId).distinct().count(),
            "highPerformers", highPerformers,
            "lowPerformers", lowPerformers,
            "averagePerformers", averagePerformers,
            "performanceDistribution", Map.of(
                "high", (double) highPerformers / filteredCategories.size() * 100,
                "low", (double) lowPerformers / filteredCategories.size() * 100,
                "average", (double) averagePerformers / filteredCategories.size() * 100
            )
        );
    }
}