package com.contract.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.Supervisor} entity.
 */
//@Schema(description = "ناظر پروژه\nمسئول نظارت بر اجرای قرارداد و کنترل کیفیت پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SupervisorDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    private String phone;

    private String company;

    private String specialization;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private BigDecimal hourlyRate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SupervisorDTO)) {
            return false;
        }

        SupervisorDTO supervisorDTO = (SupervisorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, supervisorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SupervisorDTO{" +
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
