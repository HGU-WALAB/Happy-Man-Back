package edu.handong.happymanback.participant.dto;

import edu.handong.happymanback.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ParticipantForm {
    private Long eventId;

    private String studentid;

    private State state;
}
