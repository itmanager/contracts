package com.contract.domain;

import com.contract.domain.enumeration.ContractStatus;
import com.contract.domain.enumeration.QualityStatus;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * قرارداد
 * اصلی‌ترین موجودیت سیستم مدیریت قراردادها
 */
@Entity
@Table(name = "contract_type")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    public Long getId() {
        return this.id;
    }

    public ContractType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
