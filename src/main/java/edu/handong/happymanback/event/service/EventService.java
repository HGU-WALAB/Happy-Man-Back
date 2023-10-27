package edu.handong.happymanback.event.service;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.repository.EventRepository;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.institution.repository.InstitutionRepository;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.dto.ParticipantDto;
import edu.handong.happymanback.student.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final InstitutionRepository institutionRepository;

    @Autowired
    public EventService(EventRepository eventRepository,InstitutionRepository institutionRepository) {
        this.eventRepository = eventRepository;
        this.institutionRepository=institutionRepository;
    }

    public EventDto eventList(){
        List<Event> eventList=eventRepository.findCardList();
        List<EventDto.Info> list=new ArrayList<>();
        for(Event event:eventList){
            list.add(EventDto.Info.builder()
                    .id(event.getId())
                    .institution(new InstitutionDto(null,InstitutionDto.Info.builder()
                            .id(event.getInstitution().getId())
                            .name(event.getInstitution().getName())
                            .build()))
                    .name(event.getName())
                    .manager(event.getManager())
                    .year(event.getYear())
                    .semester(event.getSemester())
                    .image(event.getImage())
                    .applicationDate(event.getApplicationDate())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .content(event.getContent())
                    .certificateIssueDate(event.getCertificateIssueDate())
                    .build());
        }
        return new EventDto(list,null);
    }

    public EventDto getEvent(Long id){
        Optional<Event> eventOptional= eventRepository.findById(id);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            List<ParticipantDto.Info> participantList=new ArrayList<>();
            for (Participant participant: event.getParticipantList()){
                participantList.add(ParticipantDto.Info.builder()
                        .id(participant.getId())
                        .state(participant.getState())
                        .student(new StudentDto(null,StudentDto.Info.builder()
                                .studentId(participant.getStudent().getStudentId())
                                .name(participant.getStudent().getName())
                                .department(participant.getStudent().getDepartment())
                                .build()))
                        .build());
            }
            return new EventDto(null,EventDto.Info.builder()
                    .id(event.getId())
                    .institution(new InstitutionDto(null,InstitutionDto.Info.builder()
                            .id(event.getInstitution().getId())
                            .name(event.getInstitution().getName())
                            .build()))
                    .participantList(new ParticipantDto(participantList,null))
                    .name(event.getName())
                    .manager(event.getManager())
                    .year(event.getYear())
                    .semester(event.getSemester())
                    .image(event.getImage())
                    .applicationDate(event.getApplicationDate())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .content(event.getContent())
                    .certificateIssueDate(event.getCertificateIssueDate())
                    .build());
        }else{
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    public Long createEvent(EventForm form){
        Optional<Institution> institutionOptional=institutionRepository.findById(form.getInstitutionId());
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            Event event = Event.create(institution,form);
            Event saved= eventRepository.save(event);
            return saved.getId();
        } else {
            throw new IllegalArgumentException("Institution not found with id: " + form.getInstitutionId());
        }
    }

    public Long deleteEvent(Long id){
        eventRepository.deleteById(id);
        return id;
    }

    public Long modifyEvent(Long id, EventForm form){
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventOptional.get().update(form);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }
}
