package com.contract.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.EquipmentUsage} entity.
 */
//@Schema(description = "استفاده از تجهیزات\nثبت استفاده از تجهیزات در فعالیت‌های پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EquipmentUsageDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime startDate;

    @NotNull
    private ZonedDateTime endDate;

    private Integer hoursUsed;

    private BigDecimal cost;

    private String notes;

    private EquipmentDTO equipment;

    private GanttActivityDTO ganttActivity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getHoursUsed() {
        return hoursUsed;
    }

    public void setHoursUsed(Integer hoursUsed) {
        this.hoursUsed = hoursUsed;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public EquipmentDTO getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentDTO equipment) {
        this.equipment = equipment;
    }

    public GanttActivityDTO getGanttActivity() {
        return ganttActivity;
    }

    public void setGanttActivity(GanttActivityDTO ganttActivity) {
        this.ganttActivity = ganttActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipmentUsageDTO)) {
            return false;
        }

        EquipmentUsageDTO equipmentUsageDTO = (EquipmentUsageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, equipmentUsageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentUsageDTO{" +
            "id=" + getId() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", hoursUsed=" + getHoursUsed() +
            ", cost=" + getCost() +
            ", notes='" + getNotes() + "'" +
            ", equipment=" + getEquipment() +
            ", ganttActivity=" + getGanttActivity() +
            "}";
    }
}
