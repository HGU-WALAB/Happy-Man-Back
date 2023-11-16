package edu.handong.happymanback.participant.controller;

import edu.handong.happymanback.participant.dto.ParticipantDto;
import edu.handong.happymanback.participant.dto.ParticipantForm;
import edu.handong.happymanback.participant.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService=participantService;
    }

    @PostMapping
    public ResponseEntity<Map<String,Long>> createParticipant(@RequestBody ParticipantForm form){
        Long createdId= participantService.createParticipant(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/participant/" + createdId)
        ).body(Map.of("id", createdId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String,Long>> modifyParticipant(@PathVariable("id") Long id,@RequestBody ParticipantForm form){
        Long modifyId= participantService.modifyParticipant(id, form);
        return ResponseEntity.ok().body(Map.of("id",modifyId));
    }

    @DeleteMapping
    public ResponseEntity<Map<String,List<Long>>> deleteParticipant(@RequestParam("ids") List<Long> ids){
        List<Long> deleteIds=participantService.deleteParticipant(ids);
        return ResponseEntity.ok().body(Map.of("ids",deleteIds));
    }

    @GetMapping("{id}")
    public ResponseEntity<ParticipantDto> getParticipant(@PathVariable("id") Long id){
        return ResponseEntity.ok(participantService.getParticipant(id));
    }
}
