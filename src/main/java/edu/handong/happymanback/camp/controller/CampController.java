package edu.handong.happymanback.camp.controller;

import edu.handong.happymanback.camp.domain.Camp;
import edu.handong.happymanback.camp.dto.CampForm;
import edu.handong.happymanback.camp.service.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/camp")
public class CampController {

    private final CampService campService;

    @Autowired
    public CampController(CampService campService){this.campService=campService;}

    @PostMapping
    public ResponseEntity<Map<String,Long>> createCamp(@RequestBody CampForm form){
        Long createdId=campService.createCamp(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/camp/" + createdId)
        ).body(Map.of("id", createdId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String,Long>> modifyCamp(@PathVariable("id")Long id,@RequestBody CampForm form){
        Long modifyId=campService.modifyCamp(id, form);
        return ResponseEntity.ok().body(Map.of("id",modifyId));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteCamp(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("id",campService.deleteCamp(id)));
    }

    @GetMapping
    public ResponseEntity<List<Camp>> campList(){
        return ResponseEntity.ok().body(campService.campList());
    }

    @GetMapping("{id}")
    public ResponseEntity<Camp> camp(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(campService.getCamp(id));
    }
}
