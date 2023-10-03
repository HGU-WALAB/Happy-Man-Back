package edu.handong.happymanback.event.dto;

import edu.handong.happymanback.utils.CertificateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventForm {
    private Long institutionId;
    private String name;
    private String image;
    private String content;
    private CertificateType certificateType;
}
