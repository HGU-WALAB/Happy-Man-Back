package edu.handong.happymanback.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.handong.happymanback.utils.enums.Authority;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private List<Info> list;
    private Info info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    public static class Info {
        private String personalId;
        private String password;
        private String name;
        private Authority authority;
        private String department;
        private String email;
    }
}