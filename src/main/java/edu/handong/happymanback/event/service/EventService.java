package edu.handong.happymanback.event.service;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.repository.EventRepository;
import edu.handong.happymanback.institution.domain.Institution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {
    public final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> eventList(){
        return eventRepository.findAll();
    }

    public Event getEvent(Long id){
        Optional<Event> eventOptional= eventRepository.findById(id);
        if(eventOptional.isPresent()){
            return eventOptional.get();
        }else{
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    public Long createEvent(Institution institution, EventForm form){
        Event event = Event.create(institution,form);
        Event saved= eventRepository.save(event);
        return saved.getId();
    }

    public Long deleteEvent(Long id){
        eventRepository.deleteById(id);
        return id;
    }

    public Long modifyEvent(Long id, EventForm form){
        Optional<Event> eventOptional = eventRepository.findById(id);

        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            return event.update(form);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }

    }
}
