package com.tia102g3.systemcoursepic.model;

import com.tia102g3.basedao.ForeignKey;
import com.tia102g3.systemcourse.model.SystemCourse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * ClassName： SystemCoursePic
 * package：com.tia102g3.systemcoursepic
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
@Entity
@Table(name = "system_course_pic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "systemCoursePicID", updatable = false)
    private Integer systemCoursePicID;
    @ManyToOne
    @JoinColumn(name = "systemCourseID", referencedColumnName = "systemCourseID", nullable = false)
    @ForeignKey(targetEntity = SystemCourse.class, keyField = "systemCourseID")
    private SystemCourse systemCourse;
    @Column(name = "pic", columnDefinition = "LONGBLOB")
//    @NotEmpty(message="請上傳課程圖片")
    private byte[] pic;
}
