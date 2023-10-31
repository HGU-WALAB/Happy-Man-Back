package edu.handong.happymanback.participant.repository;

import edu.handong.happymanback.participant.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    @Query("SELECT p FROM Participant p JOIN FETCH p.user JOIN FETCH p.event e JOIN FETCH e.institution WHERE p.id IN :participantIds")
    List<Participant> findCertificateList(List<Long> participantIds);
}