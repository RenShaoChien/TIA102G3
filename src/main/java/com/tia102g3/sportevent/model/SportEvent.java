package com.tia102g3.sportevent.model;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachspecialty.model.CoachSpecialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * ClassName： SportEvent
 * package：com.tia102g3.sportevent.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "sport_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SportEvent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportEventID")
    private Integer sportEventID;


    @Column(name = "sportEventName", length = 100)
    @NotEmpty(message = "請輸入運動項目名稱")
    private String sportEventName;



    @Column(name = "sportTypes", length = 100)
    private String sportTypes;



    @Column(name = "sportEquipment", length = 100)
    private String sportEquipment;




    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoachCourse> coachCourses;
//    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<SystemCourse> systemCourses;
    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoachSpecialty> coachSpecialties;
}
