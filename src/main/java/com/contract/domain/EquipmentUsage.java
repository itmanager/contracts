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

    @Column(name = "group_name")
    private String group;

    @Column(name = "class_field")
    private String classField;

    @Column(name = "function")
    private String function;

    @Column(name = "country")
    private String country;

    @Column(name = "model")
    private String model;

    @Column(name = "name")
    private String name;


    @Column(name = "count")
    private Integer count;

    @Column(name = "wholesalePrice")
    private BigDecimal wholesalePrice;

    @Column(name = "specifications")
    private String specifications;

    @Column(name = "notes")
    private String notes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "contract" }, allowSetters = true)
    private Contract contract;

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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
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
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
