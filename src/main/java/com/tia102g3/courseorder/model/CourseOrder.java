package com.tia102g3.courseorder.model;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "course_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseOrderID;
    
    @ManyToOne
    @JoinColumn(name = "memberID", referencedColumnName = "memberID")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "id")
    private CoachCourse coachCourse;
    
    private Date orderDate;
    private Integer price;
    private Integer status;

    public Integer getMemberID() {
        return member != null ? member.getMemberID() : null;
    }
}
