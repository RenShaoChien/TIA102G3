package com.controllers.jayren;

import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/orderCoachCourse")
    public String orderCoachCourse(@RequestParam(value = "memberID", required = false, defaultValue = "0") Integer memberID, @RequestParam("coachCourseID") Integer courseID, ModelMap model) {

        return "product/order_checking";
    }
}
