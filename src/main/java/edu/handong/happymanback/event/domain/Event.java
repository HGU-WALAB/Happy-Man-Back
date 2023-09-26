package edu.handong.happymanback.event.domain;

import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.utils.BaseTime;
import edu.handong.happymanback.utils.CertificateType;
import jakarta.persistence.*;
import edu.handong.happymanback.event.dto.EventForm;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="event")
public class Event extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="institution_id")
    private Institution institution;

    @Column(nullable = false, length = 30)
    private String name;

    private String image;

    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CertificateType certificateType;


    /*기관 관련 세팅 수정 필요!*/
    public static Event create(EventForm form){
        Event event = new Event();
        event.setName(form.getName());
        event.setImage(form.getImage());
        event.setContent(form.getContent());
        event.setCertificateType(form.getCertificateType());
        return event;
    }

    /*기관 관련 세팅 수정 필요!*/
    public Long update(EventForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.content = form.getContent() != null ? form.getContent() : content;
        this.certificateType = form.getCertificateType() != null ? form.getCertificateType() : certificateType;
        this.image = form.getImage() != null ? form.getImage() : image;
        return id;
    }
}
