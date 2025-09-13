package com.contract.web.rest;

import com.contract.repository.TestReportRepository;
import com.contract.service.TestReportService;
import com.contract.service.dto.TestReportDTO;
import com.contract.web.rest.errors.BadRequestAlertException;
import com.contract.web.utils.HeaderUtil;
import com.contract.web.utils.PaginationUtil;
import com.contract.web.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * REST controller for managing {@link com.contract.domain.TestReport}.
 */
@RestController
@RequestMapping("/api/test-reports")
public class TestReportResource {

    private static final Logger LOG = LoggerFactory.getLogger(TestReportResource.class);

    private static final String ENTITY_NAME = "testReport";

    @Value("${spring.application.name}")
    private String applicationName;

    private final TestReportService testReportService;

    private final TestReportRepository testReportRepository;

    public TestReportResource(TestReportService testReportService, TestReportRepository testReportRepository) {
        this.testReportService = testReportService;
        this.testReportRepository = testReportRepository;
    }

    /**
     * {@code POST  /test-reports} : Create a new testReport.
     *
     * @param testReportDTO the testReportDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new testReportDTO, or with status {@code 400 (Bad Request)} if the testReport has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TestReportDTO> createTestReport(@Valid @RequestBody TestReportDTO testReportDTO) throws URISyntaxException {
        LOG.debug("REST request to save TestReport : {}", testReportDTO);
        if (testReportDTO.getId() != null) {
            throw new BadRequestAlertException("A new testReport cannot already have an ID", ENTITY_NAME, "idexists");
        }
        testReportDTO = testReportService.save(testReportDTO);
        return ResponseEntity.created(new URI("/api/test-reports/" + testReportDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, testReportDTO.getId().toString()))
            .body(testReportDTO);
    }

    /**
     * {@code PUT  /test-reports/:id} : Updates an existing testReport.
     *
     * @param id the id of the testReportDTO to save.
     * @param testReportDTO the testReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testReportDTO,
     * or with status {@code 400 (Bad Request)} if the testReportDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the testReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TestReportDTO> updateTestReport(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TestReportDTO testReportDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update TestReport : {}, {}", id, testReportDTO);
        if (testReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, testReportDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!testReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        testReportDTO = testReportService.update(testReportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, testReportDTO.getId().toString()))
            .body(testReportDTO);
    }

    /**
     * {@code PATCH  /test-reports/:id} : Partial updates given fields of an existing testReport, field will ignore if it is null
     *
     * @param id the id of the testReportDTO to save.
     * @param testReportDTO the testReportDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated testReportDTO,
     * or with status {@code 400 (Bad Request)} if the testReportDTO is not valid,
     * or with status {@code 404 (Not Found)} if the testReportDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the testReportDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TestReportDTO> partialUpdateTestReport(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TestReportDTO testReportDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update TestReport partially : {}, {}", id, testReportDTO);
        if (testReportDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, testReportDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!testReportRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TestReportDTO> result = testReportService.partialUpdate(testReportDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, testReportDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /test-reports} : get all the testReports.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of testReports in body.
     */
    @GetMapping("")
    public ResponseEntity<List<TestReportDTO>> getAllTestReports(Pageable pageable) {
        LOG.debug("REST request to get a page of TestReports");
        Page<TestReportDTO> page = testReportService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /test-reports/:id} : get the "id" testReport.
     *
     * @param id the id of the testReportDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the testReportDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TestReportDTO> getTestReport(@PathVariable("id") Long id) {
        LOG.debug("REST request to get TestReport : {}", id);
        Optional<TestReportDTO> testReportDTO = testReportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(testReportDTO);
    }

    /**
     * {@code DELETE  /test-reports/:id} : delete the "id" testReport.
     *
     * @param id the id of the testReportDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestReport(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete TestReport : {}", id);
        testReportService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
