package com.contract.web.rest;


import com.contract.service.ContractPerformanceServiceImpl;
import com.contract.service.dto.ContractPerformanceModelDTO;
import com.contract.service.dto.QueryContractModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract-performance")
public class ContractPerformanceResource {

    private static final Logger LOG = LoggerFactory.getLogger(BudgetAllocationResource.class);

    private static final String ENTITY_NAME = "contractPerformance";

    @Value("${spring.application.name}")
    private String applicationName;

    private final ContractPerformanceServiceImpl contractPerformanceService;

    public ContractPerformanceResource(ContractPerformanceServiceImpl contractPerformanceService) {
        this.contractPerformanceService = contractPerformanceService;
    }

    @PostMapping("/report")
    public ResponseEntity<List<ContractPerformanceModelDTO>> getContractPerformanceReport(
            @RequestBody QueryContractModel queryModel, Pageable pageable) {

        List<ContractPerformanceModelDTO> result = contractPerformanceService.getContractPerformanceReport(queryModel);
        return ResponseEntity.ok().body(result);
    }


}