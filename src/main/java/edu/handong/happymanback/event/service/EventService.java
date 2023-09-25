package edu.handong.happymanback.event.service;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.repository.EventRepository;
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

    public List<Event> campList(){
        return eventRepository.findAll();
    }

    public Event getCamp(Long id){
        Optional<Event> campOptional= eventRepository.findById(id);
        if(campOptional.isPresent()){
            return campOptional.get();
        }else{
            throw new IllegalArgumentException("Camp not found with id: " + id);
        }
    }

    public Long createCamp(EventForm form){
        Event event = Event.create(form);
        Event saved= eventRepository.save(event);
        return saved.getId();
    }

    public Long deleteCamp(Long id){
        eventRepository.deleteById(id);
        return id;
    }

    public Long modifyCamp(Long id, EventForm form){
        Optional<Event> campOptional = eventRepository.findById(id);

        if (campOptional.isPresent()) {
            Event event = campOptional.get();
            return event.update(form);
        } else {
            throw new IllegalArgumentException("Camp not found with id: " + id);
        }

    }
}
