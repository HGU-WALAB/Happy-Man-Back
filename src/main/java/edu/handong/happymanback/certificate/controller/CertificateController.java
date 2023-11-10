package edu.handong.happymanback.certificate.controller;

import edu.handong.happymanback.certificate.dto.CertificateDto;
import edu.handong.happymanback.certificate.pdf.CertificatePDFGenerator;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/certificate")
public class CertificateController {

    @Autowired
    ParticipantRepository participantRepository;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> customerReport(@RequestParam("participantIds") List<Long> participantIds){
        List<CertificateDto> certificateDtoList = new ArrayList<>();
        List<Participant> participantList = participantRepository.findCertificateList(participantIds);
        for (Participant participant : participantList) {
            certificateDtoList.add(CertificateDto.builder()
                    .department(participant.getUser().getDepartment())
                    .studentId(participant.getUser().getStudentId())
                    .name(participant.getUser().getName())
                    .event(participant.getEvent().getName())
                    .startDate(participant.getEvent().getStartDate())
                    .endDate(participant.getEvent().getEndDate())
                    .certificateIssueDate(participant.getEvent().getCertificateIssueDate())
                    .institution(participant.getEvent().getInstitution().getName())
                    .serialNumber(participant.getSerialNumber())
                    .issuingName(participant.getEvent().getIssuingName())
                    .serialNumber(participant.getSerialNumber())
                    .stamp(participant.getEvent().getStamp())
                    .build());
        }

        ByteArrayInputStream bis = CertificatePDFGenerator.customerPDFReport(certificateDtoList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=certificate.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}