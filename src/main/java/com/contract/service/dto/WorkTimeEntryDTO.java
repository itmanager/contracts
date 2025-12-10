package com.contract.service.dto;

import com.contract.domain.Contract;
import com.contract.domain.ContractPhase;
import com.contract.domain.GanttActivity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.WorkTimeEntry} entity.
 */
//@Schema(description = "ثبت ساعت کار\nثبت ساعت‌های کارکرد پرسنل بر روی فعالیت‌ها")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WorkTimeEntryDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal entryDate;

    @NotNull
    private Integer hoursWorked;

    private Integer year;

    private Integer month;

    private String description;

    private Boolean approved;

    private BigDecimal approvalDate;

    private Long employeeId;
    private String employeeName;

    private ContractDTO contract;


    private ContractPhaseDTO contractPhase;

    private GanttActivityDTO ganttActivity;

    private SupervisorDTO approvedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(BigDecimal entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Integer hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public BigDecimal getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(BigDecimal approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public ContractPhaseDTO getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhaseDTO contractPhase) {
        this.contractPhase = contractPhase;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public GanttActivityDTO getGanttActivity() {
        return ganttActivity;
    }

    public void setGanttActivity(GanttActivityDTO ganttActivity) {
        this.ganttActivity = ganttActivity;
    }

    public SupervisorDTO getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(SupervisorDTO approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkTimeEntryDTO)) {
            return false;
        }

        WorkTimeEntryDTO workTimeEntryDTO = (WorkTimeEntryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, workTimeEntryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkTimeEntryDTO{" +
            "id=" + getId() +
            ", entryDate='" + getEntryDate() + "'" +
            ", hoursWorked=" + getHoursWorked() +
            ", description='" + getDescription() + "'" +
            ", approved='" + getApproved() + "'" +
            ", approvalDate='" + getApprovalDate() + "'" +
            ", employee=" + getEmployeeId() +
            ", ganttActivity=" + getGanttActivity() +
            ", approvedBy=" + getApprovedBy() +
            "}";
    }
}
