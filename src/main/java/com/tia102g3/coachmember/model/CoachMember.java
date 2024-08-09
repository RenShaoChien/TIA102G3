package com.tia102g3.coachmember.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

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
public class CoachMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cMemberID")
    private Integer cMemberID;
    private byte[] personalPhotos;
    private Integer status;
    private String name;
    private String account;
    private String password;
    private String email;
    private String gender;
    private String phone;
    private String address;
    private Date bD;
    private Date regDate;
    private String receiver;
    private String receiverAddress;
    private String receiverPhone;
    private String cardName;
    private String cardNumber;
    private String cardValidTime;
    private String cardLast3No;
    private String cardPhone;
}
