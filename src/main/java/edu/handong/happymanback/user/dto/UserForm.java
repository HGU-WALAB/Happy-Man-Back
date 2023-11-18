package edu.handong.happymanback.user.dto;

import edu.handong.happymanback.utils.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
public class UserForm {
    private String name;
    private String uniqueId;
    private String password;
    private String department;
    private String email;
    private Role role;
}