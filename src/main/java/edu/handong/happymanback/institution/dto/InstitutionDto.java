package edu.handong.happymanback.institution.dto;

import edu.handong.happymanback.institution.domain.Institution;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class InstitutionDto {
    private Long id;
    private String name;
    private String description;


    public InstitutionDto(Institution institution) {
        this.id=institution.getId();
        this.name=institution.getName();
        this.description=institution.getDescription();
    }
}
