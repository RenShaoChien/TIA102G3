package com.tia102g3.systemcourse.service;

import com.tia102g3.systemcourse.model.SystemCourse;

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
    /**
     * 增
     * @param systemCourse
     * @throws Exception
     */
    int insertSystemCourse(SystemCourse systemCourse) throws Exception;

    /**
     * 改
     * @param systemCourse
     * @throws Exception
     */
    int updateSystemCourse(SystemCourse systemCourse) throws Exception;

    /**
     * 刪
     * @param systemCourseID
     * @throws Exception
     */
    int deleteSystemCourse(Integer systemCourseID) throws Exception;

    /**
     * 查
     * @param systemCourseID
     * @return
     * @throws Exception
     */
    SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception;

    List<SystemCourse> getAllSystemCoursesList() throws Exception;
}
