package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * استفاده از تجهیزات
 * ثبت استفاده از تجهیزات در فعالیت‌های پروژه
 */
@Entity
@Table(name = "equipment_usage")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EquipmentUsage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private ZonedDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private ZonedDateTime endDate;

    @Column(name = "hours_used")
    private Integer hoursUsed;

    @Column(name = "cost", precision = 21, scale = 2)
    private BigDecimal cost;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "contractPhase" }, allowSetters = true)
    private GanttActivity ganttActivity;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EquipmentUsage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public EquipmentUsage startDate(ZonedDateTime startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public EquipmentUsage endDate(ZonedDateTime endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getHoursUsed() {
        return this.hoursUsed;
    }

    public EquipmentUsage hoursUsed(Integer hoursUsed) {
        this.setHoursUsed(hoursUsed);
        return this;
    }

    public void setHoursUsed(Integer hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public EquipmentUsage cost(BigDecimal cost) {
        this.setCost(cost);
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return this.notes;
    }

    public EquipmentUsage notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Equipment getEquipment() {
        return this.equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public EquipmentUsage equipment(Equipment equipment) {
        this.setEquipment(equipment);
        return this;
    }

    public GanttActivity getGanttActivity() {
        return this.ganttActivity;
    }

    public void setGanttActivity(GanttActivity ganttActivity) {
        this.ganttActivity = ganttActivity;
    }

    public EquipmentUsage ganttActivity(GanttActivity ganttActivity) {
        this.setGanttActivity(ganttActivity);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipmentUsage)) {
            return false;
        }
        return getId() != null && getId().equals(((EquipmentUsage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentUsage{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", hoursUsed=" + getHoursUsed() +
            ", cost=" + getCost() +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
