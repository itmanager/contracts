package com.contract.service.dto;

import com.contract.domain.enumeration.QualityDimension;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain} entity.
 */
//@Schema(description = "سنجش کیفیت\nارزیابی‌های دوره‌ای کیفیت پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityAssessmentDTO implements Serializable {

    private Long id;


    private Long assessmentDate;
    private  String assetType;
    private  String version;
    private  String characteristic;
    private  String subCharacteristic;
    private  String priority;
    private  String standardsCompliance;


    // list of execute way
    private  String  assessedBy;
    private  String toolsUsed;
    private  String strengths;
    private  String weaknesses;
    private  String  risks;
    private  String  testMethods;
    private  String certifications;


    /* list of metrics*/
    private  String  metric_name;
    private  String  metric_unit;
    private  String metric_minAcceptable;
    private  String metric_maxAcceptable;
    private  String  metric_score;
    private  String  metric_weight;
    private  String metric_result;

    /*end result of quality*/
    private  Integer minAcceptable;
    private  Integer maxAcceptable;
    private  Integer score;
    private  String comments;
    private  String recommendations;
    private  String status;
    private  String  trend;
    private ContractDTO contract;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(Long assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getSubCharacteristic() {
        return subCharacteristic;
    }

    public void setSubCharacteristic(String subCharacteristic) {
        this.subCharacteristic = subCharacteristic;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStandardsCompliance() {
        return standardsCompliance;
    }

    public void setStandardsCompliance(String standardsCompliance) {
        this.standardsCompliance = standardsCompliance;
    }

    public String getAssessedBy() {
        return assessedBy;
    }

    public void setAssessedBy(String assessedBy) {
        this.assessedBy = assessedBy;
    }

    public String getToolsUsed() {
        return toolsUsed;
    }

    public void setToolsUsed(String toolsUsed) {
        this.toolsUsed = toolsUsed;
    }

    public String getStrengths() {
        return strengths;
    }

    public void setStrengths(String strengths) {
        this.strengths = strengths;
    }

    public String getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(String weaknesses) {
        this.weaknesses = weaknesses;
    }

    public String getRisks() {
        return risks;
    }

    public void setRisks(String risks) {
        this.risks = risks;
    }

    public String getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(String testMethods) {
        this.testMethods = testMethods;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getMetric_name() {
        return metric_name;
    }

    public void setMetric_name(String metric_name) {
        this.metric_name = metric_name;
    }

    public String getMetric_unit() {
        return metric_unit;
    }

    public void setMetric_unit(String metric_unit) {
        this.metric_unit = metric_unit;
    }

    public String getMetric_minAcceptable() {
        return metric_minAcceptable;
    }

    public void setMetric_minAcceptable(String metric_minAcceptable) {
        this.metric_minAcceptable = metric_minAcceptable;
    }

    public String getMetric_maxAcceptable() {
        return metric_maxAcceptable;
    }

    public void setMetric_maxAcceptable(String metric_maxAcceptable) {
        this.metric_maxAcceptable = metric_maxAcceptable;
    }

    public String getMetric_score() {
        return metric_score;
    }

    public void setMetric_score(String metric_score) {
        this.metric_score = metric_score;
    }

    public String getMetric_weight() {
        return metric_weight;
    }

    public void setMetric_weight(String metric_weight) {
        this.metric_weight = metric_weight;
    }

    public String getMetric_result() {
        return metric_result;
    }

    public void setMetric_result(String metric_result) {
        this.metric_result = metric_result;
    }

    public Integer getMinAcceptable() {
        return minAcceptable;
    }

    public void setMinAcceptable(Integer minAcceptable) {
        this.minAcceptable = minAcceptable;
    }

    public Integer getMaxAcceptable() {
        return maxAcceptable;
    }

    public void setMaxAcceptable(Integer maxAcceptable) {
        this.maxAcceptable = maxAcceptable;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public String getTrend() {
        return trend;
    }

    public void setTrend(String trend) {
        this.trend = trend;
    }

    public ContractDTO getContract() {
        return contract;
    }

    public void setContract(ContractDTO contract) {
        this.contract = contract;
    }
}
