package com.tia102g3.coachcourse.service;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CoachCourseDAO;
import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import com.tia102g3.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * ClassName： CoachCourseServiceImpl
 * package：com.tia102g3.coachcourse.service
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/16 @{TIME}
 * @Version 1.0
 */
@Service
public class CoachCourseServiceImpl implements CoachCourseService {
    @Autowired
    private CoachCourseDAO ccDAO;
    @Autowired
    CoachCoursePicDAO ccpDAO;

//    @Override
//    @Transactional(readOnly = true)
//    public List<CoachCourse> getCoachCoursesList(String keyword, Pageable pageable) {
//        return ccDAO.getCoachCoursesList(keyword, pageable);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Long getCoachCourseCount(String keyword) {
//        return ccDAO.getCoachCourseCount(keyword);
//    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CoachCourse> findWithPicById(Integer coachCourseID) {
        return ccDAO.findWithPicById(coachCourseID);
    }

    @Override
    @Transactional(readOnly = true)
    public CoachCoursePic selectCoachCoursePicByID(Integer coachCoursePicID) {
        return ccpDAO.getReferenceById(coachCoursePicID);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CoachCourse> getCoachCoursesByStatusAndKeyword(String status, String keyword, Pageable pageable) {
        return ccDAO.findByStatusAndKeyword(CourseStatus.fromDescription(status), keyword, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long getCoachCourseCountByStatusAndKeyword(String status, String keyword) {
        return ccDAO.getCountByStatusAndKeyword(CourseStatus.fromDescription(status), keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMemberList(Integer currCoachCourseId) {
        return ccDAO.getMemberList(currCoachCourseId);
    }

    @Override
    @Transactional
    public boolean updateCourseStatus(Integer coachCourseId, CourseStatus newStatus) {
        return ccDAO.findById(coachCourseId)
                .map(course -> {
                    course.setStatus(newStatus); // 更新课程状态
                    ccDAO.save(course); // 保存更新后的课程
                    return true; // 返回成功标志
                })
                .orElse(false); // 如果未找到课程，返回false
    }

    @Override
    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateCourseStatusByDate() {
        LocalDate today = LocalDate.now();
        List<CoachCourse> courses = ccDAO.findAll();
        for (CoachCourse course : courses) {
            if (course.getCourseEndDate().toLocalDate().isBefore(today) && course.getStatus() != CourseStatus.fromDescription("已結束")) {
                course.setStatus(CourseStatus.fromDescription("已結束"));
                ccDAO.save(course);
            }
        }
    }
}
