package com.tia102g3.member.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.order.model.OrderVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberID")
    @NonNull
    private Integer memberID;
    private byte[] personalPhotos;
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
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<CustomizedCourse> customizedCoursesList;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    @ToString.Exclude
    private Set<OrderVO> orderSet;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private Set<CourseOrder> courseOrderSet;
    @PrePersist
    protected void onCreate() {
        if (regDate == null) {
            regDate = new Date(System.currentTimeMillis());
        }
    }
    @ToString.Exclude
    private Set<CourseOrder> courseOrderList;    
}
