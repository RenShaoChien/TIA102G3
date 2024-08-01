package com.tia102g3.coachmember.model;

import com.tia102g3.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * ClassName： CoachMember
 * package：com.tia102g3.coachmember.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "coach_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cMemberID")
    private Integer cMemberID;
    private byte[] profilePic;
    private Integer status;
    @OneToOne
    private Member member;
}
