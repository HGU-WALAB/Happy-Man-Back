package edu.handong.happymanback.event.controller;

import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/event")
public class EventController {

    private final EventService eventService;
    @Autowired
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Long>> createEvent(@RequestBody EventForm form){
        Long createdId= eventService.createEvent(form);
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
    public ResponseEntity<EventDto> searchEvent(){
        return ResponseEntity.ok(eventService.eventList());
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDto.EventDetail> getEvent(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(eventService.getEvent(id));
    }
}
