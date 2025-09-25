package com.contract.domain;

import com.contract.domain.enumeration.EmployeeType;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * نیروی انسانی
 * اطلاعات پرسنل و نیروی انسانی پروژه
 */
@Entity
@Table(name = "employee")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Employee implements Serializable {

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
    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "employee_type", nullable = false)
    private EmployeeType employeeType;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @NotNull
    @Column(name = "hourly_rate", precision = 21, scale = 2, nullable = false)
    private BigDecimal hourlyRate;

    @Column(name = "start_date")
    private BigDecimal startDate;

    @Column(name = "end_date")
    private BigDecimal endDate;

    @Column(name = "active")
    private Boolean active;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Employee id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Employee name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }

    public Employee employeeId(String employeeId) {
        this.setEmployeeId(employeeId);
        return this;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return this.email;
    }

    public Employee email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public Employee phone(String phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeType getEmployeeType() {
        return this.employeeType;
    }

    public Employee employeeType(EmployeeType employeeType) {
        this.setEmployeeType(employeeType);
        return this;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getDepartment() {
        return this.department;
    }

    public Employee department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return this.position;
    }

    public Employee position(String position) {
        this.setPosition(position);
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getHourlyRate() {
        return this.hourlyRate;
    }

    public Employee hourlyRate(BigDecimal hourlyRate) {
        this.setHourlyRate(hourlyRate);
        return this;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getStartDate() {
        return this.startDate;
    }

    public Employee startDate(BigDecimal startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(BigDecimal startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getEndDate() {
        return this.endDate;
    }

    public Employee endDate(BigDecimal endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(BigDecimal endDate) {
        this.endDate = endDate;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Employee active(Boolean active) {
        this.setActive(active);
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employee)) {
            return false;
        }
        return getId() != null && getId().equals(((Employee) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", employeeId='" + getEmployeeId() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", employeeType='" + getEmployeeType() + "'" +
            ", department='" + getDepartment() + "'" +
            ", position='" + getPosition() + "'" +
            ", hourlyRate=" + getHourlyRate() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", active='" + getActive() + "'" +
            "}";
    }
}
