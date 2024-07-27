package com.tia102g3.coachcourse.service;

import com.tia102g3.coach_member.model.CoachMemberVO;
import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CoachCourseDAO;
import com.tia102g3.coachcourse.model.CourseStatus;

import java.util.List;

/**
 * ClassName： CoachCourseServiceImpl
 * package：com.tia102g3.coachcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
public class CoachCourseServiceImpl implements CoachCourseService {
    private CoachCourseDAO coachCourseDAO;

    @Override
    public int createCoachCourse(CoachCourse coachCourse) throws Exception {
        return coachCourseDAO.insertCoachCourse(coachCourse);
    }

    @Override
    public int updateCoachCourse(CoachCourse coachCourse) throws Exception {
        return coachCourseDAO.updateCoachCourse(coachCourse);
    }

    @Override
    public CoachCourse getCourseById(CoachCourse coachCourse) throws Exception {
        return coachCourseDAO.getCoachCourseByID(coachCourse.getCoachCourseID());
    }

    @Override
    public List<CoachCourse> listAllCourses() throws Exception {
        return coachCourseDAO.getAllCoachCoursesList();
    }

    @Override
    public List<CoachCourse> listCoursesByCoach(CoachMemberVO cMember) throws Exception {
        return coachCourseDAO.findCoursesByCMember(cMember);
    }

    @Override
    public CoachCourse updateCourseStatus(CoachCourse coachCourse, CourseStatus status) throws Exception {
        coachCourseDAO.updateCourseStatus(coachCourse, status);
        return coachCourse;
    }
}
