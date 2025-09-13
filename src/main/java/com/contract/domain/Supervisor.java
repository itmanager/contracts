package com.contract.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ناظر پروژه
 * مسئول نظارت بر اجرای قرارداد و کنترل کیفیت پروژه
 */
@Entity
@Table(name = "supervisor")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Supervisor implements Serializable {

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
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "company")
    private String company;

    @Column(name = "specialization")
    private String specialization;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Column(name = "hourly_rate", precision = 21, scale = 2)
    private BigDecimal hourlyRate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Supervisor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Supervisor name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public Supervisor email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Supervisor phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return this.company;
    }

    public Supervisor company(String company) {
        this.setCompany(company);
        return this;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpecialization() {
        return this.specialization;
    }

    public Supervisor specialization(String specialization) {
        this.setSpecialization(specialization);
        return this;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public Supervisor qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public BigDecimal getHourlyRate() {
        return this.hourlyRate;
    }

    public Supervisor hourlyRate(BigDecimal hourlyRate) {
        this.setHourlyRate(hourlyRate);
        return this;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Supervisor)) {
            return false;
        }
        return getId() != null && getId().equals(((Supervisor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Supervisor{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", company='" + getCompany() + "'" +
            ", specialization='" + getSpecialization() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", hourlyRate=" + getHourlyRate() +
            "}";
    }
}
