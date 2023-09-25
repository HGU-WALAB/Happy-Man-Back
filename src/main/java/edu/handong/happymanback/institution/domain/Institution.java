package edu.handong.happymanback.institution.domain;

import edu.handong.happymanback.institution.dto.InstitutionForm;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="institution")
public class Institution extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="institution_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 60)
    private String description;

    public static Institution createInstitution(InstitutionForm form){
        Institution institution=new Institution();
        institution.setName(form.getName());
        institution.setDescription(form.getDescription());
        return institution;
    }

    public Long update(InstitutionForm form){
        this.name = form.getName() != null ? form.getName() : name;
        this.description = form.getDescription() != null ? form.getDescription() : description;
        return id;
    }


}
