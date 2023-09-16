package edu.handong.happymanback.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserDto {
    public static class Info {
        private Long id=0L;
        private String name = null;
        private String personalId = null;
        private String department = null;
        private String password=null;
        private String role="student";
    }

    private List<Info> user;
}