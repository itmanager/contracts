package com.contract.domain;

import com.contract.domain.Contract;
import com.contract.domain.ContractType;

import javax.persistence.*;

@Entity
@Table(name = "contract_metadata")
public class ContractMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @Column(name = "out_source")
    private String outSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_parent_id")
    private Contract contractParent;

    @Column(name = "five_year_plan_id")
    private Long fiveYearPlanId;

    @Column(name = "five_year_plan_name")
    private String fiveYearPlanName;

    @Column(name = "annual_plan_id")
    private Long annualPlanId;

    @Column(name = "annual_plan_name")
    private String annualPlanName;

    // Constructors
    public ContractMetaData() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public String getOutSource() {
        return outSource;
    }

    public void setOutSource(String outSource) {
        this.outSource = outSource;
    }

    public Contract getContractParent() {
        return contractParent;
    }

    public void setContractParent(Contract contractParent) {
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