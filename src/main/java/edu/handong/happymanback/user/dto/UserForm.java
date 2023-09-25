package edu.handong.happymanback.user.dto;

import edu.handong.happymanback.user.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class UserForm {
    private String name;
    private String personalId;
    private String password;
    private String department;
    private Authority authority;
    private String email;
}