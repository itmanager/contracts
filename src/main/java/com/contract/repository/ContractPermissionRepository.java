package com.contract.repository;

import com.contract.domain.ContractPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractPermissionRepository extends JpaRepository<ContractPermission, Long> {
    List<ContractPermission> findByContractId(Long contractId);
}
    