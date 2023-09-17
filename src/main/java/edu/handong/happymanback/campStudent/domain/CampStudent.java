package edu.handong.happymanback.campStudent.domain;

import edu.handong.happymanback.semesterCamp.domain.SemesterCamp;
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
@Table(name="camp_student")
public class CampStudent extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="camp_student_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="semester_camp_id")
    private SemesterCamp semesterCamp;

    @Column(nullable = false)
    private boolean feeState=false;

    private int serialNumber;
}
