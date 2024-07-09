package com.tia102g3.coachcourse.model;

import java.sql.Date;
import java.util.Objects;

public class CoachCourse {
    private Integer coachCourseID;
    private Integer cMemberID;
    private String courseName;
    private Integer courseLevel;
    private Date courseStartTime;
    private Date courseEndTime;
    private Integer noOfClasses;
    private Integer maxCap;
    private Integer status;
    private Integer sportEventID;
    private Integer coursePrice;

    public CoachCourse() {
    }

    public CoachCourse(Integer coachCourseID, Integer cMemberID, String courseName, Integer courseLevel, Date courseStartTime, Date courseEndTime, Integer noOfClasses, Integer maxCap, Integer status, Integer sportEventID, Integer coursePrice) {
        this.coachCourseID = coachCourseID;
        this.cMemberID = cMemberID;
        this.courseName = courseName;
        this.courseLevel = courseLevel;
        this.courseStartTime = courseStartTime;
        this.courseEndTime = courseEndTime;
        this.noOfClasses = noOfClasses;
        this.maxCap = maxCap;
        this.status = status;
        this.sportEventID = sportEventID;
        this.coursePrice = coursePrice;
    }

    public Integer getCoachCourseID() {
        return coachCourseID;
    }

    public void setCoachCourseID(Integer coachCourseID) {
        this.coachCourseID = coachCourseID;
    }

    public Integer getCMemberID() {
        return cMemberID;
    }

    public void setCMemberID(Integer cMemberID) {
        this.cMemberID = cMemberID;
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

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public Date getCourseEndTime() {
        return courseEndTime;
    }

    public void setCourseEndTime(Date courseEndTime) {
        this.courseEndTime = courseEndTime;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSportEventID() {
        return sportEventID;
    }

    public void setSportEventID(Integer sportEventID) {
        this.sportEventID = sportEventID;
    }

    public Integer getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Integer coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Override
    public String toString() {
        return "CoachCourse{" +
                "coachCourseID=" + coachCourseID +
                ", cMemberID=" + cMemberID +
                ", courseName='" + courseName + '\'' +
                ", courseLevel=" + courseLevel +
                ", courseStartTime=" + courseStartTime +
                ", courseEndTime=" + courseEndTime +
                ", noOfClasses=" + noOfClasses +
                ", maxCap=" + maxCap +
                ", status=" + status +
                ", sportEventID=" + sportEventID +
                ", coursePrice=" + coursePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachCourse that = (CoachCourse) o;
        return Objects.equals(coachCourseID, that.coachCourseID) && Objects.equals(cMemberID, that.cMemberID) && Objects.equals(courseName, that.courseName) && Objects.equals(courseLevel, that.courseLevel) && Objects.equals(courseStartTime, that.courseStartTime) && Objects.equals(courseEndTime, that.courseEndTime) && Objects.equals(noOfClasses, that.noOfClasses) && Objects.equals(maxCap, that.maxCap) && Objects.equals(status, that.status) && Objects.equals(sportEventID, that.sportEventID) && Objects.equals(coursePrice, that.coursePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coachCourseID, cMemberID, courseName, courseLevel, courseStartTime, courseEndTime, noOfClasses, maxCap, status, sportEventID, coursePrice);
    }
}
