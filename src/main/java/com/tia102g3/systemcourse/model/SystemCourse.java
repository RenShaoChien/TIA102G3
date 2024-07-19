package com.tia102g3.systemcourse.model;

import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

/**
 * ClassName： SystemCourse
 * package：com.tia102g3.systemcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "system_course")
public class SystemCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "systemCourseID")
    private Integer systemCourseID;
    @Column(name = "courseName")
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = false)
    SportEvent sportEvent;
    @Column(name = "courseLevel")
    SystemCourseLevel courseLevel;
    @Column(name = "burnCalories")
    Integer burnCalories;
    @Column(name = "rps")
    Integer rps;
    @Column(name = "eachExerciseTime")
    private Time eachExerciseTime;
    @Column(name = "sportTime")
    private Time sportTime;
    @Column(name = "swp")
    private Integer swp;
    @Column(name = "illustrate", length = 10000)
    private String illustrate;
    @Column(name = "video", length = 500)
    private String video;
    @OneToMany(mappedBy = "systemCourse")
    private List<CustomizedCourse> customizedCourses;
    @OneToMany(mappedBy = "systemCourse")
    private List<SystemCoursePic> systemCoursePics;

    public SystemCourse() {
    }

    public SystemCourse(Integer systemCourseID, String courseName, SportEvent sportEvent, SystemCourseLevel courseLevel, Integer burnCalories, Integer rps, Time eachExerciseTime, Time sportTime, Integer swp, String illustrate, String video) {
        this.systemCourseID = systemCourseID;
        this.courseName = courseName;
        this.sportEvent = sportEvent;
        this.courseLevel = courseLevel;
        this.burnCalories = burnCalories;
        this.rps = rps;
        this.eachExerciseTime = eachExerciseTime;
        this.sportTime = sportTime;
        this.swp = swp;
        this.illustrate = illustrate;
        this.video = video;
    }

    public Integer getSystemCourseID() {
        return systemCourseID;
    }

    public void setSystemCourseID(Integer systemCourseID) {
        this.systemCourseID = systemCourseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public SystemCourseLevel getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(SystemCourseLevel courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Integer getBurnCalories() {
        return burnCalories;
    }

    public void setBurnCalories(Integer burnCalories) {
        this.burnCalories = burnCalories;
    }

    public Integer getRps() {
        return rps;
    }

    public void setRps(Integer rps) {
        this.rps = rps;
    }

    public Time getEachExerciseTime() {
        return eachExerciseTime;
    }

    public void setEachExerciseTime(Time eachExerciseTime) {
        this.eachExerciseTime = eachExerciseTime;
    }

    public Time getSportTime() {
        return sportTime;
    }

    public void setSportTime(Time sportTime) {
        this.sportTime = sportTime;
    }

    public Integer getSwp() {
        return swp;
    }

    public void setSwp(Integer swp) {
        this.swp = swp;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public List<CustomizedCourse> getCustomizedCourses() {
        return customizedCourses;
    }

    public void setCustomizedCourses(List<CustomizedCourse> customizedCourses) {
        this.customizedCourses = customizedCourses;
    }

    public List<SystemCoursePic> getSystemCoursePics() {
        return systemCoursePics;
    }

    public void setSystemCoursePics(List<SystemCoursePic> systemCoursePics) {
        this.systemCoursePics = systemCoursePics;
    }

    @Override
    public String toString() {
        return "SystemCourse{" +
                "systemCourseID=" + systemCourseID +
                ", courseName='" + courseName + '\'' +
                ", sportEvent=" + sportEvent +
                ", courseLevel=" + courseLevel +
                ", burnCalories=" + burnCalories +
                ", rps=" + rps +
                ", eachExerciseTime=" + eachExerciseTime +
                ", sportTime=" + sportTime +
                ", swp=" + swp +
                ", illustrate='" + illustrate + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemCourse that = (SystemCourse) o;
        return Objects.equals(systemCourseID, that.systemCourseID) && Objects.equals(courseName, that.courseName) && Objects.equals(sportEvent, that.sportEvent) && courseLevel == that.courseLevel && Objects.equals(burnCalories, that.burnCalories) && Objects.equals(rps, that.rps) && Objects.equals(eachExerciseTime, that.eachExerciseTime) && Objects.equals(sportTime, that.sportTime) && Objects.equals(swp, that.swp) && Objects.equals(illustrate, that.illustrate) && Objects.equals(video, that.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemCourseID, courseName, sportEvent, courseLevel, burnCalories, rps, eachExerciseTime, sportTime, swp, illustrate, video);
    }
}
