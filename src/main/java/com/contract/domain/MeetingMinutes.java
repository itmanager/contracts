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
 * صورتجلسات
 * مدیریت صورتجلسات جلسات پروژه
 */
@Entity
@Table(name = "meeting_minutes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MeetingMinutes implements Serializable {

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
    @Column(name = "meeting_date", nullable = false)
    private BigDecimal meetingDate;

    @Column(name = "location")
    private String location;

    @Column(name = "time")
    private String time;

    @Column(name = "manager")
    private String manager;

    @Column(name = "importantNote")
    private String importantNote;

    @Column(name = "attendees")
    private String attendees;

    @Column(name = "agenda")
    private String agenda;

    @Column(name = "decisions")
    private String decisions;



    @Column(name = "file_path")
    private String filePath;

    @Column(name = "approved")
    private Boolean approved;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "quality_score")
    private Double qualityScore;

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

    public MeetingMinutes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public MeetingMinutes title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(BigDecimal meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getLocation() {
        return this.location;
    }

    public MeetingMinutes location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return this.attendees;
    }

    public MeetingMinutes attendees(String attendees) {
        this.setAttendees(attendees);
        return this;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getAgenda() {
        return this.agenda;
    }

    public MeetingMinutes agenda(String agenda) {
        this.setAgenda(agenda);
        return this;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getDecisions() {
        return this.decisions;
    }

    public MeetingMinutes decisions(String decisions) {
        this.setDecisions(decisions);
        return this;
    }

    public void setDecisions(String decisions) {
        this.decisions = decisions;
    }


    public String getFilePath() {
        return this.filePath;
    }

    public MeetingMinutes filePath(String filePath) {
        this.setFilePath(filePath);
        return this;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getApproved() {
        return this.approved;
    }

    public MeetingMinutes approved(Boolean approved) {
        this.setApproved(approved);
        return this;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Double getQualityScore() {
        return this.qualityScore;
    }

    public MeetingMinutes qualityScore(Double qualityScore) {
        this.setQualityScore(qualityScore);
        return this;
    }

    public void setQualityScore(Double qualityScore) {
        this.qualityScore = qualityScore;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public MeetingMinutes contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public ContractPhase getContractPhase() {
        return this.contractPhase;
    }

    public void setContractPhase(ContractPhase contractPhase) {
        this.contractPhase = contractPhase;
    }

    public MeetingMinutes contractPhase(ContractPhase contractPhase) {
        this.setContractPhase(contractPhase);
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getImportantNote() {
        return importantNote;
    }

    public void setImportantNote(String importantNote) {
        this.importantNote = importantNote;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingMinutes)) {
            return false;
        }
        return getId() != null && getId().equals(((MeetingMinutes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingMinutes{" +
                "id=" + getId() +
                ", title='" + getTitle() + "'" +
                ", meetingDate='" + getMeetingDate() + "'" +
                ", location='" + getLocation() + "'" +
                ", attendees='" + getAttendees() + "'" +
                ", agenda='" + getAgenda() + "'" +
                ", decisions='" + getDecisions() + "'" +
                ", filePath='" + getFilePath() + "'" +
                ", approved='" + getApproved() + "'" +
                ", qualityScore=" + getQualityScore() +
                "}";
    }
}
