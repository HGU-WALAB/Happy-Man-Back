package edu.handong.happymanback.event.domain;

import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.utils.BaseTime;
import edu.handong.happymanback.utils.enums.Semester;
import jakarta.persistence.*;
import edu.handong.happymanback.event.dto.EventForm;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="event")
public class Event extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="institution_id")
    private Institution institution;

    @OneToMany(mappedBy = "event")
    private List<Participant> participantList= new ArrayList<>();

    @Column(nullable = false, length = 40)
    private String name;

    @Column(length = 20)
    private String professor;

    @Column(nullable = false,length = 10)
    private String year;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(length = 1000)
    private String image;

    private LocalDate applicationDate;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDate certificateIssueDate;

    private Boolean isOpen=false;

    @Column(length = 1000, nullable = false)
    private String stamp;

    @Column(length = 50, nullable = false)
    private String issuingName;


    public static Event create(Institution institution, EventForm form){
        Event event = new Event();
        event.setInstitution(institution);
        event.setName(form.getName());
        event.setProfessor(form.getProfessor());
        event.setYear(form.getYear());
        event.setSemester(form.getSemester());
        event.setImage(form.getImage());
        event.setApplicationDate((form.getApplicationDate()));
        event.setStartDate(form.getStartDate());
        event.setEndDate(form.getEndDate());
        event.setContent(form.getContent());
        event.setCertificateIssueDate(form.getCertificateIssueDate());
        event.setStamp(form.getStamp());
        event.setIssuingName(form.getIssuingName());
        return event;
    }

    public Long update(EventForm form) {
        this.name = form.getName() != null ? form.getName() : this.name;
        this.professor = form.getProfessor() != null ? form.getProfessor() : this.professor;
        this.year = form.getYear() != null ? form.getYear() : this.year;
        this.semester = form.getSemester() != null ? form.getSemester() : this.semester;
        this.image = form.getImage() != null ? form.getImage() : this.image;
        this.applicationDate = form.getApplicationDate() != null ? form.getApplicationDate() : this.applicationDate;
        this.startDate = form.getStartDate() != null ? form.getStartDate() : this.startDate;
        this.endDate = form.getEndDate() != null ? form.getEndDate() : this.endDate;
        this.content = form.getContent() != null ? form.getContent() : this.content;
        this.certificateIssueDate = form.getCertificateIssueDate() != null ? form.getCertificateIssueDate() : this.certificateIssueDate;
        this.stamp = form.getStamp() != null ? form.getStamp() : this.stamp;
        this.issuingName = form.getIssuingName() != null ? form.getIssuingName() : this.issuingName;
        return id;
    }

    public Long updateIsOpen(EventForm form){
        this.isOpen=form.getIsOpen();
        return id;
    }
}
