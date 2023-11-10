package edu.handong.happymanback.event.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.participant.dto.ParticipantDto;
import edu.handong.happymanback.utils.enums.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {

    private List<Info> list;
    private Info info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    public static class Info{
        private Long id;
        private InstitutionDto institution;
        private ParticipantDto participantList;
        private String name;
        private String professor;
        private String year;
        private Semester semester;
        private String image;
        private LocalDate applicationDate;
        private LocalDate startDate;
        private LocalDate endDate;
        private String content;
        private LocalDate certificateIssueDate;
        private Boolean isOpen;
        private String stamp;
        private String issuingName;
    }
}
