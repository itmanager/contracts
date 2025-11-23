package com.contract.service;

import com.contract.repository.ContractPerformanceRepository;
import com.contract.service.dto.ContractPerformanceModelDTO;
import com.contract.service.dto.QueryContractModel;
import com.contract.service.mapper.ContractPerformanceModelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractPerformanceServiceImpl  {

    private final ContractPerformanceRepository repository;
    private final ContractPerformanceModelMapper performanceModelMapper;
    
    public List<ContractPerformanceModelDTO> getContractPerformanceReport(QueryContractModel queryModel) {
        try {
            return repository.getContractPerformanceReport(
                    emptyToNull(queryModel.getPContractIds()),
                    emptyToNull(queryModel.getPEmployerIds()),
                    emptyToNull(queryModel.getPContractorIds()),
                    emptyToNull(queryModel.getPUserIds()),
                    emptyToNull(queryModel.getPSupervisorIds()),
                    queryModel.getPFromDate(),
                    queryModel.getPToDate(),
                    emptyToNull(queryModel.getPFiveYearPlanIds()),
                    emptyToNull(queryModel.getPAnnualPlanIds()),
                    emptyToNull(queryModel.getPContractTypeIds()),
                    emptyToNull(queryModel.getPOutSourceTypes()),
                    queryModel.getPDateFrom(),
                    queryModel.getPDateTo(),
                    queryModel.getPDateNow(),
                    queryModel.getJYearFrom(),
                    queryModel.getJMonthFrom(),
                    queryModel.getJYearTo(),
                    queryModel.getJMonthTo(),
                    queryModel.getJYearNow(),
                    queryModel.getJMonthNow(),
                    emptyToNull(queryModel.getPContractStatuses()),
                    queryModel.getPMinContractPrice(),
                    queryModel.getPMaxContractPrice(),
                    queryModel.getPMinAllocatedBudget(),
                    queryModel.getPMaxAllocatedBudget(),
                    queryModel.getPMinCost(),
                    queryModel.getPMaxCost(),
                    queryModel.getPMinCpi(),
                    queryModel.getPMaxCpi(),
                    queryModel.getPMinSpi(),
                    queryModel.getPMaxSpi(),
                    queryModel.getPMinCv(),
                    queryModel.getPMaxCv(),
                    queryModel.getPMinSv(),
                    queryModel.getPMaxSv(),
                    queryModel.getPMinActualHours(),
                    queryModel.getPMaxActualHours(),
                    queryModel.getPMinEstimatedHours(),
                    queryModel.getPMaxEstimatedHours()
            ).stream().map(performanceModelMapper::toDto).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error fetching contract performance report", e);
            return Collections.emptyList();
        }
    }


    // متدهای کمکی
    private <T> List<T> emptyToNull(List<T> list) {
        return list == null || list.isEmpty() ? null : list;
    }

}

