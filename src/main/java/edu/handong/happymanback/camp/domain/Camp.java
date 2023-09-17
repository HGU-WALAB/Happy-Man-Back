package edu.handong.happymanback.camp.domain;

import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
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
}
