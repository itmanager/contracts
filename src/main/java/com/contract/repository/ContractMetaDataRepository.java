package com.contract.repository;

import com.contract.domain.ContractMetaData;
import com.google.common.io.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractMetaDataRepository extends JpaRepository<ContractMetaData, Long> {


    Optional<ContractMetaData> findByContractId(Long contractId);
}