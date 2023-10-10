package edu.handong.happymanback.user.dto;

import edu.handong.happymanback.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Getter
public class UserDto {

    private List<Info> infoList;

    @Getter
    @NoArgsConstructor
    public static class Info {
        private String personalId;
        private String name;
        private String department;
        public Info(User user){
            this.personalId=user.getPersonalId();
            this.name=user.getName();
            this.department=user.getDepartment();
        }
    }
}