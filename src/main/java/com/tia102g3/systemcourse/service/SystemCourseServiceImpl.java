package com.tia102g3.systemcourse.service;

import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.model.SystemCourseDAO;

/**
 * ClassName： SystemCourseServiceImpl
 * package：com.tia102g3.systemcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public class SystemCourseServiceImpl implements SystemCourseService {
    private SystemCourseDAO systemCourseDAO;
    @Override
    public int insertSystemCourse(SystemCourse systemCourse) throws Exception {
        return systemCourseDAO.insertSystemCourse(systemCourse);
    }

    @Override
    public int updateSystemCourse(SystemCourse systemCourse) throws Exception {
        return systemCourseDAO.updateSystemCourse(systemCourse);
    }

    @Override
    public int deleteSystemCourse(Integer systemCourseID) throws Exception {
        return systemCourseDAO.deleteSystemCourse(systemCourseID);
    }

    @Override
    public SystemCourse getSystemCourseById(Integer systemCourseID) throws Exception {
        return systemCourseDAO.getSystemCourseById(systemCourseID);
    }
}
