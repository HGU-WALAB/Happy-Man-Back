package edu.handong.happymanback.participant.domain;

import edu.handong.happymanback.eventDetail.domain.EventDetail;
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
@Table(name="participant")
public class Participant extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participant_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="event_detail_id")
    private EventDetail eventDetail;

    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state=State.WAITING;

    private Date issueDate;

    @Column(length =30)
    private String serialNumber;

    private String content1;

    private String content2;

    private String content3;
}
