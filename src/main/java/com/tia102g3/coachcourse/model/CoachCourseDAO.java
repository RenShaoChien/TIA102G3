package com.tia102g3.coachcourse.model;

import com.tia102g3.sportevent.model.SportEvent;

import java.sql.Date;
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
public interface CoachCourseDAO {
    /**
     * 教練新增課程
     * @param coachCourse
     * @return
     * @throws Exception
     */
    int insertCoachCourse(CoachCourse coachCourse) throws Exception;

    /**
     * 教練更新課程
     * @param coachCourse
     */
    int updateCoachCourse(CoachCourse coachCourse) throws Exception;

    /**
     * 獲取課程
     * @param coachCourseID
     * @return CoachCourse
     */
    CoachCourse getCoachCourseByID(Integer coachCourseID) throws Exception;

    /**
     * 刪除課程
     * @param coachCourseID
     */
    int deleteCoachCourseByID(Integer coachCourseID) throws Exception;

    /**
     * 獲取所有教練課程List
     * @return
     */
    List<CoachCourse> getAllCoachCoursesList() throws Exception;

    /**
     * 根據教練來獲取其所有課程List
     * @param cMember
     * @return
     */
    List<CoachCourse> findCoursesByCMember(CMember cMember) throws Exception;

    /**
     * 根據課程等級來獲取課程List
     * @param courseLevel
     * @return
     */
    List<CoachCourse> findCoursesByLevel(Integer courseLevel) throws Exception;

    /**
     * 根據運動項目獲取課程List
     * @param sportEvent
     * @return
     */
    List<CoachCourse> findCoursesBySportEvent(SportEvent sportEvent) throws Exception;

    /**
     * 查詢從指定日期開始的即將開設的課程List
     * @param startDate
     * @return
     */
    List<CoachCourse> findCoursesByDate(Date startDate) throws Exception;

    /**
     * 查詢在當前日期活動中的所有課程List
     * @param currentDate
     * @return
     */
    List<CoachCourse> findActiveCourses(Date currentDate) throws Exception;

    /**
     * 更新指定課程的狀態
     * @param coachCourse
     * @param status
     * @return
     * @throws Exception
     */
    int updateCourseStatus(CoachCourse coachCourse, CourseStatus status) throws Exception;
}
