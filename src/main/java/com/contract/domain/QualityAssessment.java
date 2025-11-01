package com.contract.domain;

import com.contract.domain.enumeration.QualityDimension;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * سنجش کیفیت
 * ارزیابی‌های دوره‌ای کیفیت پروژه
 */
@Entity
@Table(name = "quality_assessment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityAssessment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "assessmentDate")
    private Long assessmentDate;
    @Column(name = "assetType")

    private  String assetType;
    @Column(name = "version")private  String version;
    @Column(name = "characteristic")private  String characteristic;
    @Column(name = "subCharacteristic")private  String subCharacteristic;
    @Column(name = "priority")private  String priority;
    @Column(name = "standardsCompliance")private  String standardsCompliance;


    // list of execute way
    @Column(name = "assessedBy")    private  String  assessedBy;
    @Column(name = "toolsUsed")private  String toolsUsed;
    @Column(name = "strengths")private  String strengths;
    @Column(name = "weaknesses")private  String weaknesses;
    @Column(name = "risks")private  String  risks;
    @Column(name = "testMethods")    private  String  testMethods;
    @Column(name = "certifications")    private  String certifications;


    /* list of metrics*/
    @Column(name = "metric_name")    private  String  metric_name;
    @Column(name = "metric_unit")    private  String  metric_unit;
    @Column(name = "metric_minAcceptable")   private  String metric_minAcceptable;
    @Column(name = "metric_maxAcceptable") private  String metric_maxAcceptable;
    @Column(name = "metric_score") private  String  metric_score;
    @Column(name = "metric_weight") private  String  metric_weight;
    @Column(name = "metric_result")  private  String metric_result;

    /*end result of quality*/
    @Column(name = "minAcceptable")  private  Integer minAcceptable;
    @Column(name = "maxAcceptable") private  Integer maxAcceptable;
    @Column(name = "score") private  Integer score;
    @Column(name = "comments") private  String comments;
    @Column(name = "recommendations") private  String recommendations;
    @Column(name = "status") private  String status;
    @Column(name = "trend") private  String  trend;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "contract" }, allowSetters = true)
    private Contract contract;


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

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
