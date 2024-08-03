package com.tia102g3.courseorder.model;

import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * ClassName： CourseOrder
 * package：com.tia102g3.courseorder.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 ${TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "course_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseOrderID")
    private Integer courseOrderID;
    @ManyToOne
    @JoinColumn(name = "memberID", referencedColumnName = "memberID")
    @ForeignKey(targetEntity = Member.class, keyField = "memberID")
    private Member member;
    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "coachCourseID")
    @ForeignKey(targetEntity = CoachCourse.class, keyField = "coachCourseID")
    private CoachCourse coachCourse;
    private Date orderDate;
    private Integer price;
    private Integer status;
}
