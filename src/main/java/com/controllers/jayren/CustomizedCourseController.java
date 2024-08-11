package com.controllers.jayren;

import com.tia102g3.sportevent.service.SportEventServiceImpl;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.service.SystemCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * ClassName： CustomizedCourseController
 * package：com.tia102g3.customizedcourse.controller
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/7/23 @{TIME}
 * @Version 1.0
 */
@Controller
@RequestMapping("/trainers")
public class CustomizedCourseController {

    @Autowired
    private SportEventServiceImpl seService;
    @Autowired
    private SystemCourseServiceImpl scService;

    @GetMapping("/enter.do")
    public String customizedCourse() {
        return "trainers/customizedcourse";
    }


    @PostMapping("/customized.do")
    public String getCustomizedCourse(
            @RequestParam("sportTypes") String sportTypes, @RequestParam("sportEventName") String sportEventName,
            @RequestParam("sportEquipment") String sportEquipment, @RequestParam("target-area") String keyword,
            @RequestParam("courseLevel") String courseLevel, @RequestParam("loseWeight") String loseWeight) {

        List<SystemCourse> customizedCourses = scService.getSystemCoursesByReqPara(sportTypes, sportEventName, sportEquipment, keyword, courseLevel);

        return "trainers/customizedresult";
    }

    @GetMapping("/getEquipmentBySportEvent")
    @ResponseBody
    public Set<String> getEquipmentBySportEvent(@RequestParam String sportEventName) {
        return seService.getEquipmentBySportEvent(sportEventName);
    }
}
