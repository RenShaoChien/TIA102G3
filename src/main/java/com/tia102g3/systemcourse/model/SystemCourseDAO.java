package com.tia102g3.systemcourse.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
public interface SystemCourseDAO extends JpaRepository<SystemCourse, Integer> {

    @Transactional
    @Modifying
    @Query(value = "delete from system_course where systemCourseID = ?1", nativeQuery = true)
    int deleteSystemCourse(Integer systemCourseID);

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT * FROM system_course WHERE " +
                    "courseName LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportEventName LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportTypes LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportEquipment LIKE CONCAT('%', :keyword, '%') OR " +
                    "courseLevel LIKE CONCAT('%', :keyword, '%') OR " +
                    "burnCalories LIKE CONCAT('%', :keyword, '%') OR " +
                    "rps LIKE CONCAT('%', :keyword, '%') OR " +
                    "eachExerciseTime LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportTime LIKE CONCAT('%', :keyword, '%') OR " +
                    "swp LIKE CONCAT('%', :keyword, '%') OR " +
                    "illustrate LIKE CONCAT('%', :keyword, '%') " +
                    "LIMIT :offset, 5",
            nativeQuery = true)
    List<Object[]> getSystemCoursesList(@Param("keyword") String keyword, Integer offset);

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT count(*) FROM system_course WHERE " +
                    "courseName LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportEventName LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportTypes LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportEquipment LIKE CONCAT('%', :keyword, '%') OR " +
                    "courseLevel LIKE CONCAT('%', :keyword, '%') OR " +
                    "burnCalories LIKE CONCAT('%', :keyword, '%') OR " +
                    "rps LIKE CONCAT('%', :keyword, '%') OR " +
                    "eachExerciseTime LIKE CONCAT('%', :keyword, '%') OR " +
                    "sportTime LIKE CONCAT('%', :keyword, '%') OR " +
                    "swp LIKE CONCAT('%', :keyword, '%') OR " +
                    "illustrate LIKE CONCAT('%', :keyword, '%') ",
            nativeQuery = true)
    Long getSystemCourseCount(@Param("keyword") String keyword);
}
