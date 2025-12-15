package com.contract.repository;

import com.contract.domain.WorkTimeEntry;
import com.contract.service.dto.analysisWorkDtos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface WorkTimeEntryRepository extends JpaRepository<WorkTimeEntry, Long> {

    // 1. ساعت حضور کارمند خاص به تفکیک ماه - Native Query با Object[]
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    COUNT(DISTINCT w.contract_id) as contractCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findMonthlyPresenceByEmployeeNative(Long employeeId);

    default List<EmployeeMonthlyPresenceDTO> findMonthlyPresenceByEmployee(Long employeeId) {
        List<Object[]> results = findMonthlyPresenceByEmployeeNative(employeeId);
        return results.stream().map(row -> new EmployeeMonthlyPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0,
                row[6] != null ? ((Number) row[6]).intValue() : 0,
                row[7] != null ? ((Number) row[7]).intValue() : 0
        )).collect(Collectors.toList());
    }

    // 2. ساعت اضافه کار یک کارمند خاص به تفکیک ماه - Native Query
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findMonthlyOvertimeByEmployeeNative(Long employeeId);

    default List<EmployeeMonthlyOvertimeDTO> findMonthlyOvertimeByEmployee(Long employeeId) {
        List<Object[]> results = findMonthlyOvertimeByEmployeeNative(employeeId);
        return results.stream().map(row -> new EmployeeMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 3. مقایسه ساعت حضور یک کارمند خاص با میانگین حضورش در ماه - Native Query
    @Query(value =
            "WITH emp_summary AS ( " +
                    "    SELECT " +
                    "        w.employee_id as employeeId, " +
                    "        w.employee_name as employeeName, " +
                    "        w.year as year, " +
                    "        w.month as month, " +
                    "        CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "        CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "        AVG(CAST(SUM(w.hours_worked) AS float)) OVER (PARTITION BY w.employee_id) as avgHours " +
                    "    FROM work_time_entry w " +
                    "    WHERE w.employee_id = ?1 " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    employeeId, employeeName, year, month, yearMonth, totalHours, avgHours, " +
                    "    (totalHours - avgHours) as difference " +
                    "FROM emp_summary " +
                    "ORDER BY year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> compareEmployeeWithOwnAverageNative(Long employeeId);

    default List<EmployeeComparisonDTO> compareEmployeeWithOwnAverage(Long employeeId) {
        List<Object[]> results = compareEmployeeWithOwnAverageNative(employeeId);
        return results.stream().map(row -> new EmployeeComparisonDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0,
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 4. لیست اختلاف ساعت حضور با میانگین (برای همه کارمندان) - Native Query
    @Query(value =
            "WITH emp_summary AS ( " +
                    "    SELECT " +
                    "        w.employee_id as employeeId, " +
                    "        w.employee_name as employeeName, " +
                    "        w.year as year, " +
                    "        w.month as month, " +
                    "        CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "        CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "        AVG(CAST(SUM(w.hours_worked) AS float)) OVER (PARTITION BY w.employee_id) as avgHours " +
                    "    FROM work_time_entry w " +
                    "    WHERE (?1 IS NULL OR w.employee_id = ?1) " +
                    "    GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    employeeId, employeeName, year, month, yearMonth, totalHours, avgHours, " +
                    "    (totalHours - avgHours) as difference " +
                    "FROM emp_summary " +
                    "ORDER BY employeeId, year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> findAllEmployeesDifferenceFromAverageNative(Long employeeId);

    default List<EmployeeComparisonDTO> findAllEmployeesDifferenceFromAverage(Long employeeId) {
        List<Object[]> results = findAllEmployeesDifferenceFromAverageNative(employeeId);
        return results.stream().map(row -> new EmployeeComparisonDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0,
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 5. لیست اختلاف حضور یک فرد با 160 ساعت در ماه - Native Query
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    ?2 as targetHours, " +
                    "    (CAST(SUM(w.hours_worked) AS float) - ?2) as difference " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findDifferenceFromTargetHoursNative(Long employeeId, Integer targetHours);

    default List<EmployeeDifferenceDTO> findDifferenceFromTargetHours(Long employeeId, Integer targetHours) {
        List<Object[]> results = findDifferenceFromTargetHoursNative(employeeId, targetHours);
        return results.stream().map(row -> new EmployeeDifferenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0,
                row[6] != null ? ((Number) row[6]).doubleValue() : 160.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 6. لیست ساعت قراردادهایی که یک فرد در آنها کار کرده است - Native Query
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as contractTitle, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, c.id, c.contract_number, c.title, w.year, w.month " +
                    "ORDER BY c.contract_number, w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findEmployeeContractsMonthlyNative(Long employeeId);

    default List<EmployeeContractHoursDTO> findEmployeeContractsMonthly(Long employeeId) {
        List<Object[]> results = findEmployeeContractsMonthlyNative(employeeId);
        return results.stream().map(row -> new EmployeeContractHoursDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).longValue(),
                (String) row[3],
                (String) row[4],
                ((Number) row[5]).intValue(),
                ((Number) row[6]).intValue(),
                (String) row[7],
                row[8] != null ? ((Number) row[8]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 7. لیست حضور همه کارمندان بر اساس فیلترها - کارفرما
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    c.employer_name as employerName, " +
                    "    c.contractor_name as contractorName, " +
                    "    c.user_name as userName " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.employer_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, c.id, c.contract_number, w.year, w.month, " +
                    "   c.employer_name, c.contractor_name, c.user_name " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesPresenceFilteredNativeByEmployer(Long employerId);

    default List<FilteredPresenceDTO> findAllEmployeesPresenceFilteredByEmployer(Long employerId) {
        List<Object[]> results = findAllEmployeesPresenceFilteredNativeByEmployer(employerId);
        return results.stream().map(row -> new FilteredPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).longValue(),
                (String) row[3],
                ((Number) row[4]).intValue(),
                ((Number) row[5]).intValue(),
                (String) row[6],
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0,
                (String) row[8],
                (String) row[9],
                (String) row[10]
        )).collect(Collectors.toList());
    }

    // 7. لیست حضور همه کارمندان بر اساس فیلترها - مجری
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    c.employer_name as employerName, " +
                    "    c.contractor_name as contractorName, " +
                    "    c.user_name as userName " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.contractor_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, c.id, c.contract_number, w.year, w.month, " +
                    "   c.employer_name, c.contractor_name, c.user_name " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesPresenceFilteredNativeByContractor(Long contractorId);

    default List<FilteredPresenceDTO> findAllEmployeesPresenceFilteredByContractor(Long contractorId) {
        List<Object[]> results = findAllEmployeesPresenceFilteredNativeByContractor(contractorId);
        return results.stream().map(row -> new FilteredPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).longValue(),
                (String) row[3],
                ((Number) row[4]).intValue(),
                ((Number) row[5]).intValue(),
                (String) row[6],
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0,
                (String) row[8],
                (String) row[9],
                (String) row[10]
        )).collect(Collectors.toList());
    }

    // 7. لیست حضور همه کارمندان بر اساس فیلترها - بهره بردار
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    c.employer_name as employerName, " +
                    "    c.contractor_name as contractorName, " +
                    "    c.user_name as userName " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.user_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, c.id, c.contract_number, w.year, w.month, " +
                    "   c.employer_name, c.contractor_name, c.user_name " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesPresenceFilteredNativeByUser(Long userId);

    default List<FilteredPresenceDTO> findAllEmployeesPresenceFilteredByUser(Long userId) {
        List<Object[]> results = findAllEmployeesPresenceFilteredNativeByUser(userId);
        return results.stream().map(row -> new FilteredPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).longValue(),
                (String) row[3],
                ((Number) row[4]).intValue(),
                ((Number) row[5]).intValue(),
                (String) row[6],
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0,
                (String) row[8],
                (String) row[9],
                (String) row[10]
        )).collect(Collectors.toList());
    }

    // 8. ساعت اضافه کار همه کارمندان بر اساس فیلترها - کارفرما
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.employer_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesOvertimeFilteredNativeByEmployer(Long employerId);

    default List<EmployeeMonthlyOvertimeDTO> findAllEmployeesOvertimeFilteredByEmployer(Long employerId) {
        List<Object[]> results = findAllEmployeesOvertimeFilteredNativeByEmployer(employerId);
        return results.stream().map(row -> new EmployeeMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 8. ساعت اضافه کار همه کارمندان بر اساس فیلترها - مجری
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.contractor_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesOvertimeFilteredNativeByContractor(Long contractorId);

    default List<EmployeeMonthlyOvertimeDTO> findAllEmployeesOvertimeFilteredByContractor(Long contractorId) {
        List<Object[]> results = findAllEmployeesOvertimeFilteredNativeByContractor(contractorId);
        return results.stream().map(row -> new EmployeeMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 8. ساعت اضافه کار همه کارمندان بر اساس فیلترها - کاربر
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.user_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesOvertimeFilteredNativeByUser(Long userId);

    default List<EmployeeMonthlyOvertimeDTO> findAllEmployeesOvertimeFilteredByUser(Long userId) {
        List<Object[]> results = findAllEmployeesOvertimeFilteredNativeByUser(userId);
        return results.stream().map(row -> new EmployeeMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 9. ساعت تاخیر برای هر کارمند به تفکیک ماه - Native Query
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE " +
                    "        WHEN w.time_start > 8 THEN " +
                    "            (w.time_start - 8)" +
                    "        ELSE 0 " +
                    "    END) AS float) as delayHours " +
                    "FROM work_time_entry w " +
                    "WHERE w.employee_id = ?1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findMonthlyDelayByEmployeeNative(Long employeeId);

    default List<MonthlyDelayDTO> findMonthlyDelayByEmployee(Long employeeId) {
        List<Object[]> results = findMonthlyDelayByEmployeeNative(employeeId);
        return results.stream().map(row -> new MonthlyDelayDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 10. ساعت تاخیر همه کارمندان بر اساس فیلترها - کارفرما
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE " +
                    "        WHEN w.time_start > 8 THEN " +
                    "            (w.time_start - 8) " +
                    "        ELSE 0 " +
                    "    END) AS float) as delayHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.employer_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesDelayFilteredNativeByEmployer(Long employerId);

    default List<MonthlyDelayDTO> findAllEmployeesDelayFilteredByEmployer(Long employerId) {
        List<Object[]> results = findAllEmployeesDelayFilteredNativeByEmployer(employerId);
        return results.stream().map(row -> new MonthlyDelayDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 10. ساعت تاخیر همه کارمندان بر اساس فیلترها - مجری
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE " +
                    "        WHEN w.time_start > 8 THEN " +
                    "            ((w.time_start  - 8) " +
                    "        ELSE 0 " +
                    "    END) AS float) as delayHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.contractor_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesDelayFilteredNativeByContractor(Long contractorId);

    default List<MonthlyDelayDTO> findAllEmployeesDelayFilteredByContractor(Long contractorId) {
        List<Object[]> results = findAllEmployeesDelayFilteredNativeByContractor(contractorId);
        return results.stream().map(row -> new MonthlyDelayDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 10. ساعت تاخیر همه کارمندان بر اساس فیلترها - کاربر
    @Query(value =
            "SELECT " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE " +
                    "        WHEN w.time_start > 8 THEN " +
                    "            (w.time_start - 8) " +
                    "        ELSE 0 " +
                    "    END) AS float) as delayHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.user_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employee_name",
            nativeQuery = true)
    List<Object[]> findAllEmployeesDelayFilteredNativeByUser(Long userId);

    default List<MonthlyDelayDTO> findAllEmployeesDelayFilteredByUser(Long userId) {
        List<Object[]> results = findAllEmployeesDelayFilteredNativeByUser(userId);
        return results.stream().map(row -> new MonthlyDelayDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                ((Number) row[2]).intValue(),
                ((Number) row[3]).intValue(),
                (String) row[4],
                row[5] != null ? ((Number) row[5]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 11. ساعت حضور قرارداد خاص به تفکیک ماه - Native Query
    @Query(value =
            "SELECT " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as title, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE c.id = ?1 " +
                    "GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findMonthlyPresenceByContractNative(Long contractId);

    default List<ContractMonthlyPresenceDTO> findMonthlyPresenceByContract(Long contractId) {
        List<Object[]> results = findMonthlyPresenceByContractNative(contractId);
        return results.stream().map(row -> new ContractMonthlyPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0,
                row[8] != null ? ((Number) row[8]).doubleValue() : 0
        )).collect(Collectors.toList());
    }

    // 12. ساعت اضافه کار یک قرارداد خاص به تفکیک ماه - Native Query
    @Query(value =
            "SELECT " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as title, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE c.id = ?1 " +
                    "GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findMonthlyOvertimeByContractNative(Long contractId);

    default List<ContractMonthlyOvertimeDTO> findMonthlyOvertimeByContract(Long contractId) {
        List<Object[]> results = findMonthlyOvertimeByContractNative(contractId);
        return results.stream().map(row -> new ContractMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 13. مقایسه ساعت حضور یک قرارداد خاص با میانگین حضورش در ماه - Native Query
    @Query(value =
            "WITH contract_summary AS ( " +
                    "    SELECT " +
                    "        c.id as contractId, " +
                    "        c.contract_number as contractNumber, " +
                    "        c.title as contractTitle, " +
                    "        w.year as year, " +
                    "        w.month as month, " +
                    "        CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "        CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "        AVG(CAST(SUM(w.hours_worked) AS float)) OVER (PARTITION BY c.id) as avgHours " +
                    "    FROM work_time_entry w " +
                    "    JOIN contract c ON w.contract_id = c.id " +
                    "    WHERE c.id = ?1 " +
                    "    GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    contractId, contractNumber, contractTitle, year, month, yearMonth, totalHours, avgHours, " +
                    "    (totalHours - avgHours) as difference " +
                    "FROM contract_summary " +
                    "ORDER BY year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> compareContractWithOwnAverageNative(Long contractId);

    default List<ContractComparisonDTO> compareContractWithOwnAverage(Long contractId) {
        List<Object[]> results = compareContractWithOwnAverageNative(contractId);
        return results.stream().map(row -> new ContractComparisonDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0,
                row[8] != null ? ((Number) row[8]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 14. لیست اختلاف ساعت حضور قرارداد خاص با میانگین (برای همه قراردادها) - Native Query
    @Query(value =
            "WITH contract_summary AS ( " +
                    "    SELECT " +
                    "        c.id as contractId, " +
                    "        c.contract_number as contractNumber, " +
                    "        c.title as contractTitle, " +
                    "        w.year as year, " +
                    "        w.month as month, " +
                    "        CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "        CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "        AVG(CAST(SUM(w.hours_worked) AS float)) OVER (PARTITION BY c.id) as avgHours " +
                    "    FROM work_time_entry w " +
                    "    JOIN contract c ON w.contract_id = c.id " +
                    "    WHERE (?1 IS NULL OR c.id = ?1) " +
                    "    GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    ") " +
                    "SELECT " +
                    "    contractId, contractNumber, contractTitle, year, month, yearMonth, totalHours, avgHours, " +
                    "    (totalHours - avgHours) as difference " +
                    "FROM contract_summary " +
                    "ORDER BY contractId, year DESC, month DESC",
            nativeQuery = true)
    List<Object[]> findAllContractsDifferenceFromAverageNative(Long contractId);

    default List<ContractComparisonDTO> findAllContractsDifferenceFromAverage(Long contractId) {
        List<Object[]> results = findAllContractsDifferenceFromAverageNative(contractId);
        return results.stream().map(row -> new ContractComparisonDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0.0,
                row[8] != null ? ((Number) row[8]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 15. لیست ساعت افرادی که در یک قرارداد خاص کار کرده‌اند - Native Query
    @Query(value =
            "SELECT " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as title, " +
                    "    w.employee_id as employeeId, " +
                    "    w.employee_name as employeeName, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE c.id = ?1 " +
                    "GROUP BY c.id, c.contract_number, c.title, w.employee_id, w.employee_name, w.year, w.month " +
                    "ORDER BY w.employee_name, w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findContractEmployeesMonthlyNative(Long contractId);

    default List<ContractEmployeesDTO> findContractEmployeesMonthly(Long contractId) {
        List<Object[]> results = findContractEmployeesMonthlyNative(contractId);
        return results.stream().map(row -> new ContractEmployeesDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).longValue(),
                (String) row[4],
                ((Number) row[5]).intValue(),
                ((Number) row[6]).intValue(),
                (String) row[7],
                row[8] != null ? ((Number) row[8]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // 16. لیست حضور همه قراردادها بر اساس فیلترها - Native Query
    @Query(value =
            "SELECT " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as title, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(w.hours_worked) AS float) as totalHours, " +
                    "    COUNT(DISTINCT w.employee_id) as employeeCount, " +
                    "    COUNT(*) as entryCount " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.employer_id = ?1 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "  AND (CASE WHEN ?2 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.contractor_id = ?2 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "  AND (CASE WHEN ?3 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.user_id = ?3 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "  AND (CASE WHEN ?4 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.id = ?4 THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    "ORDER BY c.contract_number, w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findAllContractsPresenceFilteredNative(
            Long employerId,
            Long contractorId,
            Long userId,
            Long contractId);

    default List<ContractMonthlyPresenceDTO> findAllContractsPresenceFiltered(
            Long employerId,
            Long contractorId,
            Long userId,
            Long contractId) {
        List<Object[]> results = findAllContractsPresenceFilteredNative(
                employerId, contractorId, userId, contractId);
        return results.stream().map(row -> new ContractMonthlyPresenceDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0,
                row[7] != null ? ((Number) row[7]).doubleValue() : 0,
                row[8] != null ? ((Number) row[8]).doubleValue() : 0
        )).collect(Collectors.toList());
    }

    // 17. ساعت اضافه کار همه قراردادها بر اساس فیلترها - Native Query با CASE WHEN و پارامترهای positional
    @Query(value =
            "SELECT " +
                    "    c.id as contractId, " +
                    "    c.contract_number as contractNumber, " +
                    "    c.title as title, " +
                    "    w.year as year, " +
                    "    w.month as month, " +
                    "    CONCAT(CAST(w.year AS varchar), '-', LPAD(CAST(w.month AS varchar), 2, '0')) as yearMonth, " +
                    "    CAST(SUM(CASE WHEN ((w.hours_worked + w.time_start) > 16) THEN ((w.hours_worked + w.time_start) - 16)  ELSE 0 END) AS float) as overtimeHours " +
                    "FROM work_time_entry w " +
                    "JOIN contract c ON w.contract_id = c.id " +
                    "WHERE " +
                    "  (CASE WHEN ?1 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.employer_id = CAST(?1 AS bigint) THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "  AND (CASE WHEN ?2 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.contractor_id = CAST(?2 AS bigint) THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "  AND (CASE WHEN ?3 IS NULL THEN 1 ELSE " +
                    "    CASE WHEN c.user_id = CAST(?3 AS bigint) THEN 1 ELSE 0 END " +
                    "  END) = 1 " +
                    "GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    "ORDER BY c.contract_number, w.year DESC, w.month DESC",
            nativeQuery = true)
    List<Object[]> findAllContractsOvertimeFilteredNative(
            Long employerId,
            Long contractorId,
            Long userId);

    default List<ContractMonthlyOvertimeDTO> findAllContractsOvertimeFiltered(
            Long employerId,
            Long contractorId,
            Long userId) {
        List<Object[]> results = findAllContractsOvertimeFilteredNative(
                employerId, contractorId, userId);
        return results.stream().map(row -> new ContractMonthlyOvertimeDTO(
                ((Number) row[0]).longValue(),
                (String) row[1],
                (String) row[2],
                ((Number) row[3]).intValue(),
                ((Number) row[4]).intValue(),
                (String) row[5],
                row[6] != null ? ((Number) row[6]).doubleValue() : 0.0
        )).collect(Collectors.toList());
    }

    // کوئری‌های کمکی (بدون تغییر)
    @Query("SELECT w FROM WorkTimeEntry w WHERE w.employeeId = :employeeId AND w.contract.id = :contractId")
    List<WorkTimeEntry> findByEmployeeIdAndContractId(@Param("employeeId") Long employeeId,
                                                      @Param("contractId") Long contractId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.employeeId = :employeeId")
    List<WorkTimeEntry> findByEmployeeId(@Param("employeeId") Long employeeId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.contract.id = :contractId")
    List<WorkTimeEntry> findByContractId(@Param("contractId") Long contractId);

    @Query("SELECT w FROM WorkTimeEntry w WHERE w.year = :year AND w.month = :month")
    List<WorkTimeEntry> findByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query("SELECT DISTINCT w.year FROM WorkTimeEntry w ORDER BY w.year")
    List<Integer> findDistinctYears();

    @Query("SELECT DISTINCT w.month FROM WorkTimeEntry w ORDER BY w.month")
    List<Integer> findDistinctMonths();

    List<WorkTimeEntry> findAllByContractId(Long contractId);
}