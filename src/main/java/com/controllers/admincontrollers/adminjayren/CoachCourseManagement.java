package com.controllers.admincontrollers.adminjayren;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public String coachCourseList(String oper, String coachCoursesKeyword, Integer pageNo, HttpServletRequest req) {
        HttpSession session = req.getSession();

//        if (pageNo == null || pageNo < 1) {
//            pageNo = 1;
//        }
//
//        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
//            pageNo = 1;
//            if (StringUtil.isEmpty(coachCoursesKeyword)) {
//                coachCoursesKeyword = "";
//            }
//            session.setAttribute("coachCoursesKeyword", coachCoursesKeyword);
//        } else {
//            Object keywordObj = session.getAttribute("keyword");
//            if (keywordObj != null) {
//                coachCoursesKeyword = keywordObj.toString();
//            } else {
//                coachCoursesKeyword = "";
//            }
//        }
//
//        session.setAttribute("pageNo", pageNo);
//
//
//        Pageable pageable = PageRequest.of(pageNo - 1, 5);
//        List<CoachCourse> coachCourseList = ccService.getCoachCoursesList(coachCoursesKeyword, pageable);
//        session.setAttribute("coachCourseList", coachCourseList);
//
//        for (CoachCourse course : coachCourseList) {
//            System.out.println("Course Name: " + course.getCourseName());
//            CoachMember member = course.getCMember();
//            if (member != null) {
//                System.out.println("Coach Name: " + member.getName());
//            } else {
//                System.out.println("No Coach Member associated.");
//            }
//        }
//        Long totalRecords = ccService.getCoachCourseCount(coachCoursesKeyword);
//        int pageCount = (int) Math.ceil((double) totalRecords / 5);
//        session.setAttribute("pageCount", pageCount);
/////////////////////////////////////////////////////////////////////////////////////////////////////
//        if (StringUtil.isNotEmpty(oper) && oper.equals("search")) {
//            if (StringUtil.isEmpty(coachCoursesKeyword)) {
//                coachCoursesKeyword = "";
//            }
//            session.setAttribute("coachCoursesKeyword", coachCoursesKeyword);
//        } else {
//            Object keywordObj = session.getAttribute("keyword");
//            if (keywordObj != null) {
//                coachCoursesKeyword = keywordObj.toString();
//            } else {
//                coachCoursesKeyword = "";
//            }
//        }
        return "frames/coach_course_list";
    }

    @GetMapping("/currCoachCourse")
    public String currCoachCourse(@RequestParam Integer coachCourseID, ModelMap model){
        ccService.findWithPicById(coachCourseID).ifPresent(course -> model.put("course", course));

        return "frames/curr_coach_course";
    }

    @GetMapping("/filterCourses")
    @ResponseBody
    public List<CoachCourse> filterCourses(@RequestParam String status,
                                           @RequestParam(required = false) String keyword,
                                           @RequestParam(defaultValue = "1") int pageNo,
                                           @RequestParam(defaultValue = "5") int pageSize) {
        if (keyword == null) {
            keyword = "";
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return ccService.getCoachCoursesByStatusAndKeyword(status, keyword, pageable);
    }

}
