package edu.handong.happymanback.event.controller;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/camp")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService){this.eventService = eventService;}

    @PostMapping
    public ResponseEntity<Map<String,Long>> createCamp(@RequestBody EventForm form){
        Long createdId= eventService.createCamp(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/camp/" + createdId)
        ).body(Map.of("id", createdId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String,Long>> modifyCamp(@PathVariable("id")Long id,@RequestBody EventForm form){
        Long modifyId= eventService.modifyCamp(id, form);
        return ResponseEntity.ok().body(Map.of("id",modifyId));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteCamp(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("id", eventService.deleteCamp(id)));
    }

    @GetMapping
    public ResponseEntity<List<Event>> campList(){
        return ResponseEntity.ok().body(eventService.campList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> camp(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(eventService.getCamp(id));
    }
}
