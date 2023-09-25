package edu.handong.happymanback.eventDetail.domain;

import edu.handong.happymanback.event.domain.Event;
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
@Table(name="event_detail")
public class EventDetail extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_detail_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="manager_id")
    private User manager;

    @Column(name="years",nullable = false, length = 10)
    private String year;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(columnDefinition = "TEXT")
    private String detailContent;

    @Column(nullable = false)
    private int currentStaff=0;

    @Column(length = 20)
    private String content1_use;

    @Column(length = 20)
    private String content2_use;

    @Column(length = 20)
    private String content3_use;
}
