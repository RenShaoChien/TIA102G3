package com.tia102g3.customizedcourse.model;

import com.tia102g3.member.model.Member;
import com.tia102g3.systemcourse.model.SystemCourse;

import javax.persistence.*;
import java.util.Objects;

/**
 * ClassName： CustomizedCourse
 * package：com.tia102g3.customizedcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/18 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "customized_course")
public class CustomizedCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customizedCourseID", nullable = false)
    private Integer customizedCourseID;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "systemCourseID", nullable = false, referencedColumnName = "systemCourseID")
    private SystemCourse systemCourse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID", nullable = false, referencedColumnName = "memberID")
    private Member member;

    public CustomizedCourse() {
    }

    public CustomizedCourse(Integer customizedCourseID, SystemCourse systemCourse, Member member) {
        this.customizedCourseID = customizedCourseID;
        this.systemCourse = systemCourse;
        this.member = member;
    }

    public Integer getCustomizedCourseID() {
        return customizedCourseID;
    }

    public void setCustomizedCourseID(Integer customizedCourseID) {
        this.customizedCourseID = customizedCourseID;
    }

    public SystemCourse getSystemCourse() {
        return systemCourse;
    }

    public void setSystemCourse(SystemCourse systemCourse) {
        this.systemCourse = systemCourse;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "CustomizedCourse{" +
                "customizedCourseID=" + customizedCourseID +
                ", systemCourse=" + systemCourse +
                ", member=" + member +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomizedCourse that = (CustomizedCourse) o;
        return Objects.equals(customizedCourseID, that.customizedCourseID) && Objects.equals(systemCourse, that.systemCourse) && Objects.equals(member, that.member);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customizedCourseID, systemCourse, member);
    }
}
