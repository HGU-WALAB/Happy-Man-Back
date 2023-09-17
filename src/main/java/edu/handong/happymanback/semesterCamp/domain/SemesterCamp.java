package edu.handong.happymanback.semesterCamp.domain;

import edu.handong.happymanback.camp.domain.Camp;
import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="semester_camp")
public class SemesterCamp extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="semester_camp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="camp_id")
    private Camp camp;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User professor;

    @Column(nullable = false,length = 10)
    private String semester;

    @Column(nullable = false,length = 20)
    private String fee;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false, length = 20)
    private String state;

    private String detailContent;

    @Column(nullable = false)
    private int currentStaff=0;

    @Column(name="years",nullable = false, length = 10)
    private String year;
}
