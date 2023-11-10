package edu.handong.happymanback.participant.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.utils.enums.State;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDto {

    private List<ParticipantDto.Info> list;
    private Info info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    public static class Info {
        private Long id;
        private State state;
        private UserDto user;
        private String serialNumber;
    }
}
