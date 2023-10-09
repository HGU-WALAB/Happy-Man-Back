package edu.handong.happymanback.event.repository;
import edu.handong.happymanback.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event,Long>{

    @Query("select e from Event e join fetch e.institution i")
    List<Event> findSimpleList();
}
