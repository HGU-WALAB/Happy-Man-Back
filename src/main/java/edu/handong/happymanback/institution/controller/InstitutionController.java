package edu.handong.happymanback.institution.controller;

import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.institution.dto.InstitutionForm;
import edu.handong.happymanback.institution.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/institution")
public class InstitutionController {

    private final InstitutionService institutionService;
    @Autowired
    public InstitutionController(InstitutionService institutionService){this.institutionService=institutionService;}

    @PostMapping
    public ResponseEntity<Map<String, Long>> createInstitution(@RequestBody InstitutionForm form) {
        Long createId = institutionService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/institution/" + createId)
        ).body(Map.of("id", createId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, Long>> modifyInstitution(@PathVariable("id")Long id,@RequestBody InstitutionForm form) {
        Long modifystudentId = institutionService.modifyInstitution(id,form);
        return ResponseEntity.ok().body(Map.of("institution id",modifystudentId));
    }

    @GetMapping
    public ResponseEntity<InstitutionDto> institutionList(){
        return ResponseEntity.ok().body(institutionService.institutionList());
    }

    @GetMapping("{id}")
    public ResponseEntity<InstitutionDto> institution(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(institutionService.getInstitution(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteInstitution(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("institution id",institutionService.deleteInstitution(id)));
    }
}
