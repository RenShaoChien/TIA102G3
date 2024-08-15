package com.controllers.jayren;

import com.tia102g3.sportevent.service.SportEventServiceImpl;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.service.SystemCourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Random;
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
    public String customizedCourse(ModelMap model) {


        return "trainers/customizedcourse";
    }


    @PostMapping("/customized.do")
    public String getCustomizedCourse(Model model,
                                      @RequestParam("sportTypes") String sportTypes, @RequestParam("sportEventName") String sportEventName,
                                      @RequestParam("sportEquipment") String sportEquipment, @RequestParam("target-area") String keyword,
                                      @RequestParam("courseLevel") String courseLevel, @RequestParam("loseWeight") String loseWeight,
                                      RedirectAttributes redirectAttributes) {
        try {
            Random rd = new Random();
            SystemCourse randomCourse;
            List<SystemCourse> customizedCourses = scService.getSystemCoursesByReqPara(sportTypes, sportEventName, sportEquipment, keyword, courseLevel);
            if (customizedCourses.size() > 0){
                randomCourse = customizedCourses.get(rd.nextInt(customizedCourses.size()));
                model.addAttribute("systemCourse", randomCourse);

            }else
                throw new IllegalArgumentException("customizedCourses.size() < 0");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "目前沒有合適您的運動");
            return "redirect:/trainers/enter.do";
        }
        return "trainers/customizedresult";
    }

    @GetMapping("/getEquipmentBySportEvent")
    @ResponseBody
    public Set<String> getEquipmentBySportEvent(@RequestParam String sportEventName) {
        return seService.getEquipmentBySportEvent(sportEventName);
    }

}
