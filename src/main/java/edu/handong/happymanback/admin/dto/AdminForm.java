package edu.handong.happymanback.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminForm {
    private String id;

    private String name;

    private String password;
}
