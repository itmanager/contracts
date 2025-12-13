package com.contract.service;

import com.contract.repository.WorkTimeEntryRepository;
import com.contract.service.dto.*;
import com.contract.service.dto.analysisWorkDtos.*;
import com.contract.domain.WorkTimeEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WorkTimeAnalysisService {

    @Autowired
    private WorkTimeEntryRepository workTimeEntryRepository;

    // 1. ساعت حضور کارمند خاص به تفکیک ماه
    public List<EmployeeMonthlyPresenceDTO> getMonthlyPresenceByEmployee(Long employeeId) {
        return workTimeEntryRepository.findMonthlyPresenceByEmployee(employeeId);
    }

    // 2. ساعت اضافه کار یک کارمند خاص به تفکیک ماه
    public List<EmployeeMonthlyOvertimeDTO> getMonthlyOvertimeByEmployee(Long employeeId) {
        return workTimeEntryRepository.findMonthlyOvertimeByEmployee(employeeId);
    }

    // 3. مقایسه ساعت حضور یک کارمند خاص با میانگین حضورش در ماه
    public List<EmployeeComparisonDTO> compareEmployeeWithOwnAverage(Long employeeId) {
        return workTimeEntryRepository.compareEmployeeWithOwnAverage(employeeId);
    }

    // 4. لیست اختلاف ساعت حضور با میانگین (برای همه کارمندان)
    public List<EmployeeComparisonDTO> getAllEmployeesDifferenceFromAverage(Long employeeId) {
        return workTimeEntryRepository.findAllEmployeesDifferenceFromAverage(employeeId);
    }

    // 5. لیست اختلاف حضور یک فرد با 160 ساعت در ماه
    public List<EmployeeDifferenceDTO> getDifferenceFromTargetHours(Long employeeId, Integer targetHours) {
        return workTimeEntryRepository.findDifferenceFromTargetHours(employeeId, targetHours);
    }

    // 6. لیست ساعت قراردادهایی که یک فرد در آنها کار کرده است برای هر فرد خاص به تفکیک ماه
    public List<EmployeeContractHoursDTO> getEmployeeContractsMonthly(Long employeeId) {
        return workTimeEntryRepository.findEmployeeContractsMonthly(employeeId);
    }

    // 7. لیست حضور همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    public List<FilteredPresenceDTO> getAllEmployeesPresenceFiltered(
            Long employerId, Long contractorId, Long userId, Long contractId) {
        return workTimeEntryRepository.findAllEmployeesPresenceFiltered(
                employerId, contractorId, userId, contractId);
    }

    // 8. ساعت اضافه کار همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    public List<EmployeeMonthlyOvertimeDTO> getAllEmployeesOvertimeFiltered(
            Long employerId, Long contractorId, Long userId, Long contractId) {
        return workTimeEntryRepository.findAllEmployeesOvertimeFiltered(
                employerId, contractorId, userId, contractId);
    }

    // 9. ساعت تاخیر برای هر کارمند به تفکیک ماه
    public List<MonthlyDelayDTO> getMonthlyDelayByEmployee(Long employeeId) {
        return workTimeEntryRepository.findMonthlyDelayByEmployee(employeeId);
    }

    // 10. ساعت تاخیر همه کارمندان به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    public List<MonthlyDelayDTO> getAllEmployeesDelayFiltered(
            Long employerId, Long contractorId, Long userId, Long contractId) {
        return workTimeEntryRepository.findAllEmployeesDelayFiltered(
                employerId, contractorId, userId, contractId);
    }

    // 11. ساعت حضور قرارداد خاص به تفکیک ماه
    public List<ContractMonthlyPresenceDTO> getMonthlyPresenceByContract(Long contractId) {
        return workTimeEntryRepository.findMonthlyPresenceByContract(contractId);
    }

    // 12. ساعت اضافه کار یک قرارداد خاص به تفکیک ماه
    public List<ContractMonthlyOvertimeDTO> getMonthlyOvertimeByContract(Long contractId) {
        return workTimeEntryRepository.findMonthlyOvertimeByContract(contractId);
    }

    // 13. مقایسه ساعت حضور یک قرارداد خاص با میانگین حضورش در ماه
    public List<ContractComparisonDTO> compareContractWithOwnAverage(Long contractId) {
        return workTimeEntryRepository.compareContractWithOwnAverage(contractId);
    }

    // 14. لیست اختلاف ساعت حضور قرارداد خاص با میانگین (برای همه قراردادها)
    public List<ContractComparisonDTO> getAllContractsDifferenceFromAverage(Long contractId) {
        return workTimeEntryRepository.findAllContractsDifferenceFromAverage(contractId);
    }

    // 15. لیست ساعت افرادی که در یک قرارداد خاص کار کرده‌اند برای هر قرارداد خاص به تفکیک ماه
    public List<ContractEmployeesDTO> getContractEmployeesMonthly(Long contractId) {
        return workTimeEntryRepository.findContractEmployeesMonthly(contractId);
    }

    // 16. لیست حضور همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری – قرارداد
    public List<ContractMonthlyPresenceDTO> getAllContractsPresenceFiltered(
            Long employerId, Long contractorId, Long userId, Long contractId) {
        return workTimeEntryRepository.findAllContractsPresenceFiltered(
                employerId, contractorId, userId, contractId);
    }

    // 17. ساعت اضافه کار همه قراردادها به تفکیک ماه بر اساس انتخاب کارفرما – بهره بردار – مجری
    public List<ContractMonthlyOvertimeDTO> getAllContractsOvertimeFiltered(
            Long employerId, Long contractorId, Long userId) {
        return workTimeEntryRepository.findAllContractsOvertimeFiltered(
                employerId, contractorId, userId);
    }

    // کوئری‌های کمکی
    public List<WorkTimeEntry> getByEmployeeIdAndContractId(Long employeeId, Long contractId) {
        return workTimeEntryRepository.findByEmployeeIdAndContractId(employeeId, contractId);
    }

    public List<WorkTimeEntry> getByEmployeeId(Long employeeId) {
        return workTimeEntryRepository.findByEmployeeId(employeeId);
    }

    public List<WorkTimeEntry> getByContractId(Long contractId) {
        return workTimeEntryRepository.findByContractId(contractId);
    }

    public List<WorkTimeEntry> getByYearAndMonth(Integer year, Integer month) {
        return workTimeEntryRepository.findByYearAndMonth(year, month);
    }

    public List<Integer> getDistinctYears() {
        return workTimeEntryRepository.findDistinctYears();
    }

    public List<Integer> getDistinctMonths() {
        return workTimeEntryRepository.findDistinctMonths();
    }

    public List<WorkTimeEntry> getAllByContractId(Long contractId) {
        return workTimeEntryRepository.findAllByContractId(contractId);
    }
}