package com.contract.service;

import com.contract.domain.enumeration.monthlyWorkPerformance.*;
import com.contract.repository.WorkTimeEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("year", objectsResult[0]);
            map.put("month", objectsResult[1]);
            map.put("yearMonth", objectsResult[2]);
            map.put("totalHours", objectsResult[3]);
            map.put("avgMonthlyHours", objectsResult[4]);
            map.put("entryCount", objectsResult[5]);
            mapList.add(map);
        }
        return mapList;
    }

    // ۵. ساعات کار ماهانه یک کارمند در تمام پروژه‌ها
    public List<Map<String, Object>> getMonthlyHoursForEmployeeAllProjects(Long employeeId) {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursForEmployeeAllProjects(employeeId);

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("year", objectsResult[0]);
            map.put("month", objectsResult[1]);
            map.put("totalHours", objectsResult[2]);
            map.put("projectCount", objectsResult[3]);
            map.put("entryCount", objectsResult[4]);
            mapList.add(map);
        }
        return mapList;
    }

    // ۶. ساعات کار ماهانه یک پروژه با جزئیات کارمندان
    public List<Map<String, Object>> getMonthlyHoursForProjectWithEmployees(Long contractId) {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursForProjectWithEmployees(contractId);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", objectsResult[0]);
            map.put("employeeName", objectsResult[1]);
            map.put("year", objectsResult[2]);
            map.put("month", objectsResult[3]);
            map.put("yearMonth", objectsResult[4]);
            map.put("totalHours", objectsResult[5]);
            map.put("entryCount", objectsResult[6]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۷. ساعت کار ماهانه همه افراد
    public List<Map<String, Object>> getMonthlyHoursAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursAllEmployees();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("year", objectsResult[0]);
            map.put("month", objectsResult[1]);
            map.put("yearMonth", objectsResult[2]);
            map.put("employeeCount", objectsResult[3]);
            map.put("totalHours", objectsResult[4]);
            map.put("avgHoursPerEmployee", objectsResult[5]);
            map.put("totalEntries", objectsResult[6]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۸. ساعت کار ماهانه همه پروژه‌ها
    public List<Map<String, Object>> getMonthlyHoursAllProjects() {
        List<Object[]> results = workTimeEntryRepository.findMonthlyHoursAllProjects();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("year", objectsResult[0]);
            map.put("month", objectsResult[1]);
            map.put("yearMonth", objectsResult[2]);
            map.put("projectCount", objectsResult[3]);
            map.put("totalHours", objectsResult[4]);
            map.put("avgHoursPerProject", objectsResult[5]);
            map.put("totalEntries", objectsResult[6]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۹. محاسبه میانگین همه افراد ماهانه
    public List<Map<String, Object>> getAverageMonthlyHoursAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findAverageMonthlyHoursAllEmployees();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("year", objectsResult[0]);
            map.put("month", objectsResult[1]);
            map.put("yearMonth", objectsResult[2]);
            map.put("avgMonthlyHoursAllEmployees", objectsResult[3]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۱۰. محاسبه انحراف معیار هر فرد از میانگین خودش ماهانه
    public List<EmployeeDeviationDTO> getEmployeeDeviationFromOwnAverage() {
        return workTimeEntryRepository.findEmployeeDeviationFromOwnAverage();
    }

    // ۱۱. محاسبه انحراف معیار هر فرد از 160 ساعت ماهانه
    public List<Map<String, Object>> getEmployeeDeviationFrom160Hours() {
        List<Object[]> results = workTimeEntryRepository.findEmployeeDeviationFrom160Hours();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", objectsResult[0]);
            map.put("employeeName", objectsResult[1]);
            map.put("year", objectsResult[2]);
            map.put("month", objectsResult[3]);
            map.put("yearMonth", objectsResult[4]);
            map.put("monthlyHours", objectsResult[5]);
            map.put("deviationFrom160", objectsResult[6]);
            map.put("absoluteDeviationFrom160", objectsResult[7]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۱۲. محاسبه انحراف معیار هر فرد از میانگین حضور همه افراد
    public List<Map<String, Object>> getEmployeeDeviationFromOverallAverage() {
        List<Object[]> results = workTimeEntryRepository.findEmployeeDeviationFromOverallAverage();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", objectsResult[0]);
            map.put("employeeName", objectsResult[1]);
            map.put("year", objectsResult[2]);
            map.put("month", objectsResult[3]);
            map.put("yearMonth", objectsResult[4]);
            map.put("monthlyHours", objectsResult[5]);
            map.put("overallAverage", objectsResult[6]);
            map.put("overallStdDev", objectsResult[7]);
            map.put("deviationFromOverallAvg", objectsResult[8]);
            map.put("zScore", objectsResult[9]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۱۳. شناسایی افراد پرکار، کم کار و متوسط
    public List<EmployeeCategoryDTO> categorizeEmployeesByWorkHours() {
        return workTimeEntryRepository.categorizeEmployeesByWorkHours();
    }

    // ۱۴. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه پروژه‌ها
    public List<Map<String, Object>> getHoursForFarvardinOrdibeheshtAllProjects() {
        List<Object[]> results = workTimeEntryRepository.findHoursForFarvardinOrdibeheshtAllProjects();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("contractId", objectsResult[0]);
            map.put("year", objectsResult[1]);
            map.put("month", objectsResult[2]);
            map.put("monthName", objectsResult[3]);
            map.put("totalHours", objectsResult[4]);
            map.put("employeeCount", objectsResult[5]);
            map.put("entryCount", objectsResult[6]);
            mapList.add(map);
        }

        return mapList;
    }

    // ۱۵. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه افراد
    public List<Map<String, Object>> getHoursForFarvardinOrdibeheshtAllEmployees() {
        List<Object[]> results = workTimeEntryRepository.findHoursForFarvardinOrdibeheshtAllEmployees();

        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Object[] objectsResult = results.get(i);
            Map<String, Object> map = new HashMap<>();
            map.put("employeeId", objectsResult[0]);
            map.put("employeeName", objectsResult[1]);
            map.put("year", objectsResult[2]);
            map.put("month", objectsResult[3]);
            map.put("monthName", objectsResult[4]);
            map.put("totalHours", objectsResult[5]);
            map.put("projectCount", objectsResult[6]);
            map.put("entryCount", objectsResult[7]);
            mapList.add(map);
        }

        return mapList;
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
        List<EmployeeCategoryDTO> filteredCategories = new ArrayList<>();
        for (EmployeeCategoryDTO category : categories) {
            if ((year == null || category.getYear().equals(year)) &&
                    (month == null || category.getMonth().equals(month))) {
                filteredCategories.add(category);
            }
        }

        long highPerformers = 0;
        long lowPerformers = 0;
        long averagePerformers = 0;

        for (EmployeeCategoryDTO category : filteredCategories) {
            if (category.getCategory().contains("پرکار")) {
                highPerformers++;
            } else if (category.getCategory().contains("کم کار")) {
                lowPerformers++;
            } else if (category.getCategory().contains("متوسط")) {
                averagePerformers++;
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalEmployees", filteredCategories.stream().map(EmployeeCategoryDTO::getEmployeeId).distinct().count());
        result.put("highPerformers", highPerformers);
        result.put("lowPerformers", lowPerformers);
        result.put("averagePerformers", averagePerformers);

        Map<String, Double> performanceDistribution = new HashMap<>();
        if (!filteredCategories.isEmpty()) {
            performanceDistribution.put("high", (double) highPerformers / filteredCategories.size() * 100);
            performanceDistribution.put("low", (double) lowPerformers / filteredCategories.size() * 100);
            performanceDistribution.put("average", (double) averagePerformers / filteredCategories.size() * 100);
        } else {
            performanceDistribution.put("high", 0.0);
            performanceDistribution.put("low", 0.0);
            performanceDistribution.put("average", 0.0);
        }

        result.put("performanceDistribution", performanceDistribution);

        return result;
    }
}