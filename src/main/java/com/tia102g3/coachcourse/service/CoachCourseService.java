package com.tia102g3.coachcourse.service;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.member.model.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

//    List<CoachCourse> getCoachCoursesList(String keyword, Pageable pageable);
//
//    Long getCoachCourseCount(String keyword);

    Optional<CoachCourse> findWithPicById(Integer coachCourseID);

    CoachCoursePic selectCoachCoursePicByID(Integer coachCoursePicID);

    List<CoachCourse> getCoachCoursesByStatusAndKeyword(String status, String keyword, Pageable pageable);

    long getCoachCourseCountByStatusAndKeyword(String status, String keyword);

    List<Member> getMemberList(Integer currCoachCourseId);

    boolean updateCourseStatus(Integer coachCourseId, CourseStatus courseStatus);

    void updateCourseStatusByDate();
}
