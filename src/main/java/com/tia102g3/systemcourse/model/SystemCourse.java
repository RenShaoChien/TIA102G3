package com.tia102g3.systemcourse.model;

import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
