package com.tia102g3.coachcourse.service;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CoachCourseDAO;
import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcoursepic.model.CoachCoursePic;
import com.tia102g3.coachcoursepic.model.CoachCoursePicDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
