package edu.handong.happymanback.event.dto;

import edu.handong.happymanback.utils.enums.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class EventForm {
    private Long institutionId;
    private String name;
    private String professor;
    private String year;
    private Semester semester;
    private LocalDate applicationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private String content;
    private LocalDate certificateIssueDate;
    private String issuingName;
    private Boolean isOpen;
}
