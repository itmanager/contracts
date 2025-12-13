package com.contract.web.rest;

import com.contract.service.WorkTimeAnalysisService;
import com.contract.service.dto.analysisWorkDtos.*;
import com.contract.domain.WorkTimeEntry;
import com.contract.web.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing work time analysis operations.
 */
@RestController
@RequestMapping("/api/work-time-analysis")
public class WorkTimeAnalysisResource {

    private static final Logger LOG = LoggerFactory.getLogger(WorkTimeAnalysisResource.class);

    private static final String ENTITY_NAME = "workTimeAnalysis";

    @Value("${spring.application.name}")
    private String applicationName;

    private final WorkTimeAnalysisService workTimeAnalysisService;

    public WorkTimeAnalysisResource(WorkTimeAnalysisService workTimeAnalysisService) {
        this.workTimeAnalysisService = workTimeAnalysisService;
    }

    /**
     * ساعت حضور کارمند خاص به تفکیک ماه
     */
    @GetMapping("/employee/{employeeId}/monthly-presence")
    public ResponseEntity<List<EmployeeMonthlyPresenceDTO>> getMonthlyPresenceByEmployee(
            @PathVariable Long employeeId) {
        LOG.debug("REST request to get monthly presence for employee: {}", employeeId);
        List<EmployeeMonthlyPresenceDTO> result = workTimeAnalysisService.getMonthlyPresenceByEmployee(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت اضافه کار یک کارمند خاص به تفکیک ماه
     */
    @GetMapping("/employee/{employeeId}/monthly-overtime")
    public ResponseEntity<List<EmployeeMonthlyOvertimeDTO>> getMonthlyOvertimeByEmployee(
            @PathVariable Long employeeId) {
        LOG.debug("REST request to get monthly overtime for employee: {}", employeeId);
        List<EmployeeMonthlyOvertimeDTO> result = workTimeAnalysisService.getMonthlyOvertimeByEmployee(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * مقایسه ساعت حضور یک کارمند خاص با میانگین حضورش در ماه
     */
    @GetMapping("/employee/{employeeId}/compare-with-average")
    public ResponseEntity<List<EmployeeComparisonDTO>> compareEmployeeWithOwnAverage(
            @PathVariable Long employeeId) {
        LOG.debug("REST request to compare employee with own average: {}", employeeId);
        List<EmployeeComparisonDTO> result = workTimeAnalysisService.compareEmployeeWithOwnAverage(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست اختلاف ساعت حضور با میانگین (برای همه کارمندان)
     */
    @GetMapping("/employee/all-difference-from-average")
    public ResponseEntity<List<EmployeeComparisonDTO>> getAllEmployeesDifferenceFromAverage(
            @RequestParam(required = false) Long employeeId) {
        LOG.debug("REST request to get all employees difference from average, employeeId: {}", employeeId);
        List<EmployeeComparisonDTO> result = workTimeAnalysisService.getAllEmployeesDifferenceFromAverage(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست اختلاف حضور یک فرد با 160 ساعت در ماه
     */
    @GetMapping("/employee/{employeeId}/difference-from-target")
    public ResponseEntity<List<EmployeeDifferenceDTO>> getDifferenceFromTargetHours(
            @PathVariable Long employeeId,
            @RequestParam(defaultValue = "160") Integer targetHours) {
        LOG.debug("REST request to get difference from target hours for employee: {}, targetHours: {}",
                employeeId, targetHours);
        List<EmployeeDifferenceDTO> result = workTimeAnalysisService.getDifferenceFromTargetHours(employeeId, targetHours);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست ساعت قراردادهایی که یک فرد در آنها کار کرده است برای هر فرد خاص به تفکیک ماه
     */
    @GetMapping("/employee/{employeeId}/contracts-monthly")
    public ResponseEntity<List<EmployeeContractHoursDTO>> getEmployeeContractsMonthly(
            @PathVariable Long employeeId) {
        LOG.debug("REST request to get employee contracts monthly: {}", employeeId);
        List<EmployeeContractHoursDTO> result = workTimeAnalysisService.getEmployeeContractsMonthly(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست حضور همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
     */
    @GetMapping("/employee/presence-filtered")
    public ResponseEntity<List<FilteredPresenceDTO>> getAllEmployeesPresenceFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        LOG.debug("REST request to get all employees presence filtered: employerId={}, contractorId={}, userId={}, contractId={}",
                employerId, contractorId, userId, contractId);
        List<FilteredPresenceDTO> result = workTimeAnalysisService.getAllEmployeesPresenceFiltered(
                employerId, contractorId, userId, contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت اضافه کار همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
     */
    @GetMapping("/employee/overtime-filtered")
    public ResponseEntity<List<EmployeeMonthlyOvertimeDTO>> getAllEmployeesOvertimeFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        LOG.debug("REST request to get all employees overtime filtered: employerId={}, contractorId={}, userId={}, contractId={}",
                employerId, contractorId, userId, contractId);
        List<EmployeeMonthlyOvertimeDTO> result = workTimeAnalysisService.getAllEmployeesOvertimeFiltered(
                employerId, contractorId, userId, contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت تاخیر برای هر کارمند به تفکیک ماه
     */
    @GetMapping("/employee/{employeeId}/monthly-delay")
    public ResponseEntity<List<MonthlyDelayDTO>> getMonthlyDelayByEmployee(
            @PathVariable Long employeeId) {
        LOG.debug("REST request to get monthly delay for employee: {}", employeeId);
        List<MonthlyDelayDTO> result = workTimeAnalysisService.getMonthlyDelayByEmployee(employeeId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت تاخیر همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
     */
    @GetMapping("/employee/delay-filtered")
    public ResponseEntity<List<MonthlyDelayDTO>> getAllEmployeesDelayFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        LOG.debug("REST request to get all employees delay filtered: employerId={}, contractorId={}, userId={}, contractId={}",
                employerId, contractorId, userId, contractId);
        List<MonthlyDelayDTO> result = workTimeAnalysisService.getAllEmployeesDelayFiltered(
                employerId, contractorId, userId, contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت حضور قرارداد خاص به تفکیک ماه
     */
    @GetMapping("/contract/{contractId}/monthly-presence")
    public ResponseEntity<List<ContractMonthlyPresenceDTO>> getMonthlyPresenceByContract(
            @PathVariable Long contractId) {
        LOG.debug("REST request to get monthly presence for contract: {}", contractId);
        List<ContractMonthlyPresenceDTO> result = workTimeAnalysisService.getMonthlyPresenceByContract(contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت اضافه کار یک قرارداد خاص به تفکیک ماه
     */
    @GetMapping("/contract/{contractId}/monthly-overtime")
    public ResponseEntity<List<ContractMonthlyOvertimeDTO>> getMonthlyOvertimeByContract(
            @PathVariable Long contractId) {
        LOG.debug("REST request to get monthly overtime for contract: {}", contractId);
        List<ContractMonthlyOvertimeDTO> result = workTimeAnalysisService.getMonthlyOvertimeByContract(contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * مقایسه ساعت حضور یک قرارداد خاص با میانگین حضورش در ماه
     */
    @GetMapping("/contract/{contractId}/compare-with-average")
    public ResponseEntity<List<ContractComparisonDTO>> compareContractWithOwnAverage(
            @PathVariable Long contractId) {
        LOG.debug("REST request to compare contract with own average: {}", contractId);
        List<ContractComparisonDTO> result = workTimeAnalysisService.compareContractWithOwnAverage(contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست اختلاف ساعت حضور قرارداد خاص با میانگین (برای همه قراردادها)
     */
    @GetMapping("/contract/all-difference-from-average")
    public ResponseEntity<List<ContractComparisonDTO>> getAllContractsDifferenceFromAverage(
            @RequestParam(required = false) Long contractId) {
        LOG.debug("REST request to get all contracts difference from average, contractId: {}", contractId);
        List<ContractComparisonDTO> result = workTimeAnalysisService.getAllContractsDifferenceFromAverage(contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست ساعت افرادی که در یک قرارداد خاص کار کرده‌اند برای هر قرارداد خاص به تفکیک ماه
     */
    @GetMapping("/contract/{contractId}/employees-monthly")
    public ResponseEntity<List<ContractEmployeesDTO>> getContractEmployeesMonthly(
            @PathVariable Long contractId) {
        LOG.debug("REST request to get contract employees monthly: {}", contractId);
        List<ContractEmployeesDTO> result = workTimeAnalysisService.getContractEmployeesMonthly(contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * لیست حضور همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
     */
    @GetMapping("/contract/presence-filtered")
    public ResponseEntity<List<ContractMonthlyPresenceDTO>> getAllContractsPresenceFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long contractId) {
        LOG.debug("REST request to get all contracts presence filtered: employerId={}, contractorId={}, userId={}, contractId={}",
                employerId, contractorId, userId, contractId);
        List<ContractMonthlyPresenceDTO> result = workTimeAnalysisService.getAllContractsPresenceFiltered(
                employerId, contractorId, userId, contractId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * ساعت اضافه کار همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری
     */
    @GetMapping("/contract/overtime-filtered")
    public ResponseEntity<List<ContractMonthlyOvertimeDTO>> getAllContractsOvertimeFiltered(
            @RequestParam(required = false) Long employerId,
            @RequestParam(required = false) Long contractorId,
            @RequestParam(required = false) Long userId) {
        LOG.debug("REST request to get all contracts overtime filtered: employerId={}, contractorId={}, userId={}",
                employerId, contractorId, userId);
        List<ContractMonthlyOvertimeDTO> result = workTimeAnalysisService.getAllContractsOvertimeFiltered(
                employerId, contractorId, userId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * کوئری‌های کمکی
     */

    @GetMapping("/find-by-employee-and-contract")
    public ResponseEntity<List<WorkTimeEntry>> getByEmployeeIdAndContractId(
            @RequestParam Long employeeId,
            @RequestParam Long contractId) {
        LOG.debug("REST request to get work time entries by employeeId: {} and contractId: {}", employeeId, contractId);
        List<WorkTimeEntry> result = workTimeAnalysisService.getByEmployeeIdAndContractId(employeeId, contractId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/find-by-employee/{employeeId}")
    public ResponseEntity<List<WorkTimeEntry>> getByEmployeeId(@PathVariable Long employeeId) {
        LOG.debug("REST request to get work time entries by employeeId: {}", employeeId);
        List<WorkTimeEntry> result = workTimeAnalysisService.getByEmployeeId(employeeId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/find-by-contract/{contractId}")
    public ResponseEntity<List<WorkTimeEntry>> getByContractId(@PathVariable Long contractId) {
        LOG.debug("REST request to get work time entries by contractId: {}", contractId);
        List<WorkTimeEntry> result = workTimeAnalysisService.getByContractId(contractId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/find-by-year-month")
    public ResponseEntity<List<WorkTimeEntry>> getByYearAndMonth(
            @RequestParam Integer year,
            @RequestParam Integer month) {
        LOG.debug("REST request to get work time entries by year: {} and month: {}", year, month);
        List<WorkTimeEntry> result = workTimeAnalysisService.getByYearAndMonth(year, month);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/distinct-years")
    public ResponseEntity<List<Integer>> getDistinctYears() {
        LOG.debug("REST request to get distinct years");
        List<Integer> result = workTimeAnalysisService.getDistinctYears();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/distinct-months")
    public ResponseEntity<List<Integer>> getDistinctMonths() {
        LOG.debug("REST request to get distinct months");
        List<Integer> result = workTimeAnalysisService.getDistinctMonths();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/all-by-contract/{contractId}")
    public ResponseEntity<List<WorkTimeEntry>> getAllByContractId(@PathVariable Long contractId) {
        LOG.debug("REST request to get all work time entries by contractId: {}", contractId);
        List<WorkTimeEntry> result = workTimeAnalysisService.getAllByContractId(contractId);
        return ResponseEntity.ok().body(result);
    }
}