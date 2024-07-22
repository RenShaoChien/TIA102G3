package com.tia102g3.systemcourse.model;

import java.util.List;

/**
 * ClassName： SystemCourseDAO
 * package：com.tia102g3.systemcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCourseDAO {
    /**
     * 新增課程
     * @param systemCourse
     */
    int insertSystemCourse(SystemCourse systemCourse) throws Exception;

    /**
     * 根據ID獲取課程
     * @param systemCourseID
     * @return
     */
    SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception;

    /**
     * 更新課程
     * @param systemCourse
     */
    int updateSystemCourse(SystemCourse systemCourse) throws Exception;

    /**
     * 刪除課程
     * @param systemCourseID
     */
    int deleteSystemCourse(Integer systemCourseID) throws Exception;

    /**
     * 獲取所有課程
     * @return List<SystemCourse>
     */
    List<SystemCourse> getAllSystemCourses() throws Exception;
}
