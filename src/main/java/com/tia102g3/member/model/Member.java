package com.tia102g3.member.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.order.model.OrderVO;

import groovyjarjarantlr4.v4.runtime.misc.Nullable;
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

    @Lob
    private byte[] personalPhotos;

    @NotBlank(message = "姓名不能為空")
    @Size(max = 6, message = "姓名不能超過6個字符")
    private String name;

    @NotBlank(message = "帳號不能為空")
    @Size(min = 5, max = 18, message = "帳號必須在5到18個字符之間")
    private String account;

    private String password;

    @Nullable
    private String email;

    @Nullable
    private String gender;

    @NotBlank(message = "電話號碼不能為空")
    @Pattern(regexp = "^\\d{10,15}$", message = "電話號碼格式不正確")
    private String phone;

    @NotBlank(message = "地址不能為空")
    private String address;

    @Nullable
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
    @ToString.Exclude
    private List<CustomizedCourse> customizedCoursesList;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    @ToString.Exclude
    private Set<OrderVO> orderSet;

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    @ToString.Exclude
    private Set<CourseOrder> courseOrderSet;

    @PrePersist
    protected void onCreate() {
        if (regDate == null) {
            regDate = new Date(System.currentTimeMillis());
        }
    }
}
