package com.tia102g3.systemcourse.model;

import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class SystemCourse implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer systemCourseID;

    @Column(name = "courseName", length = 50)
    private String courseName;

//    @ManyToOne
//    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = false)
//    @ForeignKey(targetEntity = SportEvent.class, keyField = "sportEventID")
//    private SportEvent sportEvent;

    private String sportEventName;

    private String sportTypes;

    private String sportEquipment;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "courseLevel")
    private SystemCourseLevel courseLevel;

    private Integer burnCalories;

    private Integer rps;

    private String eachExerciseTime;

    private String sportTime;

    private Integer swp;

    private String illustrate;

    private String video;


    @OneToMany(mappedBy = "systemCourse")
    private List<CustomizedCourse> customizedCourses;
    @OneToMany(mappedBy = "systemCourse")
    private List<SystemCoursePic> systemCoursePics;
}
