package com.contract.service.dto;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.TestReport} entity.
 */
//@Schema(description = "گزارش‌های تست\nگزارش‌های تست و آزمایش‌های انجام شده")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TestReportDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private ZonedDateTime testDate;

    private String testType;

    private String environment;

    private String participants;

    private String objectives;

    @Lob
    private String results;

    @Lob
    private String conclusions;

    @Lob
    private String recommendations;

    private String status;

    private String filePath;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Double qualityScore;

    private ContractDTO contract;

    private ContractPhaseDTO contractPhase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(ZonedDateTime testDate) {
        this.testDate = testDate;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getConclusions() {
        return conclusions;
    }

    public void setConclusions(String conclusions) {
        this.conclusions = conclusions;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Double getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }

    public ContractPhaseDTO getContractPhase() {
        return contractPhase;
    }

    public void setContractPhase(ContractPhaseDTO contractPhase) {
        this.contractPhase = contractPhase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TestReportDTO)) {
            return false;
        }

        TestReportDTO testReportDTO = (TestReportDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, testReportDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TestReportDTO{" +
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
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
