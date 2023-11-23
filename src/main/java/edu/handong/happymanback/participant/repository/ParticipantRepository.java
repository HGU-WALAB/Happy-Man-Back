package edu.handong.happymanback.participant.repository;

import edu.handong.happymanback.participant.domain.Participant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant,Long> {
    @Query("SELECT p FROM Participant p JOIN FETCH p.event e JOIN FETCH e.institution WHERE p.id IN :participantIds")
    List<Participant> findCertificateList(List<Long> participantIds);
    List<Participant> findByEventId(Long eventId);
    void deleteByEventId(Long eventId);
    @Modifying
    @Transactional
    @Query("delete from Participant p where p.id in :ids")
    void deleteByIds(List<Long> ids);
}