package edu.handong.happymanback.event.controller;

import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.service.EventService;
import edu.handong.happymanback.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/event")
public class EventController {

    private final EventService eventService;
    private final ExcelService excelService;
    @Autowired
    public EventController(EventService eventService, ExcelService excelService){
        this.eventService = eventService;
        this.excelService = excelService;
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
    public ResponseEntity<EventDto> getEvent(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(eventService.getEvent(id));
    }

    @PatchMapping("updateIsOpen/{id}")
    public ResponseEntity<Map<String,Long>> updateIsOpen(@PathVariable("id")Long id,@RequestBody EventForm form){
        Long modifyId= eventService.updateIsOpen(id, form);
        return ResponseEntity.ok().body(Map.of("id",modifyId));
    }

    @GetMapping("/excel/download/{id}")
    public ResponseEntity<Resource> downloadExcel(@PathVariable("id") Long id){
        String filename = "tutorials.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.excelDownload(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
