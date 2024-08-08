package com.tia102g3.systemcourse.service;

import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcoursepic.model.SystemCoursePic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName： SystemCourseService
 * package：com.tia102g3.systemcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCourseService {
    void insertSystemCourse(SystemCourse systemCourse, MultipartFile[] courseImages) throws Exception;

    int deleteSystemCourse(Integer systemCourseID) throws Exception;

    SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception;

    List<SystemCourse> getSystemCoursesList(String keyword, Integer pageNo) throws Exception;

    List<SystemCoursePic> getSystemCoursePicsBySystemCourseId(Integer systemCourseID);

    void updateSystemCourse(SystemCourse systemCourse, MultipartFile[] courseImages) throws Exception;

    void addSystemCourse(SystemCourse systemCourse);

    Long getSystemCourseCount(String keyword);
}
