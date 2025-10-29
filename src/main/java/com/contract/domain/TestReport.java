package com.contract.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * گزارش‌های تست
 * گزارش‌های تست و آزمایش‌های انجام شده
 */
@Entity
@Table(name = "test_report")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TestReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "test_date", nullable = false)
    private BigDecimal testDate;

    @Column(name = "test_type")
    private String testType;

    @Column(name = "environment")
    private String environment;

    @Column(name = "participants")
    private String participants;

    @Column(name = "objectives")
    private String objectives;

    @Lob
    @Column(name = "results", nullable = false)
    private String results;

    @Lob
    @Column(name = "conclusions")
    private String conclusions;

    @Lob
    @Column(name = "recommendations")
    private String recommendations;

    @Column(name = "status")
    private String status;

    @Column(name = "file_path")
    private String filePath;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

    @Column(name = "description")
    private String description;
    @Column(name = "testSteps")
    private String testSteps;
    @Column(name = "expectedResult")
    private String expectedResult;
    @Column(name = "testData")
    private String testData;
    @Column(name = "preConditions")
    private String preConditions;
    @Column(name = "postConditions")
    private String postConditions;
    @Column(name = "relatedModule")
    private String relatedModule;
    @Column(name = "buildVersion")
    private String buildVersion;
    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"supervisor"}, allowSetters = true)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"contract"}, allowSetters = true)
    private ContractPhase contractPhase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TestReport id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public TestReport title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getTestDate() {
        return testDate;
    }

    public void setTestDate(BigDecimal testDate) {
        this.testDate = testDate;
    }

    public String getTestType() {
        return this.testType;
    }

    public TestReport testType(String testType) {
        this.setTestType(testType);
        return this;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public TestReport environment(String environment) {
        this.setEnvironment(environment);
        return this;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getParticipants() {
        return this.participants;
    }

    public TestReport participants(String participants) {
        this.setParticipants(participants);
        return this;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getObjectives() {
        return this.objectives;
    }

    public TestReport objectives(String objectives) {
        this.setObjectives(objectives);
        return this;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getResults() {
        return this.results;
    }

    public TestReport results(String results) {
        this.setResults(results);
        return this;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getConclusions() {
        return this.conclusions;
    }

    public TestReport conclusions(String conclusions) {
        this.setConclusions(conclusions);
        return this;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }

    public String getRecommendations() {
        return this.recommendations;
    }

    public TestReport recommendations(String recommendations) {
        this.setRecommendations(recommendations);
        return this;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getStatus() {
        return this.status;
    }

    public TestReport status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public TestReport filePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public TestReport qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(String testSteps) {
        this.testSteps = testSteps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    public String getPreConditions() {
        return preConditions;
    }

    public void setPreConditions(String preConditions) {
        this.preConditions = preConditions;
    }

    public String getPostConditions() {
        return postConditions;
    }

    public void setPostConditions(String postConditions) {
        this.postConditions = postConditions;
    }

    public String getRelatedModule() {
        return relatedModule;
    }

    public void setRelatedModule(String relatedModule) {
        this.relatedModule = relatedModule;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public TestReport contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public TestReport contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestReport)) {
            return false;
        }
        return getId() != null && getId().equals(((TestReport) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestReport{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", testDate='" + getTestDate() + "'" +
                ", testType='" + getTestType() + "'" +
                ", environment='" + getEnvironment() + "'" +
                ", participants='" + getParticipants() + "'" +
                ", objectives='" + getObjectives() + "'" +
                ", results='" + getResults() + "'" +
                ", conclusions='" + getConclusions() + "'" +
                ", recommendations='" + getRecommendations() + "'" +
                ", status='" + getStatus() + "'" +
                ", filePath='" + getFilePath() + "'" +
                ", qualityScore=" + getQualityScore() +
                "}";
    }
}
