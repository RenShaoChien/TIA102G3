package com.tia102g3.systemcourse.model;

import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @NotBlank(message = "請輸入系統課程名稱")
    private String courseName;

//    @ManyToOne
//    @JoinColumn(name = "sportEventID", referencedColumnName = "sportEventID", nullable = false)
//    @ForeignKey(targetEntity = SportEvent.class, keyField = "sportEventID")
//    private SportEvent sportEvent;

    @NotBlank(message = "請選擇運動項目")
    private String sportEventName;

    @NotBlank(message = "請選擇運動項目")
    private String sportTypes;

    @NotBlank(message = "請選擇運動器材")
    private String sportEquipment;

    @Column(name = "courseLevel")
    @Enumerated(EnumType.ORDINAL)
    private SystemCourseLevel courseLevel;

    @Column(name = "burnCalories")
    @NotNull(message = "不能空白")
    @Digits(integer = 3, fraction = 0, message = "只能輸入數字")
    private Integer burnCalories;

    @Column(name = "rps")
    @Digits(integer = 3, fraction = 0, message = "只能輸入數字")
    private Integer rps;

    @Column(name = "eachExerciseTime")
    @Pattern(regexp = "^\\d+分鐘$", message = "請輸入有效的分鐘數，例如 '30分鐘'")
    private String eachExerciseTime;

    @Column(name = "sportTime")
    @Pattern(regexp = "^\\d+(小時|分鐘)$", message = "請輸入有效的時間，例如 '2小時' 或 '30分鐘'")
    private String sportTime;

    @Column(name = "swp")
    @Digits(integer = 3, fraction = 0, message = "只能輸入數字")
    private Integer swp;

    @Column(name = "illustrate", length = 10000)
    @NotBlank(message = "請輸入運動說明")
    private String illustrate;

    @Column(name = "video", length = 500)
    @Pattern(regexp = "^http.*$", message = "請輸入以 'http' 開頭的有效網址")
    private String video;


    @OneToMany(mappedBy = "systemCourse")
    private List<CustomizedCourse> customizedCourses;
    @OneToMany(mappedBy = "systemCourse")
    private List<SystemCoursePic> systemCoursePics;
}
