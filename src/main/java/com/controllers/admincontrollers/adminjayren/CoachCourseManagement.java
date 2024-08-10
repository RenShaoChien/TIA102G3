package com.controllers.admincontrollers.adminjayren;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.email.EmailServiceImpl;
import com.tia102g3.member.model.Member;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    CoachCourseServiceImpl ccService;
    @Autowired
    private EmailServiceImpl emailService;


    @RequestMapping(value = "/coachCourseList", method = {RequestMethod.GET, RequestMethod.POST})
    public String coachCourseList(Model model) {
        return "frames/coach_course_list";
    }

    @GetMapping("/currCoachCourse")
    public String currCoachCourse(@RequestParam Integer coachCourseID, ModelMap model) {
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
    public List<Member> getMemberList(@RequestParam("id") Integer currCoachCourseId) {
        System.out.println("currCoachCourseId = " + currCoachCourseId);
        if (currCoachCourseId != null) {
            return ccService.getMemberList(currCoachCourseId);
        }
        return Collections.emptyList();
    }

    @PostMapping("/approve")
    @ResponseBody
    public ResponseEntity<String> approveCourse(@RequestParam("id") Integer coachCourseId) {
        if (coachCourseId == null) {
            return ResponseEntity.badRequest().body("沒有課程id");
        }
        boolean result = ccService.updateCourseStatus(coachCourseId, CourseStatus.IN_PROGRESS);
        if (result) {
            return ResponseEntity.ok("審核成功");
        } else {
            return ResponseEntity.status(500).body("審核失敗");
        }
    }

    @PostMapping("/sendEmail")
    @ResponseBody
    public String  sendEmail(@RequestParam("emailTo") String emailTo,
                                                         @RequestParam("emailSubject") String emailSubject,
                                                         @RequestParam("emailBody") String emailBody) {
        try {
            emailService.sendEmail(emailTo, emailSubject, emailBody);
            return "郵件已成功發送！";
        } catch (Exception e) {
            logger.error("發送郵件時出現錯誤", e);
            return "發送郵件失敗！";
        }
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
