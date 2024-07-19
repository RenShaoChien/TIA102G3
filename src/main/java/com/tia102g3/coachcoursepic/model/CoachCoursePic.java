package com.tia102g3.coachcoursepic.model;

import com.tia102g3.coachcourse.model.CoachCourse;

import javax.persistence.*;
import java.util.Arrays;

/**
 * ClassName： CoachCoursePic
 * package：com.tia102g3.coachcoursepic
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "coach_course_pic")
public class CoachCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachCoursePicID")
    private Integer coachCoursePicID;

    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "coachCourseID", nullable = false)
    private CoachCourse coachCourse;

    @Column(name = "pic", columnDefinition = "longblob")
    private byte[] pic;

    public CoachCoursePic() {
    }

    public CoachCoursePic(Integer coachCoursePicID, CoachCourse coachCourse, byte[] pic) {
        this.coachCoursePicID = coachCoursePicID;
        this.coachCourse = coachCourse;
        this.pic = pic;
    }

    public Integer getCoachCoursePicID() {
        return coachCoursePicID;
    }

    public void setCoachCoursePicID(Integer coachCoursePicID) {
        this.coachCoursePicID = coachCoursePicID;
    }

    public CoachCourse getCoachCourse() {
        return coachCourse;
    }

    public void setCoachCourse(CoachCourse coachCourse) {
        this.coachCourse = coachCourse;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "CoachCoursePic{" + "coachCoursePicID=" + coachCoursePicID + ", coachCourse=" + coachCourse + ", pic=" + Arrays.toString(pic) + '}';
    }
}
