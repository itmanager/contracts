package com.contract.service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.contract.domain.MeetingMinutes} entity.
 */
//@Schema(description = "صورتجلسات\nمدیریت صورتجلسات جلسات پروژه")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MeetingMinutesDTO implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private ZonedDateTime meetingDate;

    private String location;

    private String attendees;

    private String agenda;

    private String decisions;

    private ZonedDateTime nextMeetingDate;

    private String filePath;

    private Boolean approved;

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

    public ZonedDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(ZonedDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getDecisions() {
        return decisions;
    }

    public void setDecisions(String decisions) {
        this.decisions = decisions;
    }

    public ZonedDateTime getNextMeetingDate() {
        return nextMeetingDate;
    }

    public void setNextMeetingDate(ZonedDateTime nextMeetingDate) {
        this.nextMeetingDate = nextMeetingDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
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
        if (!(o instanceof MeetingMinutesDTO)) {
            return false;
        }

        MeetingMinutesDTO meetingMinutesDTO = (MeetingMinutesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, meetingMinutesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingMinutesDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", meetingDate='" + getMeetingDate() + "'" +
            ", location='" + getLocation() + "'" +
            ", attendees='" + getAttendees() + "'" +
            ", agenda='" + getAgenda() + "'" +
            ", decisions='" + getDecisions() + "'" +
            ", nextMeetingDate='" + getNextMeetingDate() + "'" +
            ", filePath='" + getFilePath() + "'" +
            ", approved='" + getApproved() + "'" +
            ", qualityScore=" + getQualityScore() +
            ", contract=" + getContract() +
            ", contractPhase=" + getContractPhase() +
            "}";
    }
}
