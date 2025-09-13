package com.contract.repository;

import com.contract.domain.MeetingMinutes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MeetingMinutes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeetingMinutesRepository extends JpaRepository<MeetingMinutes, Long> {}
