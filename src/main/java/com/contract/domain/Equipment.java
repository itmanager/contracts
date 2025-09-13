package com.contract.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * تجهیزات
 * اطلاعات تجهیزات و ماشین‌آلات پروژه
 */
@Entity
@Table(name = "equipment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Equipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "equipment_id", nullable = false, unique = true)
    private String equipmentId;

    @Column(name = "description")
    private String description;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "purchase_cost", precision = 21, scale = 2)
    private BigDecimal purchaseCost;

    @Column(name = "current_value", precision = 21, scale = 2)
    private BigDecimal currentValue;

    @Column(name = "maintenance_schedule")
    private String maintenanceSchedule;

    @Column(name = "depreciation_rate")
    private Double depreciationRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Equipment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Equipment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEquipmentId() {
        return this.equipmentId;
    }

    public Equipment equipmentId(String equipmentId) {
        this.setEquipmentId(equipmentId);
        return this;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getDescription() {
        return this.description;
    }

    public Equipment description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPurchaseDate() {
        return this.purchaseDate;
    }

    public Equipment purchaseDate(LocalDate purchaseDate) {
        this.setPurchaseDate(purchaseDate);
        return this;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPurchaseCost() {
        return this.purchaseCost;
    }

    public Equipment purchaseCost(BigDecimal purchaseCost) {
        this.setPurchaseCost(purchaseCost);
        return this;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public BigDecimal getCurrentValue() {
        return this.currentValue;
    }

    public Equipment currentValue(BigDecimal currentValue) {
        this.setCurrentValue(currentValue);
        return this;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public String getMaintenanceSchedule() {
        return this.maintenanceSchedule;
    }

    public Equipment maintenanceSchedule(String maintenanceSchedule) {
        this.setMaintenanceSchedule(maintenanceSchedule);
        return this;
    }

    public void setMaintenanceSchedule(String maintenanceSchedule) {
        this.maintenanceSchedule = maintenanceSchedule;
    }

    public Double getDepreciationRate() {
        return this.depreciationRate;
    }

    public Equipment depreciationRate(Double depreciationRate) {
        this.setDepreciationRate(depreciationRate);
        return this;
    }

    public void setDepreciationRate(Double depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Equipment)) {
            return false;
        }
        return getId() != null && getId().equals(((Equipment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Equipment{" +
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
