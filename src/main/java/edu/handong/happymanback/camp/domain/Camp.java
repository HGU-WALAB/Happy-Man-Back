package edu.handong.happymanback.camp.domain;

import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import edu.handong.happymanback.camp.dto.CampForm;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="camp")
public class Camp extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="camp_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    private String image;

    private String content;

    @Column(nullable = false)
    private String department;

    public static Camp create(CampForm form){
        Camp camp = new Camp();
        camp.setName(form.getName());
        camp.setImage(form.getImage());
        camp.setContent(form.getContent());
        camp.setDepartment(form.getDepartment());
        return camp;
    }

    public Long update(CampForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.content = form.getContent() != null ? form.getContent() : content;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.image = form.getImage() != null ? form.getImage() : image;
        return id;
    }
}
