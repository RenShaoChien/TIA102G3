package com.tia102g3.sportevent.model;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.systemcourse.model.SystemCourse;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
public class SportEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sportEventID")
    private Integer sportEventID;
    @Column(name = "sportEventName", length = 100)
    private String sportEventName;
    @Column(name = "sportTypes", length = 100)
    private String sportTypes;
    @Column(name = "sportEquipment", length = 100)
    private String sportEquipment;

    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoachCourse> coachCourses;
    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SystemCourse> systemCourses;
    @OneToMany(mappedBy = "sportEvent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CoachSpecialty> coachSpecialties;

    public SportEvent() {
    }

    public SportEvent(Integer sportEventID, String sportEventName, String sportTypes, String sportEquipment) {
        this.sportEventID = sportEventID;
        this.sportEventName = sportEventName;
        this.sportTypes = sportTypes;
        this.sportEquipment = sportEquipment;
    }

    public Integer getSportEventID() {
        return sportEventID;
    }

    public void setSportEventID(Integer sportEventID) {
        this.sportEventID = sportEventID;
    }

    public String getSportEventName() {
        return sportEventName;
    }

    public void setSportEventName(String sportEventName) {
        this.sportEventName = sportEventName;
    }

    public String getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(String sportTypes) {
        this.sportTypes = sportTypes;
    }

    public String getSportEquipment() {
        return sportEquipment;
    }

    public void setSportEquipment(String sportEquipment) {
        this.sportEquipment = sportEquipment;
    }

    public List<CoachCourse> getCoachCourses() {
        return coachCourses;
    }

    public void setCoachCourses(List<CoachCourse> coachCourses) {
        this.coachCourses = coachCourses;
    }

    public List<SystemCourse> getSystemCourses() {
        return systemCourses;
    }

    public void setSystemCourses(List<SystemCourse> systemCourses) {
        this.systemCourses = systemCourses;
    }

    public List<CoachSpecialty> getCoachSpecialties() {
        return coachSpecialties;
    }

    public void setCoachSpecialties(List<CoachSpecialty> coachSpecialties) {
        this.coachSpecialties = coachSpecialties;
    }

    @Override
    public String toString() {
        return "SportEvent{" +
                "sportEquipment='" + sportEquipment + '\'' +
                ", sportTypes='" + sportTypes + '\'' +
                ", sportEventName='" + sportEventName + '\'' +
                ", sportEventID=" + sportEventID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SportEvent that = (SportEvent) o;
        return Objects.equals(sportEventID, that.sportEventID) && Objects.equals(sportEventName, that.sportEventName) && Objects.equals(sportTypes, that.sportTypes) && Objects.equals(sportEquipment, that.sportEquipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportEventID, sportEventName, sportTypes, sportEquipment);
    }
}
