package com.contract.repository;

import com.contract.domain.WorkTimeEntry;
import com.contract.service.dto.analysisWorkDtos.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkTimeEntryRepository extends JpaRepository<WorkTimeEntry, Long> {

    // 1. ساعت حضور کارمند خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeMonthlyPresenceDTO(" +
            "w.employeeId, w.employeeName, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double), " +
            "COUNT(DISTINCT w.contract.id), COUNT(w)) " +
            "FROM WorkTimeEntry w " +
            "WHERE w.employeeId = :employeeId " +
            "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC" , nativeQuery = true)
    List<EmployeeMonthlyPresenceDTO> findMonthlyPresenceByEmployee(@Param("employeeId") Long employeeId);

    // 2. ساعت اضافه کار یک کارمند خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeMonthlyOvertimeDTO(" +
            "w.employeeId, w.employeeName, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(CASE WHEN w.hoursWorked > 8 THEN w.hoursWorked - 8 ELSE 0 END) AS double)) " +
            "FROM WorkTimeEntry w " +
            "WHERE w.employeeId = :employeeId " +
            "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC", nativeQuery = true)
    List<EmployeeMonthlyOvertimeDTO> findMonthlyOvertimeByEmployee(@Param("employeeId") Long employeeId);

    // 3. مقایسه ساعت حضور یک کارمند خاص با میانگین حضورش در ماه
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeComparisonDTO(" +
                    "emp.employeeId, emp.employeeName, emp.year, emp.month, emp.yearMonth, " +
                    "emp.totalHours, emp.avgHours, (emp.totalHours - emp.avgHours)) " +
                    "FROM (" +
                    "   SELECT w.employee_id as employeeId, w.employee_name as employeeName, " +
                    "          w.year as year, w.month as month, " +
                    "          CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')) as yearMonth, " +
                    "          CAST(SUM(w.hours_worked) AS double) as totalHours, " +
                    "          CAST(AVG(SUM(w.hours_worked)) OVER (PARTITION BY w.employee_id) AS double) as avgHours " +
                    "   FROM work_time_entry w " +
                    "   WHERE w.employee_id = :employeeId " +
                    "   GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") emp " +
                    "ORDER BY emp.year DESC, emp.month DESC", nativeQuery = true)
    List<EmployeeComparisonDTO> compareEmployeeWithOwnAverage(@Param("employeeId") Long employeeId);

    // 4. لیست اختلاف ساعت حضور با میانگین (برای همه کارمندان)
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeComparisonDTO(" +
                    "emp.employeeId, emp.employeeName, emp.year, emp.month, emp.yearMonth, " +
                    "emp.totalHours, emp.avgHours, (emp.totalHours - emp.avgHours)) " +
                    "FROM(" +
                    " SELECT w.employee_id as employeeId, w.employee_name as employeeName, " +
                    "          w.year as year, w.month as month, " +
                    "          CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')) as yearMonth, " +
                    "          CAST(SUM(w.hours_worked) AS double) as totalHours, " +
                    "          CAST(AVG(SUM(w.hours_worked)) OVER (PARTITION BY w.employee_id) AS double) as avgHours " +
                    "   FROM work_time_entry w " +
                    "   WHERE w.employee_id = :employeeId " +
                    "   GROUP BY w.employee_id, w.employee_name, w.year, w.month " +
                    ") emp " +
                    "ORDER BY emp.employeeId, emp.year DESC, emp.month DESC", nativeQuery = true)
    List<EmployeeComparisonDTO> findAllEmployeesDifferenceFromAverage(@Param("employeeId") Long employeeId);

    // 5. لیست اختلاف حضور یک فرد با 160 ساعت در ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeDifferenceDTO(" +
            "w.employeeId, w.employeeName, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double), " +
            "CAST(:targetHours AS double), " +
            "CAST(SUM(w.hoursWorked) - :targetHours AS double)) " +
            "FROM WorkTimeEntry w " +
            "WHERE w.employeeId = :employeeId " +
            "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC",nativeQuery = true)
    List<EmployeeDifferenceDTO> findDifferenceFromTargetHours(@Param("employeeId") Long employeeId,
                                                              @Param("targetHours") Integer targetHours);

    // 6. لیست ساعت قراردادهایی که یک فرد در آنها کار کرده است برای هر فرد خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeContractHoursDTO(" +
            "w.employeeId, w.employeeName, c.id, c.contractNumber, c.title, " +
            "w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE w.employeeId = :employeeId " +
            "GROUP BY w.employeeId, w.employeeName, c.id, c.contractNumber, c.title, w.year, w.month " +
            "ORDER BY c.contractNumber, w.year DESC, w.month DESC", nativeQuery = true)
    List<EmployeeContractHoursDTO> findEmployeeContractsMonthly(@Param("employeeId") Long employeeId);

    // 7. لیست حضور همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.FilteredPresenceDTO(" +
            "w.employeeId, w.employeeName, c.id, c.contractNumber, " +
            "w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double), " +
            "c.employerName, c.contractorName, c.userName) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE (:employerId IS NULL OR c.employer.id = :employerId) " +
            "AND (:contractorId IS NULL OR c.contractor.id = :contractorId) " +
            "AND (:userId IS NULL OR c.user.id = :userId) " +
            "AND (:contractId IS NULL OR c.id = :contractId) " +
            "GROUP BY w.employeeId, w.employeeName, c.id, c.contractNumber, w.year, w.month, " +
            "c.employerName, c.contractorName, c.userName " +
            "ORDER BY w.year DESC, w.month DESC, w.employeeName", nativeQuery = true)
    List<FilteredPresenceDTO> findAllEmployeesPresenceFiltered(
            @Param("employerId") Long employerId,
            @Param("contractorId") Long contractorId,
            @Param("userId") Long userId,
            @Param("contractId") Long contractId);

    // 8. ساعت اضافه کار همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.EmployeeMonthlyOvertimeDTO(" +
            "w.employeeId, w.employeeName, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(CASE WHEN w.hoursWorked > 8 THEN w.hoursWorked - 8 ELSE 0 END) AS double)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE (:employerId IS NULL OR c.employer.id = :employerId) " +
            "AND (:contractorId IS NULL OR c.contractor.id = :contractorId) " +
            "AND (:userId IS NULL OR c.user.id = :userId) " +
            "AND (:contractId IS NULL OR c.id = :contractId) " +
            "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC, w.employeeName", nativeQuery = true)
    List<EmployeeMonthlyOvertimeDTO> findAllEmployeesOvertimeFiltered(
            @Param("employerId") Long employerId,
            @Param("contractorId") Long contractorId,
            @Param("userId") Long userId,
            @Param("contractId") Long contractId);

    // 9. ساعت تاخیر برای هر کارمند به تفکیک ماه
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.MonthlyDelayDTO(" +
                    "w.employeeId, w.employeeName, w.year, w.month, " +
                    "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
                    "CAST(SUM(CASE " +
                    "   WHEN w.timeStart > 800 THEN " +
                    "       ((w.timeStart / 100 - 8) + (w.timeStart % 100) / 60.0) " +
                    "   ELSE 0 " +
                    "END) AS double)) " +
                    "FROM WorkTimeEntry w " +
                    "WHERE w.employeeId = :employeeId " +
                    "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC", nativeQuery = true)
    List<MonthlyDelayDTO> findMonthlyDelayByEmployee(@Param("employeeId") Long employeeId);

    // 10. ساعت تاخیر همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.MonthlyDelayDTO(" +
                    "w.employeeId, w.employeeName, w.year, w.month, " +
                    "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
                    "CAST(SUM(CASE " +
                    "   WHEN w.timeStart > 800 THEN " +
                    "       ((w.timeStart / 100 - 8) + (w.timeStart % 100) / 60.0) " +
                    "   ELSE 0 " +
                    "END) AS double)) " +
                    "FROM WorkTimeEntry w " +
                    "JOIN w.contract c " +
                    "WHERE (:employerId IS NULL OR c.employer.id = :employerId) " +
                    "AND (:contractorId IS NULL OR c.contractor.id = :contractorId) " +
                    "AND (:userId IS NULL OR c.user.id = :userId) " +
                    "AND (:contractId IS NULL OR c.id = :contractId) " +
                    "GROUP BY w.employeeId, w.employeeName, w.year, w.month " +
                    "ORDER BY w.year DESC, w.month DESC, w.employeeName", nativeQuery = true)
    List<MonthlyDelayDTO> findAllEmployeesDelayFiltered(
            @Param("employerId") Long employerId,
            @Param("contractorId") Long contractorId,
            @Param("userId") Long userId,
            @Param("contractId") Long contractId);

    // 11. ساعت حضور قرارداد خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.ContractMonthlyPresenceDTO(" +
            "c.id, c.contractNumber, c.title, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double), " +
            "COUNT(DISTINCT w.employeeId), COUNT(w)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE c.id = :contractId " +
            "GROUP BY c.id, c.contractNumber, c.title, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC", nativeQuery = true)
    List<ContractMonthlyPresenceDTO> findMonthlyPresenceByContract(@Param("contractId") Long contractId);

    // 12. ساعت اضافه کار یک قرارداد خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.ContractMonthlyOvertimeDTO(" +
            "c.id, c.contractNumber, c.title, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(CASE WHEN w.hoursWorked > 8 THEN w.hoursWorked - 8 ELSE 0 END) AS double)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE c.id = :contractId " +
            "GROUP BY c.id, c.contractNumber, c.title, w.year, w.month " +
            "ORDER BY w.year DESC, w.month DESC", nativeQuery = true)
    List<ContractMonthlyOvertimeDTO> findMonthlyOvertimeByContract(@Param("contractId") Long contractId);

    // 13. مقایسه ساعت حضور یک قرارداد خاص با میانگین حضورش در ماه
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.ContractComparisonDTO(" +
                    "cont.contractId, cont.contractNumber, cont.contractTitle, cont.year, cont.month, " +
                    "cont.yearMonth, cont.totalHours, cont.avgHours, (cont.totalHours - cont.avgHours)) " +
                    "FROM (" +
                    "   SELECT c.id as contractId, c.contract_number as contractNumber, c.title as contractTitle, " +
                    "          w.year as year, w.month as month, " +
                    "          CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')) as yearMonth, " +
                    "          CAST(SUM(w.hours_worked) AS double) as totalHours, " +
                    "          CAST(AVG(SUM(w.hours_worked)) OVER (PARTITION BY c.id) AS double) as avgHours " +
                    "   FROM work_time_entry w " +
                    "   JOIN contract c ON w.contract_id = c.id " +
                    "   WHERE c.id = :contractId " +
                    "   GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    ") cont " +
                    "ORDER BY cont.year DESC, cont.month DESC", nativeQuery = true)
    List<ContractComparisonDTO> compareContractWithOwnAverage(@Param("contractId") Long contractId);

    // 14. لیست اختلاف ساعت حضور قرارداد خاص با میانگین (برای همه قراردادها)
    @Query(value =
            "SELECT new com.contract.service.dto.analysisWorkDtos.ContractComparisonDTO(" +
                    "cont.contractId, cont.contractNumber, cont.contractTitle, cont.year, cont.month, " +
                    "cont.yearMonth, cont.totalHours, cont.avgHours, (cont.totalHours - cont.avgHours)) " +
                    "FROM (" +
                    "   SELECT c.id as contractId, c.contract_number as contractNumber, c.title as contractTitle, " +
                    "          w.year as year, w.month as month, " +
                    "          CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')) as yearMonth, " +
                    "          CAST(SUM(w.hours_worked) AS double) as totalHours, " +
                    "          CAST(AVG(SUM(w.hours_worked)) OVER (PARTITION BY c.id) AS double) as avgHours " +
                    "   FROM work_time_entry w " +
                    "   JOIN contract c ON w.contract_id = c.id " +
                    "   WHERE c.id = :contractId " +
                    "   GROUP BY c.id, c.contract_number, c.title, w.year, w.month " +
                    ") cont " +
                    "ORDER BY cont.contractId, cont.year DESC, cont.month DESC", nativeQuery = true)
    List<ContractComparisonDTO> findAllContractsDifferenceFromAverage(@Param("contractId") Long contractId);

    // 15. لیست ساعت افرادی که در یک قرارداد خاص کار کرده‌اند برای هر قرارداد خاص به تفکیک ماه
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.ContractEmployeesDTO(" +
            "c.id, c.contractNumber, c.title, w.employeeId, w.employeeName, " +
            "w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE c.id = :contractId " +
            "GROUP BY c.id, c.contractNumber, c.title, w.employeeId, w.employeeName, w.year, w.month " +
            "ORDER BY w.employeeName, w.year DESC, w.month DESC", nativeQuery = true)
    List<ContractEmployeesDTO> findContractEmployeesMonthly(@Param("contractId") Long contractId);

    // 16. لیست حضور همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.ContractMonthlyPresenceDTO(" +
            "c.id, c.contractNumber, c.title, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(w.hoursWorked) AS double), " +
            "COUNT(DISTINCT w.employeeId), COUNT(w)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE (:employerId IS NULL OR c.employer.id = :employerId) " +
            "AND (:contractorId IS NULL OR c.contractor.id = :contractorId) " +
            "AND (:userId IS NULL OR c.user.id = :userId) " +
            "AND (:contractId IS NULL OR c.id = :contractId) " +
            "GROUP BY c.id, c.contractNumber, c.title, w.year, w.month " +
            "ORDER BY c.contractNumber, w.year DESC, w.month DESC", nativeQuery = true)
    List<ContractMonthlyPresenceDTO> findAllContractsPresenceFiltered(
            @Param("employerId") Long employerId,
            @Param("contractorId") Long contractorId,
            @Param("userId") Long userId,
            @Param("contractId") Long contractId);

    // 17. ساعت اضافه کار همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری
    @Query(value = "SELECT new com.contract.service.dto.analysisWorkDtos.ContractMonthlyOvertimeDTO(" +
            "c.id, c.contractNumber, c.title, w.year, w.month, " +
            "CONCAT(CAST(w.year AS string), '-', LPAD(CAST(w.month AS string), 2, '0')), " +
            "CAST(SUM(CASE WHEN w.hoursWorked > 8 THEN w.hoursWorked - 8 ELSE 0 END) AS double)) " +
            "FROM WorkTimeEntry w " +
            "JOIN w.contract c " +
            "WHERE (:employerId IS NULL OR c.employer.id = :employerId) " +
            "AND (:contractorId IS NULL OR c.contractor.id = :contractorId) " +
            "AND (:userId IS NULL OR c.user.id = :userId) " +
            "GROUP BY c.id, c.contractNumber, c.title, w.year, w.month " +
            "ORDER BY c.contractNumber, w.year DESC, w.month DESC", nativeQuery = true)
    List<ContractMonthlyOvertimeDTO> findAllContractsOvertimeFiltered(
            @Param("employerId") Long employerId,
            @Param("contractorId") Long contractorId,
            @Param("userId") Long userId);

    // کوئری‌های کمکی
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