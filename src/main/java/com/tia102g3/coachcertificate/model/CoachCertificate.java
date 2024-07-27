package com.tia102g3.coachcertificate.model;


import com.tia102g3.coach_member.model.CoachMemberVO;
import lombok.*;

import javax.persistence.*;

/**
 * ClassName： CoachCertificate
 * package：com.tia102g3.coachcertificate.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "coach_certificate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachCertificateID", updatable = false)
    private Integer coachCertificateID;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cMemberID", referencedColumnName = "cMemberID", nullable = false)
    private CoachMemberVO cMember;
    @Column(name = "certificateName", nullable = false, length = 200)
    private String certificateName;
    @Column(name = "certificatePic", columnDefinition = "LONGBLOB"  )
    private byte[] certificatePic;

}
