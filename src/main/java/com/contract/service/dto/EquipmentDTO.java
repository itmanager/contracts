package com.contract.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.Equipment} entity.
 */
//@Schema(description = "تجهیزات\nاطلاعات تجهیزات و ماشین‌آلات پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EquipmentDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String equipmentId;

    private String description;

    private BigDecimal purchaseDate;

    private BigDecimal purchaseCost;

    private BigDecimal currentValue;

    private String maintenanceSchedule;

    private Double depreciationRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(BigDecimal purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public String getMaintenanceSchedule() {
        return maintenanceSchedule;
    }

    public void setMaintenanceSchedule(String maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public Double getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(Double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EquipmentDTO)) {
            return false;
        }

        EquipmentDTO equipmentDTO = (EquipmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, equipmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EquipmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", equipmentId='" + getEquipmentId() + "'" +
            ", description='" + getDescription() + "'" +
            ", purchaseDate='" + getPurchaseDate() + "'" +
            ", purchaseCost=" + getPurchaseCost() +
            ", currentValue=" + getCurrentValue() +
            ", maintenanceSchedule='" + getMaintenanceSchedule() + "'" +
            ", depreciationRate=" + getDepreciationRate() +
            "}";
    }
}
