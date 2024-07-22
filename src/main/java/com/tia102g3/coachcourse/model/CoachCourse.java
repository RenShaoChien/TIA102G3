package com.tia102g3.coachcourse.model;

import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.sportevent.model.SportEvent;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

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
public class CoachCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachCourseID")
    private Integer coachCourseID;
    @ManyToOne
    @JoinColumn(name = "cMemberID", referencedColumnName = "cMemberID", nullable = false)
    private CMember cMember;
    @Column(name = "courseName")
    private String courseName;
    @Column(name = "courseLevel")
    private Integer courseLevel;
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


    public CoachCourse() {
    }

    public CoachCourse(Integer coachCourseID, CMember cMember, String courseName, Integer courseLevel, Date courseStartDate, Date courseEndDate, Integer noOfClasses, Integer maxCap, CourseStatus status, SportEvent sportEvent, Integer coursePrice, Time classStartTime, Time classEndTime) {
        this.coachCourseID = coachCourseID;
        this.cMember = cMember;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.noOfClasses = noOfClasses;
        this.maxCap = maxCap;
        this.status = status;
        this.sportEvent = sportEvent;
        this.coursePrice = coursePrice;
        this.classStartTime = classStartTime;
        this.classEndTime = classEndTime;
    }

    public Integer getCoachCourseID() {
        return coachCourseID;
    }

    public void setCoachCourseID(Integer coachCourseID) {
        this.coachCourseID = coachCourseID;
    }

    public CMember getCMember() {
        return cMember;
    }

    public void setCMember(CMember cMember) {
        this.cMember = cMember;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(Integer courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Date getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public Date getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public Integer getNoOfClasses() {
        return noOfClasses;
    }

    public void setNoOfClasses(Integer noOfClasses) {
        this.noOfClasses = noOfClasses;
    }

    public Integer getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(Integer maxCap) {
        this.maxCap = maxCap;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public Integer getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Integer coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Time getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Time classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Time getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Time classEndTime) {
        this.classEndTime = classEndTime;
    }

    @Override
    public String toString() {
        return "CoachCourse{" +
                "coachCourseID=" + coachCourseID +
                ", cMember=" + cMember +
                ", courseName='" + courseName + '\'' +
                ", courseLevel=" + courseLevel +
                ", courseStartDate=" + courseStartDate +
                ", courseEndDate=" + courseEndDate +
                ", noOfClasses=" + noOfClasses +
                ", maxCap=" + maxCap +
                ", status=" + status +
                ", sportEvent=" + sportEvent +
                ", coursePrice=" + coursePrice +
                ", classStartTime=" + classStartTime +
                ", classEndTime=" + classEndTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachCourse that = (CoachCourse) o;
        return Objects.equals(coachCourseID, that.coachCourseID) && Objects.equals(cMember, that.cMember) && Objects.equals(courseName, that.courseName) && Objects.equals(courseLevel, that.courseLevel) && Objects.equals(courseStartDate, that.courseStartDate) && Objects.equals(courseEndDate, that.courseEndDate) && Objects.equals(noOfClasses, that.noOfClasses) && Objects.equals(maxCap, that.maxCap) && status == that.status && Objects.equals(sportEvent, that.sportEvent) && Objects.equals(coursePrice, that.coursePrice) && Objects.equals(classStartTime, that.classStartTime) && Objects.equals(classEndTime, that.classEndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachCourseID, cMember, courseName, courseLevel, courseStartDate, courseEndDate, noOfClasses, maxCap, status, sportEvent, coursePrice, classStartTime, classEndTime);
    }
}
