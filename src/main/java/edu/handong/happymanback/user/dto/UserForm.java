package edu.handong.happymanback.user.dto;

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
    private String role;
}