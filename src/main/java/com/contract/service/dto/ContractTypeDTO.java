package com.contract.service.dto;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * قرارداد
 * اصلی‌ترین موجودیت سیستم مدیریت قراردادها
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    @NotNull
    private String title;

    public Long getId() {
        return this.id;
    }

    public ContractTypeDTO id(Long id) {
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
