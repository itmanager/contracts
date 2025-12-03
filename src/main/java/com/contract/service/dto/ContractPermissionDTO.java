// ContractPermissionDTO.java
package com.contract.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ContractPermissionDTO implements Serializable {
    private Long id;
    private ContractDTO contract;
    private String contractName;
    
    // اطلاعات هدف (یکی از این چهار باید پر شود)
    private Long userId;
    private String userName;
    
    private Long personId;
    private String personName;
    
    private Long userGroupId;
    private String userGroupName;
    
    private Long organizationalPositionId;
    private String organizationalPositionName;
    
    // سطوح دسترسی
    private boolean canRead;
    private boolean canEdit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public Long getOrganizationalPositionId() {
        return organizationalPositionId;
    }

    public void setOrganizationalPositionId(Long organizationalPositionId) {
        this.organizationalPositionId = organizationalPositionId;
    }

    public String getOrganizationalPositionName() {
        return organizationalPositionName;
    }

    public void setOrganizationalPositionName(String organizationalPositionName) {
        this.organizationalPositionName = organizationalPositionName;
    }

    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }
}
