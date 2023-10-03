package edu.handong.happymanback.event.controller;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.service.EventService;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/event")
public class EventController {

    private final EventService eventService;
    private final InstitutionService institutionService;
    @Autowired
    public EventController(EventService eventService,InstitutionService institutionService){
        this.eventService = eventService;
        this.institutionService=institutionService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Long>> createEvent(@RequestBody EventForm form){
        Institution institution=institutionService.getInstitution(form.getInstitutionId());
        Long createdId= eventService.createEvent(institution,form);
        return ResponseEntity.created(
                URI.create("/api/happyman/event/" + createdId)
        ).body(Map.of("id", createdId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String,Long>> modifyEvent(@PathVariable("id")Long id,@RequestBody EventForm form){
        Long modifyId= eventService.modifyEvent(id, form);
        return ResponseEntity.ok().body(Map.of("id",modifyId));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteEvent(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("id", eventService.deleteEvent(id)));
    }

    @GetMapping
    public ResponseEntity<List<Event>> eventList(){
        return ResponseEntity.ok().body(eventService.eventList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getEvent(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(eventService.getEvent(id));
    }
}
