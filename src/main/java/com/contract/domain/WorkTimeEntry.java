package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.time.LocalDate;
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
    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @NotNull
    @Column(name = "hours_worked", nullable = false)
    private Integer hoursWorked;

    @Column(name = "description")
    private String description;

    @Column(name = "approved")
    private Boolean approved;

    @Column(name = "approval_date")
    private ZonedDateTime approvalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "contractPhase" }, allowSetters = true)
    private GanttActivity ganttActivity;

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

    public LocalDate getEntryDate() {
        return this.entryDate;
    }

    public WorkTimeEntry entryDate(LocalDate entryDate) {
        this.setEntryDate(entryDate);
        return this;
    }

    public void setEntryDate(LocalDate entryDate) {
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

    public Boolean getApproved() {
        return this.approved;
    }

    public WorkTimeEntry approved(Boolean approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public ZonedDateTime getApprovalDate() {
        return this.approvalDate;
    }

    public WorkTimeEntry approvalDate(ZonedDateTime approvalDate) {
        this.setApprovalDate(approvalDate);
        return this;
    }

    public void setApprovalDate(ZonedDateTime approvalDate) {
        this.approvalDate = approvalDate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public WorkTimeEntry employee(Employee employee) {
        this.setEmployee(employee);
        return this;
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
