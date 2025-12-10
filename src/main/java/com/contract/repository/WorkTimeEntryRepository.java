package com.contract.repository;

import com.contract.domain.WorkTimeEntry;
import com.contract.domain.enumeration.monthlyWorkPerformance.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTimeEntryRepository extends JpaRepository<WorkTimeEntry, Long> {

    // ۱. مجموع ساعات ماهانه برای هر پروژه
    @Query(value =
            "SELECT " +
                    "    w.contract_id as contractId, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.contract_id, w.year, w.month " +
                    "ORDER BY w.contract_id, w.year, w.month",
            nativeQuery = true)
    List<MonthlyProjectHoursDTO> findMonthlyHoursPerProject();

    // ۲. مجموع ساعات ماهانه برای هر کارمند
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.contract_id) as projectCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.employee_id, w.year, w.month",
            nativeQuery = true)
    List<MonthlyEmployeeHoursDTO> findMonthlyHoursPerEmployee();

    // ۳. مجموع ساعات ماهانه برای هر کارمند در هر پروژه
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.contract_id as contractId, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.employee_id, w.employee_name, w.contract_id, w.year, w.month " +
                    "ORDER BY w.employee_id, w.contract_id, w.year, w.month",
            nativeQuery = true)
    List<MonthlyEmployeeProjectHoursDTO> findMonthlyHoursPerEmployeePerProject();

    // ۴. ساعات کار ماهانه یک کارمند خاص در یک پروژه خاص
    @Query(value =
            "SELECT " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    AVG(w.hours_worked) as avgMonthlyHours, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "    AND w.contract_id = ?2 " +
                    "GROUP BY w.year, w.month " +
                    "ORDER BY w.year, w.month",
            nativeQuery = true)
    List<Object[]> findMonthlyHoursForSpecificEmployeeAndProject(
            Long employeeId,
            Long contractId
    );

    // ۵. ساعات کار ماهانه یک کارمند در تمام پروژه‌ها
    @Query(value =
            "SELECT " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.contract_id) as projectCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.year, w.month " +
                    "ORDER BY w.year, w.month",
            nativeQuery = true)
    List<Object[]> findMonthlyHoursForEmployeeAllProjects(Long employeeId);

    // ۶. ساعات کار ماهانه یک پروژه با جزئیات کارمندان
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.contract_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.employee_id, w.year, w.month",
            nativeQuery = true)
    List<Object[]> findMonthlyHoursForProjectWithEmployees(
            Long contractId
    );

    // ۷. ساعت کار ماهانه همه افراد
    @Query(value =
            "SELECT " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    AVG(w.hours_worked) as avgHoursPerEmployee, " +
                    "    COUNT(*) as totalEntries " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.year, w.month " +
                    "ORDER BY w.year, w.month",
            nativeQuery = true)
    List<Object[]> findMonthlyHoursAllEmployees();

    // ۸. ساعت کار ماهانه همه پروژه‌ها
    @Query(value =
            "SELECT " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    COUNT(DISTINCT w.contract_id) as projectCount, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    AVG(w.hours_worked) as avgHoursPerProject, " +
                    "    COUNT(*) as totalEntries " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.year, w.month " +
                    "ORDER BY w.year, w.month",
            nativeQuery = true)
    List<Object[]> findMonthlyHoursAllProjects();

    // ۹. محاسبه میانگین همه افراد ماهانه
    @Query(value =
            "SELECT " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "    AVG(employee_monthly_hours) as avgMonthlyHoursAllEmployees " +
                    "FROM ( " +
                    "    SELECT " +
                    "        w2.year, " +
                    "        w2.month, " +
                    "        w2.employee_id, " +
                    "        SUM(w2.hours_worked) as employee_monthly_hours " +
                    "    FROM work_time_entry w2 " +
                    "    GROUP BY w2.year, w2.month, w2.employee_id " +
                    ") w " +
                    "GROUP BY w.year, w.month " +
                    "ORDER BY w.year, w.month",
            nativeQuery = true)
    List<Object[]> findAverageMonthlyHoursAllEmployees();

    // ۱۰. محاسبه انحراف معیار هر فرد از میانگین خودش ماهانه
    @Query(value =
            "WITH employee_monthly_stats AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "        SUM(w.hours_worked) as monthly_hours, " +
                    "        AVG(SUM(w.hours_worked)) OVER (PARTITION BY w.employee_id) as employee_avg, " +
                    "        STDDEV(SUM(w.hours_worked)) OVER (PARTITION BY w.employee_id) as employee_stddev " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    ems.employee_id as employeeId, " +
                    "    ems.year as year, " +
                    "    ems.month as month, " +
                    "    ems.yearMonth as yearMonth, " +
                    "    ems.monthly_hours as monthlyHours, " +
                    "    ems.employee_avg as employeeAvg, " +
                    "    ems.employee_stddev as employeeStdDev " +
                    "FROM employee_monthly_stats ems " +
                    "ORDER BY ems.employee_id, ems.year, ems.month",
            nativeQuery = true)
    List<EmployeeDeviationDTO> findEmployeeDeviationFromOwnAverage();

    // ۱۱. محاسبه انحراف معیار هر فرد از 160 ساعت ماهانه
    @Query(value =
            "WITH employee_monthly_hours AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "        SUM(w.hours_worked) as monthly_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    emh.employee_id as employeeId, " +
                    "    emh.employee_name as employeeName, " +
                    "    emh.year as year, " +
                    "    emh.month as month, " +
                    "    emh.yearMonth as yearMonth, " +
                    "    emh.monthly_hours as monthlyHours, " +
                    "    (emh.monthly_hours - 160) as deviationFrom160, " +
                    "    ABS(emh.monthly_hours - 160) as absoluteDeviationFrom160 " +
                    "FROM employee_monthly_hours emh " +
                    "ORDER BY emh.employee_id, emh.year, emh.month",
            nativeQuery = true)
    List<Object[]> findEmployeeDeviationFrom160Hours();

    // ۱۲. محاسبه انحراف معیار هر فرد از میانگین حضور همه افراد
    @Query(value =
            "WITH monthly_overall_stats AS ( " +
                    "    SELECT " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "        AVG(employee_monthly_hours) as overall_avg, " +
                    "        STDDEV(employee_monthly_hours) as overall_stddev " +
                    "    FROM ( " +
                    "        SELECT " +
                    "            w2.year, " +
                    "            w2.month, " +
                    "            w2.employee_id, " +
                    "            SUM(w2.hours_worked) as employee_monthly_hours " +
                    "        FROM work_time_entry w2 " +
                    "        GROUP BY w2.year, w2.month, w2.employee_id " +
                    "    ) w " +
                    "    GROUP BY w.year, w.month " +
                    "), " +
                    "employee_monthly_hours AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "        SUM(w.hours_worked) as monthly_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    emh.employee_id as employeeId, " +
                    "    emh.employee_name as employeeName, " +
                    "    emh.year as year, " +
                    "    emh.month as month, " +
                    "    emh.yearMonth as yearMonth, " +
                    "    emh.monthly_hours as monthlyHours, " +
                    "    mos.overall_avg as overallAverage, " +
                    "    mos.overall_stddev as overallStdDev, " +
                    "    (emh.monthly_hours - mos.overall_avg) as deviationFromOverallAvg, " +
                    "    (emh.monthly_hours - mos.overall_avg) / NULLIF(mos.overall_stddev, 0) as zScore " +
                    "FROM employee_monthly_hours emh " +
                    "JOIN monthly_overall_stats mos ON emh.year = mos.year AND emh.month = mos.month " +
                    "ORDER BY emh.employee_id, emh.year, emh.month",
            nativeQuery = true)
    List<Object[]> findEmployeeDeviationFromOverallAverage();

    // ۱۳. شناسایی افراد پرکار (بیش از 160) و کم کار (کمتر از 100) و متوسط (بین 100 تا 160)
    @Query(value =
            "WITH employee_monthly_hours AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CONCAT(CAST(w.year AS TEXT), '-', LPAD(CAST(w.month AS TEXT), 2, '0')) as yearMonth, " +
                    "        SUM(w.hours_worked) as total_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    emh.employee_id as employeeId, " +
                    "    emh.employee_name as employeeName, " +
                    "    emh.year as year, " +
                    "    emh.month as month, " +
                    "    emh.yearMonth as yearMonth, " +
                    "    emh.total_hours as totalHours, " +
                    "    CASE " +
                    "        WHEN emh.total_hours > 160 THEN 'پرکار (بیش از 160 ساعت)' " +
                    "        WHEN emh.total_hours < 100 THEN 'کم کار (کمتر از 100 ساعت)' " +
                    "        ELSE 'متوسط (بین 100 تا 160 ساعت)' " +
                    "    END as category " +
                    "FROM employee_monthly_hours emh " +
                    "ORDER BY emh.year, emh.month, emh.total_hours DESC",
            nativeQuery = true)
    List<EmployeeCategoryDTO> categorizeEmployeesByWorkHours();

    // ۱۴. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه پروژه‌ها
    @Query(value =
            "SELECT " +
                    "    w.contract_id as contractId, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CASE " +
                    "        WHEN w.month = 1 THEN 'فروردین' " +
                    "        WHEN w.month = 2 THEN 'اردیبهشت' " +
                    "        WHEN w.month = 3 THEN 'خرداد' " +
                    "        WHEN w.month = 4 THEN 'تیر' " +
                    "        WHEN w.month = 5 THEN 'مرداد' " +
                    "        WHEN w.month = 6 THEN 'شهریور' " +
                    "        WHEN w.month = 7 THEN 'مهر' " +
                    "        WHEN w.month = 8 THEN 'آبان' " +
                    "        WHEN w.month = 9 THEN 'آذر' " +
                    "        WHEN w.month = 10 THEN 'دی' " +
                    "        WHEN w.month = 11 THEN 'بهمن' " +
                    "        WHEN w.month = 12 THEN 'اسفند' " +
                    "    END as monthName, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.month IN (1, 2) " +  // فروردین (1) و اردیبهشت (2)
                    "GROUP BY w.contract_id, w.year, w.month " +
                    "ORDER BY w.year, w.month, w.contract_id",
            nativeQuery = true)
    List<Object[]> findHoursForFarvardinOrdibeheshtAllProjects();

    // ۱۵. محاسبه ساعت کاری در ماه‌های فروردین تا اردیبهشت برای همه افراد
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CASE " +
                    "        WHEN w.month = 1 THEN 'فروردین' " +
                    "        WHEN w.month = 2 THEN 'اردیبهشت' " +
                    "        WHEN w.month = 3 THEN 'خرداد' " +
                    "        WHEN w.month = 4 THEN 'تیر' " +
                    "        WHEN w.month = 5 THEN 'مرداد' " +
                    "        WHEN w.month = 6 THEN 'شهریور' " +
                    "        WHEN w.month = 7 THEN 'مهر' " +
                    "        WHEN w.month = 8 THEN 'آبان' " +
                    "        WHEN w.month = 9 THEN 'آذر' " +
                    "        WHEN w.month = 10 THEN 'دی' " +
                    "        WHEN w.month = 11 THEN 'بهمن' " +
                    "        WHEN w.month = 12 THEN 'اسفند' " +
                    "    END as monthName, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.contract_id) as projectCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.month IN (1, 2) " +  // فروردین (1) و اردیبهشت (2)
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year, w.month, w.employee_id",
            nativeQuery = true)
    List<Object[]> findHoursForFarvardinOrdibeheshtAllEmployees();

    // ۱۶. آمار کلی ماهانه برای همه ماه‌ها
    @Query(value =
            "SELECT " +
                    "    w.month as monthNumber, " +
                    "    CASE " +
                    "        WHEN w.month = 1 THEN 'فروردین' " +
                    "        WHEN w.month = 2 THEN 'اردیبهشت' " +
                    "        WHEN w.month = 3 THEN 'خرداد' " +
                    "        WHEN w.month = 4 THEN 'تیر' " +
                    "        WHEN w.month = 5 THEN 'مرداد' " +
                    "        WHEN w.month = 6 THEN 'شهریور' " +
                    "        WHEN w.month = 7 THEN 'مهر' " +
                    "        WHEN w.month = 8 THEN 'آبان' " +
                    "        WHEN w.month = 9 THEN 'آذر' " +
                    "        WHEN w.month = 10 THEN 'دی' " +
                    "        WHEN w.month = 11 THEN 'بهمن' " +
                    "        WHEN w.month = 12 THEN 'اسفند' " +
                    "    END as monthName, " +
                    "    SUM(w.hours_worked) as totalHours, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    COUNT(DISTINCT w.contract_id) as projectCount " +
                    "FROM work_time_entry w " +
                    "GROUP BY w.month " +
                    "ORDER BY w.month",
            nativeQuery = true)
    List<MonthComparisonDTO> findMonthlyComparisonAllMonths();

    // کوئری‌های کمکی
    @Query("SELECT w FROM WorkTimeEntry w WHERE w.employeeId = ?1 AND w.contract.id = ?2")
    List<WorkTimeEntry> findByEmployeeIdAndContractId(Long employeeId, Long contractId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.employeeId = ?1")
    List<WorkTimeEntry> findByEmployeeId(Long employeeId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.contract.id = ?1")
    List<WorkTimeEntry> findByContractId(Long contractId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.year = ?1 AND w.month = ?2")
    List<WorkTimeEntry> findByYearAndMonth(Integer year, Integer month);

    @Query("SELECT DISTINCT w.year FROM WorkTimeEntry w ORDER BY w.year")
    List<Integer> findDistinctYears();

    @Query("SELECT DISTINCT w.month FROM WorkTimeEntry w ORDER BY w.month")
    List<Integer> findDistinctMonths();

    List<WorkTimeEntry> findAllByContractId(Long contractId);
}