package edu.handong.happymanback.certificateType.controller;

import edu.handong.happymanback.certificateType.domain.CertificateType;
import edu.handong.happymanback.certificateType.dto.CertificateTypeForm;
import edu.handong.happymanback.certificateType.service.CertificateTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/certificateType")
public class CertificateTypeController {

    private final CertificateTypeService certificateTypeService;

    public CertificateTypeController(CertificateTypeService certificateTypeService){this.certificateTypeService=certificateTypeService;}

    @PostMapping
    public ResponseEntity<Map<String, Long>> createCertificateType(@RequestBody CertificateTypeForm form){
        Long createId=certificateTypeService.createCertificateType(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/certificateType/" + createId)
        ).body(Map.of("id", createId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, Long>> modifyCertificateType(@PathVariable("id")Long id,@RequestBody CertificateTypeForm form){
        Long modifyId= certificateTypeService.modifyCertificateType(id, form);
        return ResponseEntity.ok().body(Map.of("CertificateType id",modifyId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> modifyCertificateType(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("CertificateType id",certificateTypeService.deleteCertificateType(id)));
    }

    @GetMapping
    public ResponseEntity<List<CertificateType>> getCertificateTypeList(@RequestBody Long institutionId){
        return ResponseEntity.ok().body(certificateTypeService.getCertificateTypeListByInstitutionId(institutionId));
    }

    @GetMapping("{id}")
    public ResponseEntity<CertificateType> getCertificateType(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(certificateTypeService.getCertificateType(id));
    }
}
