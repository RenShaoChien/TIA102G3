package com.tia102g3.courseorder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.member.model.Member;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @ToString.Exclude
    @NotNull(message = "會員不能為空")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "id")
    @JsonBackReference
    @NonNull
    @NotNull(message = "教練課程不能為空")
    private CoachCourse coachCourse;
    
    @NotNull(message = "訂購日期不能為空")
    @PastOrPresent(message = "訂購日期不能是未來日期")
    private Date orderDate;
    
    @NotNull(message = "價格不能為空")
    @Min(value = 0, message = "價格不能小於0")
    private Integer price;
    
    @NotNull(message = "狀態不能為空")
    @Min(value = 0, message = "狀態不能小於0")
    @Max(value = 2, message = "狀態不能大於2")
    private Integer status;

    public Integer getMemberID() {
        return member != null ? member.getMemberID() : null;
    }
}
