package com.controllers;

import com.tia102g3.sportevent.service.SportEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private SportEventServiceImpl sportEventService;
    @GetMapping("/enter.do")
    public String customizedCourse(Model model) throws Exception {
        Set<String> sportEquipmentsSet = sportEventService.getSportEquipmentsSet();
        model.addAttribute("sportEquipmentSet", sportEquipmentsSet);
        return "trainers/customizedcourse";
    }
    @PostMapping("/soul")
    public String soul(Model model) throws Exception {

        return "customizedCourse/soul";
    }

}
