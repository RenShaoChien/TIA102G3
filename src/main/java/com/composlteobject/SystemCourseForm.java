package com.composlteobject;

import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName： SystemCourseForm
 * package：com.composlteobject
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/3 上午2:35
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemCourseForm {

    private SystemCourse systemCourse;
    private SystemCoursePic systemCoursePic;
}
