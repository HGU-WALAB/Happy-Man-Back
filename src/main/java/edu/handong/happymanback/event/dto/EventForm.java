package edu.handong.happymanback.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EventForm {
    private Long institutionId;
    private String name;
    private String image;
    private String content;
}
