package edu.handong.happymanback.participant.dto;

import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.utils.enums.State;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ParticipantDto {

    private List<ParticipantDto.Info> infoList;

    @Getter
    @NoArgsConstructor
    public static class Info {
        private State state;
        private UserDto.Info student;
        public Info(Participant participant){
            this.state=participant.getState();
            this.student=new UserDto.Info(participant.getStudent());
        }
    }
}
