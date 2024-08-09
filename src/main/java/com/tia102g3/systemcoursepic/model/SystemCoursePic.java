package com.tia102g3.systemcoursepic.model;

import com.tia102g3.systemcourse.model.SystemCourse;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;

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
@RequiredArgsConstructor
public class SystemCoursePic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "systemCoursePicID", updatable = false)
    @NonNull
    private Integer systemCoursePicID;
    @ManyToOne
    @JoinColumn(name = "systemCourseID", referencedColumnName = "systemCourseID", nullable = false)
    @NonNull
    private SystemCourse systemCourse;
    @Column(name = "pic", columnDefinition = "LONGBLOB")
//    @NotEmpty(message="請上傳課程圖片")
    private byte[] pic;

    public SystemCoursePic(SystemCourse systemCourse, MultipartFile courseImages) throws IOException {
        this.systemCourse = systemCourse;
        this.pic = courseImages.getBytes();
    }

}
