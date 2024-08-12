package com.tia102g3.coachcourse.model;

import com.tia102g3.member.model.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

//    @Transactional(readOnly = true)
//    @Query(
//            value = "SELECT cc FROM CoachCourse cc " +
//                    "JOIN cc.cMember cm " +
//                    "WHERE " +
//                    "LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cm.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseLevel) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseStartDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseEndDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.noOfClasses) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.maxCap) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.coursePrice) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.classStartTime) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.classEndTime) LIKE LOWER(CONCAT('%', :keyword, '%'))"
//    )
//    List<CoachCourse> getCoachCoursesList(@Param("keyword") String keyword, Pageable pageable);
//
//    @Transactional(readOnly = true)
//    @Query(
//            "SELECT COUNT(cc) FROM CoachCourse cc " +
//                    "JOIN cc.cMember cm " +
//                    "WHERE " +
//                    "LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cm.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseLevel) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseStartDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.courseEndDate) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.noOfClasses) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.maxCap) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.status) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.coursePrice) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.classStartTime) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
//                    "LOWER(cc.classEndTime) LIKE LOWER(CONCAT('%', :keyword, '%'))"
//    )
//    Long getCoachCourseCount(@Param("keyword") String keyword);

    @Transactional(readOnly = true)
    @EntityGraph(attributePaths = {"coachCoursePics"})
    Optional<CoachCourse> findWithPicById(Integer coachCourseID);

    @Transactional(readOnly = true)
    @Query("SELECT cc FROM CoachCourse cc WHERE cc.status = :status AND " +
            "(LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.cMember.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    List<CoachCourse> findByStatusAndKeyword(CourseStatus status, String keyword, Pageable pageable);

    @Transactional(readOnly = true)
    @Query("SELECT COUNT(cc) FROM CoachCourse cc WHERE cc.status = :status AND " +
            "(LOWER(cc.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.cMember.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportEventName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportTypes) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cc.sportEquipment) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    long getCountByStatusAndKeyword(CourseStatus status, String keyword);

    @Transactional(readOnly = true)
    @Query("SELECT co.member FROM CourseOrder co WHERE co.coachCourse.id = :currCoachCourseId")
    List<Member> getMemberList(Integer currCoachCourseId);

    @Transactional(readOnly = true)
    @Query("SELECT cc FROM CoachCourse cc " +
            "LEFT JOIN FETCH cc.cMember cm " +
            "LEFT JOIN FETCH cc.coachCoursePics cp " +
            "WHERE cc.status = :courseStatus")
    Set<CoachCourse> findAllByStatus(CourseStatus courseStatus);
}
