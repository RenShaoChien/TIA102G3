package com.tia102g3.coachcourse.service;

import com.tia102g3.coach_member.model.CoachMemberVO;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CourseStatus;

import java.util.List;

/**
 * ClassName： CoachCourseService
 * package：com.tia102g3.coachcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
public interface CoachCourseService {
    /**
     * 教練創建課程
     *
     * @param coachCourse
     * @return
     */
    int createCoachCourse(CoachCourse coachCourse) throws Exception;

    /**
     * 教練更新課程
     *
     * @param coachCourse
     * @return
     * @throws Exception
     */
    int updateCoachCourse(CoachCourse coachCourse) throws Exception;

    /**
     * 獲取課程
     * @param coachCourse
     * @return
     */
    CoachCourse getCourseById(CoachCourse coachCourse) throws Exception;

    /**
     * 列出所有课程
     * @return 所有课程的列表
     */
    List<CoachCourse> listAllCourses() throws Exception;

    /**
     * 列出當前教練的所有課程
     * @param cMember
     * @return
     */
    List<CoachCourse> listCoursesByCoach(CoachMemberVO cMember) throws Exception;

    /**
     * 更新課程狀態
     * @param coachCourse
     * @param status
     * @return
     * @throws Exception
     */
    CoachCourse updateCourseStatus(CoachCourse coachCourse, CourseStatus status) throws Exception;
}
