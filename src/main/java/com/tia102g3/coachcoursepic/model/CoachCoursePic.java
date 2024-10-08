package com.tia102g3.coachcoursepic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tia102g3.coachcourse.model.CoachCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private CoachCourse coachCourse;

    @Column(name = "pic", columnDefinition = "longblob")
    private byte[] pic;
}
