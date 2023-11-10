package edu.handong.happymanback.certificate.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class CertificateDto {
    private String department;
    private String studentId;
    private String name;
    private String event;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate certificateIssueDate;
    private String institution;
    private String serialNumber;
    private String issuingName;
    private String stamp;
}
