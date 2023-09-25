package edu.handong.happymanback.event.repository;
import edu.handong.happymanback.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long>{

}
