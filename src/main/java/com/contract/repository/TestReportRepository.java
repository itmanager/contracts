package com.contract.repository;

import com.contract.domain.TestReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TestReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestReportRepository extends JpaRepository<TestReport, Long> {}
