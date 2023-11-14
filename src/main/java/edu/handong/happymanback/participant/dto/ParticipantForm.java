package edu.handong.happymanback.participant.dto;

import edu.handong.happymanback.utils.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantForm {
    private Long eventId;

    private String studentId;

    private State state;

    private String serialNumber;
}
