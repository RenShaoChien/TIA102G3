package com.tia102g3.coachspecialty.model;

import com.tia102g3.sportevent.model.SportEvent;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName： CoachSpecialty
 * package：com.tia102g3.coachspecialty.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/19 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "coach_specialty")
public class CoachSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachSpecialtyID", updatable = false)
    private Integer coachSpecialtyID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cMemberID", nullable = false, referencedColumnName = "cMemberID")
    private CMember cMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = false)
    private SportEvent sportEvent;

    public CoachSpecialty() {
    }

    public CoachSpecialty(Integer coachSpecialtyID, CMember cMember, SportEvent sportEvent) {
        this.coachSpecialtyID = coachSpecialtyID;
        this.cMember = cMember;
        this.sportEvent = sportEvent;
    }

    public Integer getCoachSpecialtyID() {
        return coachSpecialtyID;
    }

    public void setCoachSpecialtyID(Integer coachSpecialtyID) {
        this.coachSpecialtyID = coachSpecialtyID;
    }

    public CMember getCMember() {
        return cMember;
    }

    public void setCMember(CMember cMember) {
        this.cMember = cMember;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    @Override
    public String toString() {
        return "CoachSpecialty{" +
                "coachSpecialtyID=" + coachSpecialtyID +
                ", cMember=" + cMember +
                ", sportEvent=" + sportEvent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachSpecialty that = (CoachSpecialty) o;
        return Objects.equals(coachSpecialtyID, that.coachSpecialtyID) && Objects.equals(cMember, that.cMember) && Objects.equals(sportEvent, that.sportEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachSpecialtyID, cMember, sportEvent);
    }
}
