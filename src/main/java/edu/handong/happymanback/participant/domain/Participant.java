package edu.handong.happymanback.participant.domain;

import edu.handong.happymanback.event.domain.Event;
import edu.handong.happymanback.participant.dto.ParticipantForm;
import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.utils.BaseTime;
import edu.handong.happymanback.utils.enums.State;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="participant")
public class Participant extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participant_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state=State.WAITING;

    @Column(length = 30)
    private String serialNumber;

    public static Participant create(Event event, User user,ParticipantForm form){
        Participant participant=new Participant();
        participant.setEvent(event);
        participant.setUser(user);
        if(form.getState()!=null) {
            participant.setState(form.getState());
        }
        participant.setSerialNumber(form.getSerialNumber());
        return participant;
    }

    public Long update(ParticipantForm form){
        this.state = form.getState() != null ? form.getState() : this.state;
        this.serialNumber = form.getSerialNumber() != null ? form.getSerialNumber() : this.serialNumber;
        return id;
    }
}
