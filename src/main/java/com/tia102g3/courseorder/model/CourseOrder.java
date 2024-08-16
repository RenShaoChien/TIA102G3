package com.tia102g3.courseorder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.member.model.Member;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "course_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class CourseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseOrderID;
    
    @ManyToOne
    @JoinColumn(name = "memberID", referencedColumnName = "memberID")
    @NonNull
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    private CoachCourse coachCourse;
    
    private Date orderDate;
    private Integer price;
    private Integer status;

    public Integer getMemberID() {
        return member != null ? member.getMemberID() : null;
    }
}
