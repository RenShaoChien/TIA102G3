package com.tia102g3.coachcertificate.model;


import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.coachmember.model.CoachMember;
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
    @ForeignKey(targetEntity = CoachMember.class, keyField = "cMemberID")
    private CoachMember cMember;
    @Column(name = "certificateName", nullable = false, length = 200)
    private String certificateName;
    @Column(name = "certificatePic", columnDefinition = "LONGBLOB"  )
    private byte[] certificatePic;

}
