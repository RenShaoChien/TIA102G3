package com.tia102g3.coachmember.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachspecialty.model.CoachSpecialty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "coach_member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CoachMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cMemberID")
    @NonNull
    private Integer cMemberID;

    @Lob
    private byte[] personalPhotos;

    @NotNull(message = "狀態不能為空")
    @Enumerated(EnumType.ORDINAL)
    private CoachStatus status;

    @NotBlank(message = "姓名不能為空")
    @Size(max = 6, message = "姓名不能超過6個字符")
    private String name;

    @NotBlank(message = "帳號不能為空")
    @Size(min = 5, max = 18, message = "帳號必須在5到18個字符之間")
    private String account;
    
    private String password;

    @NotBlank(message = "電子郵件不能為空")
    @Email(message = "電子郵件格式不正確")
    private String email;

    @NotBlank(message = "性別不能為空")
    @Pattern(regexp = "男|女", message = "性別必須是男或女")
    private String gender;

    @NotBlank(message = "電話號碼不能為空")
    @Pattern(regexp = "^\\d{10,15}$", message = "電話號碼格式不正確")
    private String phone;

    @NotBlank(message = "地址不能為空")
    private String address;

    @NotNull(message = "生日不能為空")
    private Date bD;

    private Date regDate;

    private String receiver;

    private String receiverAddress;

    private String receiverPhone;

    private String cardName;

    @Pattern(regexp = "\\d{16}", message = "信用卡號必須是16位數字")
    private String cardNumber;

    private String cardValidTime;

    @Pattern(regexp = "\\d{3}", message = "信用卡后三碼必須是3位數字")
    private String cardLast3No;

    private String cardPhone;

    @OneToMany(mappedBy = "cMember" )
    @JsonBackReference
    @ToString.Exclude
    private List<CoachSpecialty> coachSpecialties;

    @OneToMany(mappedBy = "cMember" )
    @JsonBackReference
    @ToString.Exclude
    private List<CoachCourse> coachCourses;

    @PrePersist
    protected void onCreate() {
        if (regDate == null) {
            regDate = new Date(System.currentTimeMillis());
        }
    }
}
