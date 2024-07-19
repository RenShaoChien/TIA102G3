package com.tia102g3.systemcoursepic.model;

import com.tia102g3.systemcourse.model.SystemCourse;

import javax.persistence.*;
import java.util.Arrays;

/**
 * ClassName： SystemCoursePic
 * package：com.tia102g3.systemcoursepic
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "system_course_pic")
public class SystemCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "systemCoursePicID", updatable = false)
    private Integer systemCoursePicID;
    @ManyToOne
    @JoinColumn(name = "systemCourseID", referencedColumnName = "systemCourseID", nullable = false)
    private SystemCourse systemCourse;
    @Column(name = "pic", columnDefinition = "LONGBLOB")
    private byte[] pic;

    public SystemCoursePic() {
    }

    public SystemCoursePic(Integer systemCoursePicID, SystemCourse systemCourse, byte[] pic) {
        this.systemCoursePicID = systemCoursePicID;
        this.systemCourse = systemCourse;
        this.pic = pic;
    }

    public Integer getSystemCoursePicID() {
        return systemCoursePicID;
    }

    public void setSystemCoursePicID(Integer systemCoursePicID) {
        this.systemCoursePicID = systemCoursePicID;
    }

    public SystemCourse getSystemCourse() {
        return systemCourse;
    }

    public void setSystemCourse(SystemCourse systemCourse) {
        this.systemCourse = systemCourse;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "SystemCoursePic{" +
                "systemCoursePicID=" + systemCoursePicID +
                ", systemCourse=" + systemCourse +
                ", pic=" + Arrays.toString(pic) +
                '}';
    }
}
