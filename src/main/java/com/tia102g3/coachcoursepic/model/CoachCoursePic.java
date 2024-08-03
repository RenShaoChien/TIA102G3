package com.tia102g3.coachcoursepic.model;

import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.coachcourse.model.CoachCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coachCoursePicID")
    private Integer coachCoursePicID;

    @ManyToOne
    @JoinColumn(name = "coachCourseID", referencedColumnName = "coachCourseID", nullable = false)
    @ForeignKey(targetEntity = CoachCourse.class, keyField = "coachCourseID")
    private CoachCourse coachCourse;

    @Column(name = "pic", columnDefinition = "longblob")
    private byte[] pic;
}
