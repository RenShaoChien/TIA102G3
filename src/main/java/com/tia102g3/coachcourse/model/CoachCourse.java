package com.tia102g3.coachcourse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.systemcourse.model.SystemCourseLevel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * ClassName： CoachCourse
 * package：com.tia102g3.coachcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/6/30 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "coach_course")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CoachCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cMemberID", referencedColumnName = "cMemberID", nullable = false)
    @JsonManagedReference
    private CoachMember cMember;

    private String courseName;

    @Column(name = "courseLevel")
    @Enumerated(EnumType.ORDINAL)
    private SystemCourseLevel courseLevel;

    private Date courseStartDate;

    private Date courseEndDate;

    private Integer noOfClasses;

    private Integer maxCap;

    //0: 未開始, 1: 進行中, 2: 已完成, 3: 已取消, 4: 延期
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private CourseStatus status;

    private String sportEventName;

    private String sportTypes;

    private String sportEquipment;

    private Integer coursePrice;

    private Time classStartTime;

    private Time classEndTime;

    private String illustrate;

    private String courseIntro;

    @OneToMany(mappedBy = "coachCourse")
    @JsonIgnore
    private List<CoachCoursePic> coachCoursePics;

    @OneToMany(mappedBy = "coachCourse")
    @JsonIgnore
    private List<CourseOrder> courseOrders;

}
