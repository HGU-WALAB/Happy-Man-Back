package edu.handong.happymanback.event.service;

import edu.handong.happymanback.aws.s3.service.S3Service;
import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.dto.EventDto;
import edu.handong.happymanback.event.dto.EventForm;
import edu.handong.happymanback.event.repository.EventRepository;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.institution.repository.InstitutionRepository;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.dto.ParticipantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {
    private final EventRepository eventRepository;
    private final InstitutionRepository institutionRepository;

    private final S3Service s3Service;

    @Autowired
    public EventService(EventRepository eventRepository, InstitutionRepository institutionRepository, S3Service s3Service) {
        this.eventRepository = eventRepository;
        this.institutionRepository=institutionRepository;
        this.s3Service = s3Service;
    }

    public EventDto adminEventList(){
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
                    .professor(event.getProfessor())
                    .year(event.getYear())
                    .semester(event.getSemester())
                    .image(s3Service.getEventUrl(event.getImage()))
                    .applicationDate(event.getApplicationDate())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .content(event.getContent())
                    .certificateIssueDate(event.getCertificateIssueDate())
                    .isOpen(event.getIsOpen())
                    .build());
        }
        return new EventDto(list,null);
    }

    public EventDto adminGetEvent(Long id){
        Optional<Event> eventOptional= eventRepository.findById(id);
        if(eventOptional.isPresent()){
            Event event=eventOptional.get();
            List<ParticipantDto.Info> participantList=new ArrayList<>();
            for (Participant participant: event.getParticipantList()){
                participantList.add(ParticipantDto.Info.builder()
                        .id(participant.getId())
                        .serialNumber(participant.getSerialNumber())
                        .name(participant.getName())
                                .studentId(participant.getStudentId())
                                .department(participant.getDepartment())
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
                    .professor(event.getProfessor())
                    .year(event.getYear())
                    .semester(event.getSemester())
                    .image(s3Service.getEventUrl(event.getImage()))
                    .applicationDate(event.getApplicationDate())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .content(event.getContent())
                    .certificateIssueDate(event.getCertificateIssueDate())
                    .isOpen(event.getIsOpen())
                    .issuingName(event.getIssuingName())
                    .stamp(s3Service.getImageUrl(event.getStamp()))
                    .build());
        }else{
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    public Long createEvent(EventForm form, MultipartFile image, MultipartFile stamp) throws IOException {
        String imageName=null;
        String stampName=null;
        if(image!=null)
            imageName= s3Service.upload(image,"event");
        if(stamp!=null)
            stampName= s3Service.upload(stamp,"stamp");
        Optional<Institution> institutionOptional=institutionRepository.findById(form.getInstitutionId());
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            Event event = Event.create(institution,form,imageName,stampName);
            Event saved= eventRepository.save(event);
            return saved.getId();
        } else {
            throw new IllegalArgumentException("Institution not found with id: " + form.getInstitutionId());
        }
    }

    public Long deleteEvent(Long id){
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event=eventOptional.get();
            if(event.getImage()!=null){
                s3Service.delete(event.getImage());
            }
            if(event.getStamp()!=null){
                s3Service.delete(event.getStamp());
            }
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
        return id;
    }

    public Long modifyEvent(Long id, EventForm form, MultipartFile image, MultipartFile stamp) throws IOException {
        String imageName=null;
        String stampName=null;
        if(image!=null)
            imageName= s3Service.upload(image,"event");
        if(stamp!=null)
            stampName= s3Service.upload(stamp,"stamp");
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            if(eventOptional.get().getImage()!=null&&imageName!=null){
                s3Service.delete(eventOptional.get().getImage());
            }
            if(eventOptional.get().getStamp()!=null&&stampName!=null){
                s3Service.delete(eventOptional.get().getStamp());
            }
            return eventOptional.get().update(form,imageName,stampName);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }

    public Long updateIsOpen(Long id, EventForm form){
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            return eventOptional.get().updateIsOpen(form);
        } else {
            throw new IllegalArgumentException("Event not found with id: " + id);
        }
    }
}
