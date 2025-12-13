package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * ثبت ساعت کار
 * ثبت ساعت‌های کارکرد پرسنل بر روی فعالیت‌ها
 */
@Entity
@Table(name = "work_time_entry")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class WorkTimeEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "entry_date")
    private BigDecimal entryDate;


    @Column(name = "time_start", nullable = false)
    private Integer timeStart;

    @Column(name = "year", nullable = false)
    private Integer year;


    @Column(name = "month", nullable = false)
    private Integer month;

    @NotNull
    @Column(name = "hours_worked", nullable = false)
    private Integer hoursWorked;

    @Column(name = "description")
    private String description;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "approval_date")
    private BigDecimal approvalDate;

    @Column(name = "employeeId")
    private Long employeeId;

    @Column(name = "employeeName")
    private String employeeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"ganttActivity"}, allowSetters = true)
    private GanttActivity ganttActivity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private Contract contract;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contractPhase"}, allowSetters = true)
    private ContractPhase contractPhase;


    @ManyToOne(fetch = FetchType.LAZY)
    private Supervisor approvedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public WorkTimeEntry id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getEntryDate() {
        return this.entryDate;
    }

    public WorkTimeEntry entryDate(BigDecimal entryDate) {
        this.setEntryDate(entryDate);
        return this;
    }

    public void setEntryDate(BigDecimal entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getHoursWorked() {
        return this.hoursWorked;
    }

    public WorkTimeEntry hoursWorked(Integer hoursWorked) {
        this.setHoursWorked(hoursWorked);
        return this;
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
        return this.description;
    }

    public WorkTimeEntry description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    public Boolean getApproved() {
        return this.approved;
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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public ContractPhase getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public GanttActivity getGanttActivity() {
        return this.ganttActivity;
    }

    public void setGanttActivity(GanttActivity ganttActivity) {
        this.ganttActivity = ganttActivity;
    }

    public WorkTimeEntry ganttActivity(GanttActivity ganttActivity) {
        this.setGanttActivity(ganttActivity);
        return this;
    }

    public Supervisor getApprovedBy() {
        return this.approvedBy;
    }

    public void setApprovedBy(Supervisor supervisor) {
        this.approvedBy = supervisor;
    }

    public WorkTimeEntry approvedBy(Supervisor supervisor) {
        this.setApprovedBy(supervisor);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WorkTimeEntry)) {
            return false;
        }
        return getId() != null && getId().equals(((WorkTimeEntry) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "WorkTimeEntry{" +
                "id=" + getId() +
                ", entryDate='" + getEntryDate() + "'" +
                ", hoursWorked=" + getHoursWorked() +
                ", description='" + getDescription() + "'" +
                ", approved='" + getApproved() + "'" +
                ", approvalDate='" + getApprovalDate() + "'" +
                "}";
    }
}
