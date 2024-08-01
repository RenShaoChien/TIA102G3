package com.tia102g3.coachcourse.model;

import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.systemcourse.model.SystemCourseLevel;
import lombok.*;

import javax.persistence.*;
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
public class CoachCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachCourseID")
    private Integer coachCourseID;
    @ManyToOne
    @JoinColumn(name = "cMemberID", referencedColumnName = "cMemberID", nullable = false)
    @ForeignKey(targetEntity = CoachMember.class, keyField = "cMemberID")
    private CoachMember cMember;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseLevel")
    @Enumerated(EnumType.ORDINAL)
    private SystemCourseLevel courseLevel;
    @Column(name = "courseStartDate")
    private Date courseStartDate;
    @Column(name = "courseEndDate")
    private Date courseEndDate;
    @Column(name = "noOfClasses")
    private Integer noOfClasses;
    @Column(name = "maxCap")
    private Integer maxCap;
    //0: 未開始, 1: 進行中, 2: 已完成, 3: 已取消, 4: 延期
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private CourseStatus status;
    @ManyToOne
    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = true)
    @ForeignKey(targetEntity = SportEvent.class, keyField = "sportEventID")
    private SportEvent sportEvent;
    @Column(name = "coursePrice")
    private Integer coursePrice;
    @Column(name = "classStartTime")
    private Time classStartTime;
    @Column(name = "classEndTime")
    private Time classEndTime;
    @OneToMany(mappedBy = "coachCourse")
    private List<CoachCoursePic> coachCoursePics;
    @OneToMany(mappedBy = "coachCourse")
    private List<CourseOrder> courseOrders;

}
