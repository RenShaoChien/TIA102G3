package com.tia102g3.coachcourse.service;

import com.tia102g3.coachcourse.model.CoachCourse;
import org.springframework.data.domain.Pageable;

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

    List<CoachCourse> getCoachCoursesList(String keyword, Pageable pageable);

    Long getCoachCourseCount(String keyword);
}
