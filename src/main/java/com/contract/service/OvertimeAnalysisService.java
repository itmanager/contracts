package com.contract.service;

import com.contract.domain.enumeration.overTime.*;
import com.contract.repository.WorkOverTimeReppository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OvertimeAnalysisService {
    
    private final WorkOverTimeReppository workTimeEntryRepository;
    
    /**
     * محاسبه اضافه کار روزانه همه افراد
     */
    public List<EmployeeOvertimeDailyDTO> getDailyOvertime() {
        return workTimeEntryRepository.findDailyOvertime();
    }
    
    /**
     * محاسبه اضافه کار ماهانه همه افراد
     */
    public List<EmployeeOvertimeMonthlyDTO> getMonthlyOvertimePerEmployee() {
        return workTimeEntryRepository.findMonthlyOvertimePerEmployee();
    }
    
    /**
     * محاسبه اضافه کار افراد روی پروژه‌ها
     */
    public List<EmployeeProjectOvertimeDTO> getProjectOvertimePerEmployee() {
        return workTimeEntryRepository.findProjectOvertimePerEmployee();
    }
    
    /**
     * اضافه کار در فروردین
     */
    public List<MonthlyOvertimeSummaryDTO> getFarvardinOvertimeSummary() {
        return workTimeEntryRepository.findFarvardinOvertimeSummary();
    }
    
    /**
     * اضافه کار در اردیبهشت
     */
    public List<MonthlyOvertimeSummaryDTO> getOrdibeheshtOvertimeSummary() {
        return workTimeEntryRepository.findOrdibeheshtOvertimeSummary();
    }
    
    /**
     * اضافه کار همه ماه‌های سال
     */
    public List<MonthlyOvertimeSummaryDTO> getMonthlyOvertimeSummaryAllMonths() {
        return workTimeEntryRepository.findMonthlyOvertimeSummaryAllMonths();
    }
    
    /**
     * اضافه کار پروژه‌ها به تفکیک ماه
     */
    public List<ProjectOvertimeMonthlyDTO> getMonthlyOvertimePerProject() {
        return workTimeEntryRepository.findMonthlyOvertimePerProject();
    }
    
    /**
     * اضافه کار یک فرد خاص
     */
    public List<EmployeeOvertimeMonthlyDTO> getMonthlyOvertimeByEmployeeId(Long employeeId) {
        return workTimeEntryRepository.findMonthlyOvertimeByEmployeeId(employeeId);
    }
    
    /**
     * اضافه کار یک پروژه خاص
     */
    public List<ProjectOvertimeMonthlyDTO> getMonthlyOvertimeByContractId(Long contractId) {
        return workTimeEntryRepository.findMonthlyOvertimeByContractId(contractId);
    }
    
    /**
     * ۱۰ فرد با بیشترین اضافه کار در یک ماه
     */
    public List<EmployeeOvertimeMonthlyDTO> getTop10OvertimeEmployeesByMonth(Integer year, Integer month) {
        return workTimeEntryRepository.findTop10OvertimeEmployeesByMonth(year, month);
    }
    
    /**
     * گزارش کامل اضافه کار
     */
    public Map<String, Object> getCompleteOvertimeReport(Integer year, Integer month) {
        List<MonthlyOvertimeSummaryDTO> monthlySummary = workTimeEntryRepository.findMonthlyOvertimeSummaryAllMonths();
        List<EmployeeOvertimeMonthlyDTO> topEmployees = workTimeEntryRepository.findTop10OvertimeEmployeesByMonth(year, month);
        List<ProjectOvertimeMonthlyDTO> projectOvertime = workTimeEntryRepository.findMonthlyOvertimePerProject();
        
        // فیلتر بر اساس سال و ماه اگر مشخص شده باشد
        if (year != null && month != null) {
            monthlySummary = monthlySummary.stream()
                .filter(m -> m.getYear().equals(year) && m.getMonth().equals(month))
                .collect(Collectors.toList());
            
            projectOvertime = projectOvertime.stream()
                .filter(p -> p.getYear().equals(year) && p.getMonth().equals(month))
                .collect(Collectors.toList());
        } else if (year != null) {
            monthlySummary = monthlySummary.stream()
                .filter(m -> m.getYear().equals(year))
                .collect(Collectors.toList());
            
            projectOvertime = projectOvertime.stream()
                .filter(p -> p.getYear().equals(year))
                .collect(Collectors.toList());
        }
        
        // محاسبه آمار کلی
        double totalOvertime = monthlySummary.stream()
            .mapToDouble(MonthlyOvertimeSummaryDTO::getTotalOvertime)
            .sum();
        
        long totalEmployees = monthlySummary.stream()
            .mapToLong(MonthlyOvertimeSummaryDTO::getEmployeeCount)
            .sum();
        
        long totalOvertimeDays = monthlySummary.stream()
            .mapToLong(MonthlyOvertimeSummaryDTO::getOvertimeDaysCount)
            .sum();
        
        return Map.of(
            "totalOvertimeHours", totalOvertime,
            "totalEmployeesWithOvertime", totalEmployees,
            "totalOvertimeDays", totalOvertimeDays,
            "averageOvertimePerEmployee", totalEmployees > 0 ? totalOvertime / totalEmployees : 0,
            "monthlySummary", monthlySummary,
            "topEmployees", topEmployees,
            "projectOvertime", projectOvertime
        );
    }
    
    /**
     * تشخیص روزهای اضافه کاری یک فرد
     * @return
     */
    public List<Map<String, Object>> getEmployeeOvertimeDays(Long employeeId) {
        return workTimeEntryRepository.findDailyOvertime()
                .stream()
                .filter(d -> employeeId.equals(d.getEmployeeId()))
                .map(this::convertToOvertimeMap)
                .collect(Collectors.toList());
    }

    private Map<String, Object> convertToOvertimeMap(EmployeeOvertimeDailyDTO dto) {
        Map<String, Object> overtimeMap = new LinkedHashMap<>();

        overtimeMap.put("date", formatDate(dto.getYear(), dto.getMonth(), dto.getDay()));
        overtimeMap.put("year", dto.getYear());
        overtimeMap.put("month", dto.getMonth());
        overtimeMap.put("day", dto.getDay());
        overtimeMap.put("totalHours", dto.getTotalDailyHours());
        overtimeMap.put("overtimeHours", dto.getOvertimeHours());
        overtimeMap.put("normalHours", calculateNormalHours(dto));

        return overtimeMap;
    }

    private String formatDate(int year, int month, int day) {
        return String.format("%d-%02d-%02d", year, month, day);
    }

    private double calculateNormalHours(EmployeeOvertimeDailyDTO dto) {
        return dto.getTotalDailyHours() - dto.getOvertimeHours();
    }
}