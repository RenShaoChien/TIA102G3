package com.tia102g3.systemcoursepic.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName： SystemCoursePicDAO
 * package：com.tia102g3.systemcoursepic.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/17 @{TIME}
 * @Version 1.0
 */
public interface SystemCoursePicDAO extends JpaRepository<SystemCoursePic, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from system_course_pic where systemCourseID = ?1", nativeQuery = true)
    void delBySystemCourseID(Integer systemCourseID);
}
