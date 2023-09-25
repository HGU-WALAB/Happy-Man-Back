package edu.handong.happymanback.representative.domain;

import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="representative")
public class Representative extends BaseTime {

    @Id
    @Column(name="template_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long representativeId;

    private String name;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    private String stamp;
}
