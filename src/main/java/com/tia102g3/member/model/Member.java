package com.tia102g3.member.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.order.model.OrderVO;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * ClassName： Member
 * package：com.tia102g3.member.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 下午8:08
 * @Version 1.0
 */
@Entity
@Table(name = "member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Set<OrderVO> orderSet;
    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<CourseOrder> courseOrderList;
}
