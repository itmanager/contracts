package com.contract.repository;

import com.contract.domain.WorkTimeEntry;
import com.contract.domain.enumeration.overTime.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOverTimeReppository extends JpaRepository<WorkTimeEntry, Long> {

    // ۱. محاسبه اضافه کار روزانه هر فرد (بیش از ۸ ساعت)
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    employee_id as employeeId, " +
                    "    employee_name as employeeName, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    day as day, " +
                    "    total_daily_hours as totalDailyHours, " +
                    "    CASE " +
                    "        WHEN total_daily_hours > 8 THEN total_daily_hours - 8 " +
                    "        ELSE 0 " +
                    "    END as overtimeHours " +
                    "FROM daily_work " +
                    "WHERE total_daily_hours > 8 " +
                    "ORDER BY year, month, day, employee_id",
            nativeQuery = true)
    List<EmployeeOvertimeDailyDTO> findDailyOvertime();

    // ۲. اضافه کار ماهانه هر فرد (جمع اضافه کارهای روزانه)
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    employee_id as employeeId, " +
                    "    employee_name as employeeName, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime " +
                    "FROM daily_work " +
                    "GROUP BY employee_id, employee_name, year, month " +
                    "HAVING SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) > 0 " +
                    "ORDER BY year, month, totalOvertime DESC",
            nativeQuery = true)
    List<EmployeeOvertimeMonthlyDTO> findMonthlyOvertimePerEmployee();

    // ۳. اضافه کاری‌های فرد روی پروژه‌ها به تفکیک ماه
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.contract_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as project_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.employee_name, w.contract_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    "), " +
                    "employee_daily_total AS ( " +
                    "    SELECT " +
                    "        employee_id, " +
                    "        year, " +
                    "        month, " +
                    "        day, " +
                    "        SUM(project_daily_hours) as total_daily_hours " +
                    "    FROM daily_work " +
                    "    GROUP BY employee_id, year, month, day " +
                    "), " +
                    "overtime_calc AS ( " +
                    "    SELECT " +
                    "        dw.employee_id, " +
                    "        dw.employee_name, " +
                    "        dw.contract_id, " +
                    "        dw.year, " +
                    "        dw.month, " +
                    "        dw.day, " +
                    "        dw.project_daily_hours, " +
                    "        edt.total_daily_hours, " +
                    "        CASE " +
                    "            WHEN edt.total_daily_hours > 8 THEN " +
                    "                (dw.project_daily_hours / edt.total_daily_hours) * (edt.total_daily_hours - 8) " +
                    "            ELSE 0 " +
                    "        END as project_overtime " +
                    "    FROM daily_work dw " +
                    "    JOIN employee_daily_total edt " +
                    "        ON dw.employee_id = edt.employee_id " +
                    "        AND dw.year = edt.year " +
                    "        AND dw.month = edt.month " +
                    "        AND dw.day = edt.day " +
                    "    WHERE edt.total_daily_hours > 8 " +
                    ") " +
                    "SELECT " +
                    "    employee_id as employeeId, " +
                    "    employee_name as employeeName, " +
                    "    contract_id as contractId, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    CONCAT(year, '-', LPAD(CAST(month AS VARCHAR), 2, '0')) as yearMonth, " +
                    "    SUM(project_overtime) as projectOvertime, " +
                    "    SUM(project_daily_hours) as projectTotalHours, " +
                    "    CASE " +
                    "        WHEN SUM(project_daily_hours) > 0 THEN " +
                    "            (SUM(project_overtime) / SUM(project_daily_hours)) * 100 " +
                    "        ELSE 0 " +
                    "    END as overtimePercentage " +
                    "FROM overtime_calc " +
                    "GROUP BY employee_id, employee_name, contract_id, year, month " +
                    "ORDER BY year, month, employee_id, contract_id",
            nativeQuery = true)
    List<EmployeeProjectOvertimeDTO> findProjectOvertimePerEmployee();

    // ۴. کل اضافه کاری در فروردین (ماه ۱)
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.month = 1 " + // فروردین
                    "    GROUP BY w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    year as year, " +
                    "    month as month, " +
                    "    'فروردین' as monthName, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime, " +
                    "    COUNT(DISTINCT employee_id) as employeeCount, " +
                    "    COUNT(*) as overtimeDaysCount " +
                    "FROM daily_work " +
                    "GROUP BY year, month " +
                    "ORDER BY year",
            nativeQuery = true)
    List<MonthlyOvertimeSummaryDTO> findFarvardinOvertimeSummary();

    // ۵. کل اضافه کاری در اردیبهشت (ماه ۲)
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.month = 2 " + // اردیبهشت
                    "    GROUP BY w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    year as year, " +
                    "    month as month, " +
                    "    'اردیبهشت' as monthName, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime, " +
                    "    COUNT(DISTINCT employee_id) as employeeCount, " +
                    "    COUNT(*) as overtimeDaysCount " +
                    "FROM daily_work " +
                    "GROUP BY year, month " +
                    "ORDER BY year",
            nativeQuery = true)
    List<MonthlyOvertimeSummaryDTO> findOrdibeheshtOvertimeSummary();

    // ۶. کل اضافه کاری همه ماه‌های سال به تفکیک
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    year as year, " +
                    "    month as month, " +
                    "    CASE " +
                    "        WHEN month = 1 THEN 'فروردین' " +
                    "        WHEN month = 2 THEN 'اردیبهشت' " +
                    "        WHEN month = 3 THEN 'خرداد' " +
                    "        WHEN month = 4 THEN 'تیر' " +
                    "        WHEN month = 5 THEN 'مرداد' " +
                    "        WHEN month = 6 THEN 'شهریور' " +
                    "        WHEN month = 7 THEN 'مهر' " +
                    "        WHEN month = 8 THEN 'آبان' " +
                    "        WHEN month = 9 THEN 'آذر' " +
                    "        WHEN month = 10 THEN 'دی' " +
                    "        WHEN month = 11 THEN 'بهمن' " +
                    "        WHEN month = 12 THEN 'اسفند' " +
                    "    END as monthName, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime, " +
                    "    COUNT(DISTINCT employee_id) as employeeCount, " +
                    "    COUNT(*) as overtimeDaysCount " +
                    "FROM daily_work " +
                    "GROUP BY year, month " +
                    "ORDER BY year, month",
            nativeQuery = true)
    List<MonthlyOvertimeSummaryDTO> findMonthlyOvertimeSummaryAllMonths();

    // ۷. اضافه کار پروژه‌ها به تفکیک ماه
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.contract_id, " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as project_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    GROUP BY w.contract_id, w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    "), " +
                    "employee_daily_total AS ( " +
                    "    SELECT " +
                    "        employee_id, " +
                    "        year, " +
                    "        month, " +
                    "        day, " +
                    "        SUM(project_daily_hours) as total_daily_hours " +
                    "    FROM daily_work " +
                    "    GROUP BY employee_id, year, month, day " +
                    "), " +
                    "overtime_calc AS ( " +
                    "    SELECT " +
                    "        dw.contract_id, " +
                    "        dw.employee_id, " +
                    "        dw.year, " +
                    "        dw.month, " +
                    "        dw.day, " +
                    "        dw.project_daily_hours, " +
                    "        edt.total_daily_hours, " +
                    "        CASE " +
                    "            WHEN edt.total_daily_hours > 8 THEN " +
                    "                (dw.project_daily_hours / edt.total_daily_hours) * (edt.total_daily_hours - 8) " +
                    "            ELSE 0 " +
                    "        END as project_overtime " +
                    "    FROM daily_work dw " +
                    "    JOIN employee_daily_total edt " +
                    "        ON dw.employee_id = edt.employee_id " +
                    "        AND dw.year = edt.year " +
                    "        AND dw.month = edt.month " +
                    "        AND dw.day = edt.day " +
                    "    WHERE edt.total_daily_hours > 8 " +
                    ") " +
                    "SELECT " +
                    "    contract_id as contractId, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    CONCAT(year, '-', LPAD(CAST(month AS VARCHAR), 2, '0')) as yearMonth, " +
                    "    SUM(project_overtime) as totalOvertime, " +
                    "    COUNT(DISTINCT employee_id) as employeeCount " +
                    "FROM overtime_calc " +
                    "GROUP BY contract_id, year, month " +
                    "ORDER BY year, month, contract_id",
            nativeQuery = true)
    List<ProjectOvertimeMonthlyDTO> findMonthlyOvertimePerProject();

    // ۸. اضافه کار ماهانه برای یک فرد خاص
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.employee_id = CAST(?1 AS BIGINT) " +
                    "    GROUP BY w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    employee_id as employeeId, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime " +
                    "FROM daily_work " +
                    "GROUP BY employee_id, year, month " +
                    "ORDER BY year, month",
            nativeQuery = true)
    List<EmployeeOvertimeMonthlyDTO> findMonthlyOvertimeByEmployeeId(Long employeeId);

    // ۹. اضافه کار ماهانه برای یک پروژه خاص
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.contract_id, " +
                    "        w.employee_id, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as project_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.contract_id = CAST(?1 AS BIGINT) " +
                    "    GROUP BY w.contract_id, w.employee_id, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    "), " +
                    "employee_daily_total AS ( " +
                    "    SELECT " +
                    "        employee_id, " +
                    "        year, " +
                    "        month, " +
                    "        day, " +
                    "        SUM(project_daily_hours) as total_daily_hours " +
                    "    FROM daily_work " +
                    "    GROUP BY employee_id, year, month, day " +
                    "), " +
                    "overtime_calc AS ( " +
                    "    SELECT " +
                    "        dw.contract_id, " +
                    "        dw.employee_id, " +
                    "        dw.year, " +
                    "        dw.month, " +
                    "        dw.day, " +
                    "        dw.project_daily_hours, " +
                    "        edt.total_daily_hours, " +
                    "        CASE " +
                    "            WHEN edt.total_daily_hours > 8 THEN " +
                    "                (dw.project_daily_hours / edt.total_daily_hours) * (edt.total_daily_hours - 8) " +
                    "            ELSE 0 " +
                    "        END as project_overtime " +
                    "    FROM daily_work dw " +
                    "    JOIN employee_daily_total edt " +
                    "        ON dw.employee_id = edt.employee_id " +
                    "        AND dw.year = edt.year " +
                    "        AND dw.month = edt.month " +
                    "        AND dw.day = edt.day " +
                    "    WHERE edt.total_daily_hours > 8 " +
                    ") " +
                    "SELECT " +
                    "    contract_id as contractId, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    CONCAT(year, '-', LPAD(CAST(month AS VARCHAR), 2, '0')) as yearMonth, " +
                    "    SUM(project_overtime) as totalOvertime, " +
                    "    COUNT(DISTINCT employee_id) as employeeCount " +
                    "FROM overtime_calc " +
                    "GROUP BY contract_id, year, month " +
                    "ORDER BY year, month",
            nativeQuery = true)
    List<ProjectOvertimeMonthlyDTO> findMonthlyOvertimeByContractId(Long contractId);

    // ۱۰. ۱۰ فرد با بیشترین اضافه کار در یک ماه خاص
    @Query(value =
            "WITH daily_work AS ( " +
                    "    SELECT " +
                    "        w.employee_id, " +
                    "        w.employee_name, " +
                    "        w.year, " +
                    "        w.month, " +
                    "        CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) as day, " +
                    "        SUM(w.hours_worked) as total_daily_hours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.year = CAST(?1 AS INTEGER) AND w.month = CAST(?2 AS INTEGER) " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month, " +
                    "             CAST(SUBSTRING(CAST(w.entry_date AS VARCHAR), 7, 2) AS INTEGER) " +
                    ") " +
                    "SELECT " +
                    "    employee_id as employeeId, " +
                    "    employee_name as employeeName, " +
                    "    year as year, " +
                    "    month as month, " +
                    "    SUM(CASE WHEN total_daily_hours > 8 THEN total_daily_hours - 8 ELSE 0 END) as totalOvertime " +
                    "FROM daily_work " +
                    "GROUP BY employee_id, employee_name, year, month " +
                    "ORDER BY totalOvertime DESC " +
                    "LIMIT 10",
            nativeQuery = true)
    List<EmployeeOvertimeMonthlyDTO> findTop10OvertimeEmployeesByMonth(Integer year, Integer month);

    // کوئری‌های کمکی
    @Query("SELECT w FROM WorkTimeEntry w WHERE w.employeeId = :employeeId")
    List<WorkTimeEntry> findByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.contract.id = :contractId")
    List<WorkTimeEntry> findByContractId(@Param("contractId") Long contractId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.year = :year AND w.month = :month")
    List<WorkTimeEntry> findByYearAndMonth(@Param("year") Integer year,
                                           @Param("month") Integer month);
}