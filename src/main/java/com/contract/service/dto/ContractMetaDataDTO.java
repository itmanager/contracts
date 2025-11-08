package com.contract.service.dto;

public class ContractMetaDataDTO {

    private Long id;
    private ContractDTO contract;
    private ContractTypeDTO contractType;
    private String outSource;
    private ContractDTO contractParent;
    private Long fiveYearPlanId;
    private String fiveYearPlanName;
    private Long annualPlanId;
    private String annualPlanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public ContractTypeDTO getContractType() {
        return contractType;
    }

    public void setContractType(ContractTypeDTO contractType) {
        this.contractType = contractType;
    }

    public String getOutSource() {
        return outSource;
    }

    public void setOutSource(String outSource) {
        this.outSource = outSource;
    }

    public ContractDTO getContractParent() {
        return contractParent;
    }

    public void setContractParent(ContractDTO contractParent) {
        this.contractParent = contractParent;
    }

    public Long getFiveYearPlanId() {
        return fiveYearPlanId;
    }

    public void setFiveYearPlanId(Long fiveYearPlanId) {
        this.fiveYearPlanId = fiveYearPlanId;
    }

    public String getFiveYearPlanName() {
        return fiveYearPlanName;
    }

    public void setFiveYearPlanName(String fiveYearPlanName) {
        this.fiveYearPlanName = fiveYearPlanName;
    }

    public Long getAnnualPlanId() {
        return annualPlanId;
    }

    public void setAnnualPlanId(Long annualPlanId) {
        this.annualPlanId = annualPlanId;
    }

    public String getAnnualPlanName() {
        return annualPlanName;
    }

    public void setAnnualPlanName(String annualPlanName) {
        this.annualPlanName = annualPlanName;
    }
}