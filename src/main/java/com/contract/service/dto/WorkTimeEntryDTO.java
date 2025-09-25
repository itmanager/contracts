package com.contract.service.dto;

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

    private String description;

    private Boolean approved;

    private ZonedDateTime approvalDate;

    private EmployeeDTO employee;

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

    public ZonedDateTime getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(ZonedDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
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
            ", employee=" + getEmployee() +
            ", ganttActivity=" + getGanttActivity() +
            ", approvedBy=" + getApprovedBy() +
            "}";
    }
}
