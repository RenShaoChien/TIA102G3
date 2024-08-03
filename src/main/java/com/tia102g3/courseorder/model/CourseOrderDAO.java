package com.tia102g3.courseorder.model;

import java.util.List;

/**
 * ClassName： CourseOrderDAO
 * package：com.tia102g3.courseorder.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/27 下午10:20
 * @Version 1.0
 */
public interface CourseOrderDAO {
    int insert(CourseOrder courseOrder) throws Exception;
    int update(CourseOrder courseOrder) throws Exception;
    int delete(CourseOrder courseOrder) throws Exception;
    CourseOrder getCourseOrderByID(Integer courseOrderID) throws Exception;
    List<CourseOrder> getCourseOrdersList() throws Exception;
}
