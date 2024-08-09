package com.tia102g3.systemcourse.model;

import com.tia102g3.customizedcourse.model.CustomizedCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
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
@RequiredArgsConstructor
public class SystemCourse implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer systemCourseID;

    @Column(name = "courseName", length = 50)
    @NotBlank(message = "課程名稱不能為空")
    private String courseName;

    @NotBlank(message = "運動項目不能為空")
    private String sportEventName;

    @NotBlank(message = "運動項目類型不能為空")
    private String sportTypes;

    @NotBlank(message = "運動器材不能為空")
    private String sportEquipment;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "courseLevel")
    @NotNull(message = "課程等級不能為空")
    private SystemCourseLevel courseLevel;

    @NotNull(message = "消耗熱量不能為空")
    @Min(value = 0, message = "消耗熱量不能為負數")
    private Integer burnCalories;

    @Min(value = 5, message = "建議每組次數不能少於5")
    @Max(value = 20, message = "建議每組次數不能大於20")
    private Integer rps;

    private String eachExerciseTime;

    private String sportTime;

    @Min(value = 3, message = "建議每次組數不能少於3")
    @Max(value = 9, message = "建議每次組數不能大於9")
    private Integer swp;

    @NotBlank(message = "說明不能為空")
    private String illustrate;

    @NotBlank(message = "課程影片連結不能為空")
    @Pattern(regexp = "^(http|https)://.*$", message = "影片連結必須以http或https開頭")
    private String video;


    @OneToMany(mappedBy = "systemCourse")
    private List<CustomizedCourse> customizedCourses;

    @OneToMany(mappedBy = "systemCourse")
    private List<SystemCoursePic> systemCoursePics;

}
