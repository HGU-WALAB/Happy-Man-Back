package edu.handong.happymanback.template.domain;

import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="template")
public class Template extends BaseTime {

    @Id
    @Column(name="template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long templateId;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String font;

    @Column(length = 80)
    private String nameLocation;

    @Column(length = 80)
    private String representativeLocation;

    @Column(length = 80)
    private String issueDateLocation;

    @Column(length = 80)
    private String serialNumberLocation;

    @Column(length = 80)
    private String periodLocation;

    @Column(length = 80)
    private String stampLocation;

    @Column(length = 80)
    private String eventNameLocation;

    @Column(length = 80)
    private String personalIdLocation;

    @Column(length = 80)
    private String departmentLocation;

    @Column(length = 80)
    private String content1Location;

    @Column(length = 80)
    private String content2Location;

    @Column(length = 80)
    private String content3Location;

    @Column(length = 20)
    private String content1Use;

    @Column(length = 20)
    private String content2Use;

    @Column(length = 20)
    private String content3Use;
}
