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
            return repository.getContractPerformanceReportCombined(queryModel);

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

