package com.tia102g3.coachspecialty.model;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.sportevent.model.SportEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachSpecialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cMemberID", nullable = false, referencedColumnName = "cMemberID")
    private CoachMember cMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = false)
    private SportEvent sportEvent;
}
