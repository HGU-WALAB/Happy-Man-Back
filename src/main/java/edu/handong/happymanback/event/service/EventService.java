package edu.handong.happymanback.event.service;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.repository.EventRepository;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EventService {
    public final EventRepository eventRepository;
    public final InstitutionRepository institutionRepository;

    @Autowired
    public EventService(EventRepository eventRepository,InstitutionRepository institutionRepository) {
        this.eventRepository = eventRepository;
        this.institutionRepository=institutionRepository;
    }

    public EventDto eventList(){
        List<EventDto.EventSimple> events=eventRepository.findSimpleList()
                .stream()
                .map(EventDto.EventSimple::new)
                .collect(Collectors.toList());
        return new EventDto(events);
    }

    public Event getEvent(Long id){
        Optional<Event> eventOptional= eventRepository.findById(id);
        if(eventOptional.isPresent()){
            return eventOptional.get();
        }else{
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    public Long createEvent(EventForm form){
        Optional<Institution> institutionOptional=institutionRepository.findById(form.getInstitutionId());
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            Event event = Event.create(institution,form);
            Event saved= eventRepository.save(event);
            return saved.getId();
        } else {
            throw new IllegalArgumentException("Institution not found with id: " + form.getInstitutionId());
        }
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
