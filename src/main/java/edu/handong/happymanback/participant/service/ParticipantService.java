package edu.handong.happymanback.participant.service;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.event.repository.EventRepository;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.dto.ParticipantDto;
import edu.handong.happymanback.participant.dto.ParticipantForm;
import edu.handong.happymanback.participant.repository.ParticipantRepository;
import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;
    private final UserRepository UserRepository;

    public ParticipantService(ParticipantRepository participantRepository,EventRepository eventRepository,UserRepository UserRepository){
        this.participantRepository=participantRepository;
        this.eventRepository=eventRepository;
        this.UserRepository=UserRepository;
    }

    public Long createParticipant(ParticipantForm form){
        Optional<Event> eventOptional=eventRepository.findById(form.getEventId());
        if (eventOptional.isPresent()) {
            Optional<User> userOptional=UserRepository.findById(form.getStudentId());
            if (userOptional.isPresent()){
                Participant participant=Participant.create(eventOptional.get(),userOptional.get(),form);
                Participant saved=participantRepository.save(participant);
                return saved.getId();
            }
            else{
                throw new IllegalArgumentException("User not found with id: " + form.getStudentId());
            }
        } else {
            throw new IllegalArgumentException("Event not found with id: " + form.getEventId());
        }
    }

    public List<Long> deleteParticipant(List<Long> ids){
        participantRepository.deleteByIds(ids);
        return ids;
    }

    public Long modifyParticipant(Long id, ParticipantForm form){
        Optional<Participant> participantOptional=participantRepository.findById(id);
        if(participantOptional.isPresent()){
            return participantOptional.get().update(form);
        }
        else{
            throw new IllegalArgumentException("Participant not found with id: " + id);
        }
    }

    public ParticipantDto getParticipant(Long id){
        Optional<Participant> participantOptional= participantRepository.findById(id);
        if(participantOptional.isPresent()){
            Participant participant=participantOptional.get();
            return new ParticipantDto(null, ParticipantDto.Info.builder()
                    .id(participant.getId())
                    .serialNumber(participant.getSerialNumber())
                    .build());
        }
        else{
            throw new IllegalArgumentException("Participant not found with id: " + id);
        }
    }
}
