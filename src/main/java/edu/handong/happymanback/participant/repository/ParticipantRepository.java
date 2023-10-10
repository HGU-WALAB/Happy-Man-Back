package edu.handong.happymanback.participant.repository;

import edu.handong.happymanback.participant.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {

}
