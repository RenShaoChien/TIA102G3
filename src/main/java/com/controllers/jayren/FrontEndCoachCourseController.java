package com.controllers.jayren;

import com.tia102g3.coachcourse.model.CoachCourse;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * ClassName： FrontEndCoachCourseController
 * package：com.controllers.jayren
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/13 下午2:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/trainers")
public class FrontEndCoachCourseController {

    @Autowired
    CoachCourseServiceImpl ccService;

    @GetMapping("/currCoachCourse")
    public String currCoachCourse(@RequestParam("id") Integer courseID, ModelMap model) {
        ccService.findOneAllAttr(courseID).ifPresent(course -> model.put("currCourse", course));
        return "trainers/coachCourse";
    }

    @RequestMapping(value = "/orderCoachCourse", method = {RequestMethod.GET, RequestMethod.POST})
    public String orderCoachCourse(@RequestParam("coachCourseID") Integer courseID, ModelMap model, HttpSession session) {
        Object memberObj = session.getAttribute("user");
        if (memberObj == null) {
            session.setAttribute("pendingCourseID", courseID);
            return "redirect:/login";
        }
        Member member = (Member) memberObj;

        model.putAll(Map.of("member", member, "coachCourseID", courseID));

        return "trainers/course_order_checking";
    }

    @PostMapping("/orderCoachCourse.do")
    @ResponseBody
    public ResponseEntity orderCoachCourse(@RequestParam("coachCourseID") Integer courseID) {
        if (courseID != null) {
            CoachCourse orderCoachCourse = ccService.getOneOrderCoachCourse(courseID);
            return ResponseEntity.ok(orderCoachCourse);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Coach Course Found in Request Scope");
        }
    }
}
