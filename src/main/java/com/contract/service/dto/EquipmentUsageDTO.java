package com.contract.service.dto;

import com.contract.domain.Contract;

import javax.persistence.Column;
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

    private String group;

    private String classField;

    private String function;

    private String country;

    private String model;

    private String name;

    private Integer count;

    private BigDecimal wholesalePrice;

    private String specifications;

    private String notes;


    private ContractDTO contract;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClassField() {
        return classField;
    }

    public void setClassField(String classField) {
        this.classField = classField;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(BigDecimal wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
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
            ", notes='" + getNotes() + "'" +
            ", contract=" + getContract() +
            "}";
    }
}
