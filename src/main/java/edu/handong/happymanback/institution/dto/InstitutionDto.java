package edu.handong.happymanback.institution.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
public class InstitutionDto {

    private List<Info> list;
    private Info info;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    public static class Info{
        private Long id;
        private String name;
        private String description;
    }
}
