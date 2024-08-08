package com.tia102g3.coachcourse.service;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CoachCourseDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
public class CoachCourseServiceImpl implements CoachCourseService {
    @Autowired
    private CoachCourseDAO ccDAO;

    @Override
    @Transactional(readOnly = true)
    public List<CoachCourse> getCoachCoursesList(String keyword, Pageable pageable) {
        return ccDAO.getCoachCoursesList(keyword, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCoachCourseCount(String keyword) {
        return ccDAO.getCoachCourseCount(keyword);
    }

}
