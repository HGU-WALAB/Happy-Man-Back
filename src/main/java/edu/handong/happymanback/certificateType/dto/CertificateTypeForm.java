package edu.handong.happymanback.certificateType.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CertificateTypeForm {
    private Long institutionId;
    private String name;
}
