package com.controllers.admincontrollers.adminjayren;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.member.model.Member;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * ClassName： CoachCourseManagement
 * package：com.controllers.admincontrollers
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/8 上午5:22
 * @Version 1.0
 */
@Controller
@RequestMapping("/course")
@Validated
public class CoachCourseManagement {

    @Autowired
    CoachCourseServiceImpl ccService;


    @RequestMapping(value = "/coachCourseList", method = {RequestMethod.GET, RequestMethod.POST})
    public String coachCourseList(Model model) {
        return "frames/coach_course_list";
    }

    @GetMapping("/currCoachCourse")
    public String currCoachCourse(@RequestParam Integer coachCourseID, ModelMap model){
        ccService.findWithPicById(coachCourseID).ifPresent(course -> model.put("course", course));

        return "frames/curr_coach_course";
    }

    @GetMapping("/filterCourses")
    @ResponseBody
    public CoursePageDTO filterCourses(@RequestParam String status,
                                           @RequestParam(required = false) String keyword,
                                           @RequestParam(defaultValue = "1") int pageNo,
                                           @RequestParam(defaultValue = "5") int pageSize) {
        if (keyword == null) {
            keyword = "";
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<CoachCourse> courses = ccService.getCoachCoursesByStatusAndKeyword(status, keyword, pageable);

        long totalRecords = ccService.getCoachCourseCountByStatusAndKeyword(status, keyword);
        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        CoursePageDTO coursePageDTO = new CoursePageDTO();
        coursePageDTO.setCourses(courses);
        coursePageDTO.setTotalRecords(totalRecords);
        coursePageDTO.setTotalPages(totalPages);

        return coursePageDTO;
    }

    @PostMapping("/getMemberList")
    @ResponseBody
    public List<Member> getMemberList(@RequestParam("id") Integer currCoachCourseId){
        System.out.println("currCoachCourseId = " + currCoachCourseId);
        if (currCoachCourseId != null){
            return ccService.getMemberList(currCoachCourseId);
        }
        return Collections.emptyList();
    }


    @Data
    public static class CoursePageDTO {
        private List<CoachCourse> courses;
        private long totalRecords;
        private int totalPages;

        @Override
        public String toString() {
            return "CoursePageDTO{" +
                    "totalRecords=" + totalRecords +
                    ", totalPages=" + totalPages +
                    ", courses=" + (courses != null ? courses.size() : "null") +
                    '}';
        }
    }

}
