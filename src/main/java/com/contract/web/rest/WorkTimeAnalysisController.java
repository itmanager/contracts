package com.contract.web.rest;

import com.contract.service.WorkTimeAnalysisService;
import com.contract.service.dto.analysisWorkDtos.*;
import com.contract.domain.WorkTimeEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work-time-analysis")
public class WorkTimeAnalysisController {

    @Autowired
    private final WorkTimeAnalysisService workTimeAnalysisService;

    public WorkTimeAnalysisController(WorkTimeAnalysisService workTimeAnalysisService) {
        this.workTimeAnalysisService = workTimeAnalysisService;
    }

    // 1. ساعت حضور کارمند خاص به تفکیک ماه
    @GetMapping("/employee/{employeeId}/monthly-presence")
    public ResponseEntity<List<EmployeeMonthlyPresenceDTO>> getMonthlyPresenceByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getMonthlyPresenceByEmployee(employeeId));
    }

    // 2. ساعت اضافه کار یک کارمند خاص به تفکیک ماه
    @GetMapping("/employee/{employeeId}/monthly-overtime")
    public ResponseEntity<List<EmployeeMonthlyOvertimeDTO>> getMonthlyOvertimeByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getMonthlyOvertimeByEmployee(employeeId));
    }

    // 3. مقایسه ساعت حضور یک کارمند خاص با میانگین حضورش در ماه
    @GetMapping("/employee/{employeeId}/compare-with-average")
    public ResponseEntity<List<EmployeeComparisonDTO>> compareEmployeeWithOwnAverage(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.compareEmployeeWithOwnAverage(employeeId));
    }

    // 4. لیست اختلاف ساعت حضور با میانگین (برای همه کارمندان)
    @GetMapping("/employee/all-difference-from-average")
    public ResponseEntity<List<EmployeeComparisonDTO>> getAllEmployeesDifferenceFromAverage(
            @RequestParam(required = false) Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllEmployeesDifferenceFromAverage(employeeId));
    }

    // 5. لیست اختلاف حضور یک فرد با 160 ساعت در ماه
    @GetMapping("/employee/{employeeId}/difference-from-target")
    public ResponseEntity<List<EmployeeDifferenceDTO>> getDifferenceFromTargetHours(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "160") Integer targetHours) {
        return ResponseEntity.ok(workTimeAnalysisService.getDifferenceFromTargetHours(employeeId, targetHours));
    }

    // 6. لیست ساعت قراردادهایی که یک فرد در آنها کار کرده است برای هر فرد خاص به تفکیک ماه
    @GetMapping("/employee/{employeeId}/contracts-monthly")
    public ResponseEntity<List<EmployeeContractHoursDTO>> getEmployeeContractsMonthly(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getEmployeeContractsMonthly(employeeId));
    }

    // 7. لیست حضور همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @GetMapping("/employee/presence-filtered")
    public ResponseEntity<List<FilteredPresenceDTO>> getAllEmployeesPresenceFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllEmployeesPresenceFiltered(
                employerId, contractorId, userId, contractId));
    }

    // 8. ساعت اضافه کار همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @GetMapping("/employee/overtime-filtered")
    public ResponseEntity<List<EmployeeMonthlyOvertimeDTO>> getAllEmployeesOvertimeFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllEmployeesOvertimeFiltered(
                employerId, contractorId, userId, contractId));
    }

    // 9. ساعت تاخیر برای هر کارمند به تفکیک ماه
    @GetMapping("/employee/{employeeId}/monthly-delay")
    public ResponseEntity<List<MonthlyDelayDTO>> getMonthlyDelayByEmployee(
            @PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getMonthlyDelayByEmployee(employeeId));
    }

    // 10. ساعت تاخیر همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @GetMapping("/employee/delay-filtered")
    public ResponseEntity<List<MonthlyDelayDTO>> getAllEmployeesDelayFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllEmployeesDelayFiltered(
                employerId, contractorId, userId, contractId));
    }

    // 11. ساعت حضور قرارداد خاص به تفکیک ماه
    @GetMapping("/contract/{contractId}/monthly-presence")
    public ResponseEntity<List<ContractMonthlyPresenceDTO>> getMonthlyPresenceByContract(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getMonthlyPresenceByContract(contractId));
    }

    // 12. ساعت اضافه کار یک قرارداد خاص به تفکیک ماه
    @GetMapping("/contract/{contractId}/monthly-overtime")
    public ResponseEntity<List<ContractMonthlyOvertimeDTO>> getMonthlyOvertimeByContract(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getMonthlyOvertimeByContract(contractId));
    }

    // 13. مقایسه ساعت حضور یک قرارداد خاص با میانگین حضورش در ماه
    @GetMapping("/contract/{contractId}/compare-with-average")
    public ResponseEntity<List<ContractComparisonDTO>> compareContractWithOwnAverage(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.compareContractWithOwnAverage(contractId));
    }

    // 14. لیست اختلاف ساعت حضور قرارداد خاص با میانگین (برای همه قراردادها)
    @GetMapping("/contract/all-difference-from-average")
    public ResponseEntity<List<ContractComparisonDTO>> getAllContractsDifferenceFromAverage(
            @RequestParam(required = false) Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllContractsDifferenceFromAverage(contractId));
    }

    // 15. لیست ساعت افرادی که در یک قرارداد خاص کار کرده‌اند برای هر قرارداد خاص به تفکیک ماه
    @GetMapping("/contract/{contractId}/employees-monthly")
    public ResponseEntity<List<ContractEmployeesDTO>> getContractEmployeesMonthly(
            @PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getContractEmployeesMonthly(contractId));
    }

    // 16. لیست حضور همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    @GetMapping("/contract/presence-filtered")
    public ResponseEntity<List<ContractMonthlyPresenceDTO>> getAllContractsPresenceFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllContractsPresenceFiltered(
                employerId, contractorId, userId, contractId));
    }

    // 17. ساعت اضافه کار همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری
    @GetMapping("/contract/overtime-filtered")
    public ResponseEntity<List<ContractMonthlyOvertimeDTO>> getAllContractsOvertimeFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllContractsOvertimeFiltered(
                employerId, contractorId, userId));
    }

    // کوئری‌های کمکی
    @GetMapping("/find-by-employee-and-contract")
    public ResponseEntity<List<WorkTimeEntry>> getByEmployeeIdAndContractId(
            @RequestParam Long employeeId,
            @RequestParam Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getByEmployeeIdAndContractId(employeeId, contractId));
    }

    @GetMapping("/find-by-employee/{employeeId}")
    public ResponseEntity<List<WorkTimeEntry>> getByEmployeeId(@PathVariable Long employeeId) {
        return ResponseEntity.ok(workTimeAnalysisService.getByEmployeeId(employeeId));
    }

    @GetMapping("/find-by-contract/{contractId}")
    public ResponseEntity<List<WorkTimeEntry>> getByContractId(@PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getByContractId(contractId));
    }

    @GetMapping("/find-by-year-month")
    public ResponseEntity<List<WorkTimeEntry>> getByYearAndMonth(
            @RequestParam Integer year,
            @RequestParam Integer month) {
        return ResponseEntity.ok(workTimeAnalysisService.getByYearAndMonth(year, month));
    }

    @GetMapping("/distinct-years")
    public ResponseEntity<List<Integer>> getDistinctYears() {
        return ResponseEntity.ok(workTimeAnalysisService.getDistinctYears());
    }

    @GetMapping("/distinct-months")
    public ResponseEntity<List<Integer>> getDistinctMonths() {
        return ResponseEntity.ok(workTimeAnalysisService.getDistinctMonths());
    }

    @GetMapping("/all-by-contract/{contractId}")
    public ResponseEntity<List<WorkTimeEntry>> getAllByContractId(@PathVariable Long contractId) {
        return ResponseEntity.ok(workTimeAnalysisService.getAllByContractId(contractId));
    }
}