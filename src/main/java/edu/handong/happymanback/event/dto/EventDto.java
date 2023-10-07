package edu.handong.happymanback.event.dto;


import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.utils.enums.Semester;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@Getter
public class EventDto {

    private List<EventSimple> eventSimpleList;

    @Getter
    @NoArgsConstructor
    public static class EventSimple{
        private Long id;
        private InstitutionDto institution;
        private String name;
        private String manager;
        private String year;
        private Semester semester;
        private String image;
        private LocalDate applicationDate;
        private LocalDate startDate;
        private LocalDate endDate;
        public EventSimple(Event event) {
            this.id = event.getId();
            this.institution = new InstitutionDto(event.getInstitution());
            this.name = event.getName();
            this.manager = event.getManager();
            this.year = event.getYear();
            this.semester = event.getSemester();
            this.image = event.getImage();
            this.applicationDate = event.getApplicationDate();
            this.startDate = event.getStartDate();
            this.endDate = event.getEndDate();
        }
    }
}
