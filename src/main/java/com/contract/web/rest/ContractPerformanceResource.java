package com.contract.web.rest;


import com.contract.service.ContractPerformanceServiceImpl;
import com.contract.service.dto.ContractPerformanceModelDTO;
import com.contract.service.dto.QueryContractModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contract-performance")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContractPerformanceResource {

    private final ContractPerformanceServiceImpl contractPerformanceService;

    @PostMapping("/report")
    public ResponseEntity<List<ContractPerformanceModelDTO>> getContractPerformanceReport(
            @RequestBody QueryContractModel queryModel) {

        List<ContractPerformanceModelDTO> result = contractPerformanceService.getContractPerformanceReport(queryModel);
        return ResponseEntity.ok(result);
    }


}