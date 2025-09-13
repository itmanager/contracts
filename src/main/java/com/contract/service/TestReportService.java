package com.contract.service;

import com.contract.domain.TestReport;
import com.contract.repository.TestReportRepository;
import com.contract.service.dto.TestReportDTO;
import com.contract.service.mapper.TestReportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TestReport}.
 */
@Service
@Transactional
public class TestReportService {

    private static final Logger LOG = LoggerFactory.getLogger(TestReportService.class);

    private final TestReportRepository testReportRepository;

    private final TestReportMapper testReportMapper;

    public TestReportService(TestReportRepository testReportRepository, TestReportMapper testReportMapper) {
        this.testReportRepository = testReportRepository;
        this.testReportMapper = testReportMapper;
    }

    /**
     * Save a testReport.
     *
     * @param testReportDTO the entity to save.
     * @return the persisted entity.
     */
    public TestReportDTO save(TestReportDTO testReportDTO) {
        LOG.debug("Request to save TestReport : {}", testReportDTO);
        TestReport testReport = testReportMapper.toEntity(testReportDTO);
        testReport = testReportRepository.save(testReport);
        return testReportMapper.toDto(testReport);
    }

    /**
     * Update a testReport.
     *
     * @param testReportDTO the entity to save.
     * @return the persisted entity.
     */
    public TestReportDTO update(TestReportDTO testReportDTO) {
        LOG.debug("Request to update TestReport : {}", testReportDTO);
        TestReport testReport = testReportMapper.toEntity(testReportDTO);
        testReport = testReportRepository.save(testReport);
        return testReportMapper.toDto(testReport);
    }

    /**
     * Partially update a testReport.
     *
     * @param testReportDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<TestReportDTO> partialUpdate(TestReportDTO testReportDTO) {
        LOG.debug("Request to partially update TestReport : {}", testReportDTO);

        return testReportRepository
            .findById(testReportDTO.getId())
            .map(existingTestReport -> {
                testReportMapper.partialUpdate(existingTestReport, testReportDTO);

                return existingTestReport;
            })
            .map(testReportRepository::save)
            .map(testReportMapper::toDto);
    }

    /**
     * Get all the testReports.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TestReportDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all TestReports");
        return testReportRepository.findAll(pageable).map(testReportMapper::toDto);
    }

    /**
     * Get one testReport by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TestReportDTO> findOne(Long id) {
        LOG.debug("Request to get TestReport : {}", id);
        return testReportRepository.findById(id).map(testReportMapper::toDto);
    }

    /**
     * Delete the testReport by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete TestReport : {}", id);
        testReportRepository.deleteById(id);
    }
}
