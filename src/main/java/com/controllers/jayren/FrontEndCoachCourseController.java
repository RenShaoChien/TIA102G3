package com.controllers.jayren;

import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

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

        return "product/order_checking";
    }
}
