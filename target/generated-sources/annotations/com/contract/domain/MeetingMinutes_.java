package com.contract.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MeetingMinutes.class)
public abstract class MeetingMinutes_ {

	public static volatile SingularAttribute<MeetingMinutes, String> attendees;
	public static volatile SingularAttribute<MeetingMinutes, String> filePath;
	public static volatile SingularAttribute<MeetingMinutes, Contract> contract;
	public static volatile SingularAttribute<MeetingMinutes, String> title;
	public static volatile SingularAttribute<MeetingMinutes, String> agenda;
	public static volatile SingularAttribute<MeetingMinutes, Boolean> approved;
	public static volatile SingularAttribute<MeetingMinutes, ContractPhase> contractPhase;
	public static volatile SingularAttribute<MeetingMinutes, ZonedDateTime> meetingDate;
	public static volatile SingularAttribute<MeetingMinutes, Double> qualityScore;
	public static volatile SingularAttribute<MeetingMinutes, String> location;
	public static volatile SingularAttribute<MeetingMinutes, String> decisions;
	public static volatile SingularAttribute<MeetingMinutes, ZonedDateTime> nextMeetingDate;
	public static volatile SingularAttribute<MeetingMinutes, Long> id;

	public static final String ATTENDEES = "attendees";
	public static final String FILE_PATH = "filePath";
	public static final String CONTRACT = "contract";
	public static final String TITLE = "title";
	public static final String AGENDA = "agenda";
	public static final String APPROVED = "approved";
	public static final String CONTRACT_PHASE = "contractPhase";
	public static final String MEETING_DATE = "meetingDate";
	public static final String QUALITY_SCORE = "qualityScore";
	public static final String LOCATION = "location";
	public static final String DECISIONS = "decisions";
	public static final String NEXT_MEETING_DATE = "nextMeetingDate";
	public static final String ID = "id";

}

