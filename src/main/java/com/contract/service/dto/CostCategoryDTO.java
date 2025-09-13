package com.contract.service.dto;

import com.contract.domain.enumeration.CostType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.CostCategory} entity.
 */
//@Schema(description = "دسته‌بندی هزینه\nطبقه‌بندی انواع هزینه‌های پروژه برای گزارشگیری و تحلیل")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CostCategoryDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private String code;

    @NotNull
    private CostType costType;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostCategoryDTO)) {
            return false;
        }

        CostCategoryDTO costCategoryDTO = (CostCategoryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, costCategoryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostCategoryDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", code='" + getCode() + "'" +
            ", costType='" + getCostType() + "'" +
            "}";
    }
}
