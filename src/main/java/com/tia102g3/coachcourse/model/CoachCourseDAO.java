package com.tia102g3.coachcourse.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName： CoachCourseDAO
 * package：com.tia102g3.coachcourse.model
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
public interface CoachCourseDAO extends JpaRepository<CoachCourse, Integer> {

    @Transactional(readOnly = true)
    @Query(
            value = "SELECT cc FROM CoachCourse cc " +
                    "JOIN cc.cMember cm " +
                    "WHERE " +
                    "LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cm.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseLevel) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseStartDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseEndDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.noOfClasses) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.maxCap) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.coursePrice) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.classStartTime) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.classEndTime) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    List<CoachCourse> getCoachCoursesList(@Param("keyword") String keyword, Pageable pageable);

    @Transactional(readOnly = true)
    @Query(
            "SELECT COUNT(cc) FROM CoachCourse cc " +
                    "JOIN cc.cMember cm " +
                    "WHERE " +
                    "LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cm.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseLevel) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseStartDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.courseEndDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.noOfClasses) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.maxCap) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.coursePrice) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.classStartTime) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                    "LOWER(cc.classEndTime) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    Long getCoachCourseCount(@Param("keyword") String keyword);
}
