package edu.handong.happymanback.event.controller;

import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.service.EventService;
import edu.handong.happymanback.excel.exception.FileUploadException;
import edu.handong.happymanback.excel.exception.InvalidFileFormatException;
import edu.handong.happymanback.excel.service.ExcelService;
import edu.handong.happymanback.excel.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;
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
        EventDto eventDto=eventService.getEvent(id);
        String filename =eventDto.getInfo().getYear()+eventDto.getInfo().getSemester()+".xlsx";
        InputStreamResource file = new InputStreamResource(excelService.excelDownload(id));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @PostMapping("/excel/upload/{id}")
    public ResponseEntity<Map<String, List<Long>>> uploadFile(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file) throws FileUploadException {
        if (ExcelUtil.hasExcelFormat(file)) {
            try {
                String message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.ok().body(Map.of(message,excelService.excelUpload(id,file)));
            } catch (Exception e) {
                throw new FileUploadException("Could not upload the file: " + file.getOriginalFilename() + "!", e);
            }
        }
        throw new InvalidFileFormatException("excel");
    }
}