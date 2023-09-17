package edu.handong.happymanback.camp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CampForm {
    private String name;
    private String image;
    private String content;
    private String department;
}
