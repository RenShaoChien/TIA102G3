package com.controllers.admincontrollers;

import com.tia102g3.sportevent.model.SportEvent;
import com.tia102g3.sportevent.service.SportEventServiceImpl;
import com.tia102g3.systemcourse.model.SystemCourse;
import com.tia102g3.systemcourse.service.SystemCourseServiceImpl;
import com.tia102g3.systemcoursepic.service.SystemCoursePicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName： SystemCourseManagement
 * package：com.controllers.admincontrollers
 * Description：
 *
 * @Author 任少騫
 * @Create 2024/8/2 下午8:52
 * @Version 1.0
 */
@Controller
@RequestMapping("/course")
@Validated
public class SystemCourseManagement {
    @Autowired
    private SportEventServiceImpl sportEventService;
    @Autowired
    private SystemCourseServiceImpl systemCourseService;
    @Autowired
    private SystemCoursePicServiceImpl systemCoursePicService;


    @GetMapping("/enter")
    public String systemCourseManagement(Model model){
        return "frames/system_course_management";
    }

    @GetMapping("/systemCourseList"  )
    public String systemCourseList(Model model){
        return "frames/system_course_list";
    }

    @GetMapping("/addSystemCourse")
    public String addSystemCourse(ModelMap model) {
        SystemCourse systemCourse = new SystemCourse();
        model.addAttribute("systemCourse", systemCourse);
        return "frames/add_system_course";
    }

    @GetMapping("/coachCourseList")
    public String coachCourseList(Model model){
        return "frames/coach_course_list";
    }

    @GetMapping("/getSportEventsName")
    @ResponseBody
    public Set<SportEvent> getSportEvents(@RequestParam String sportType) throws Exception {
        return sportEventService.getSportEventsFromTypeSet(sportType);
    }

    @GetMapping("/getSportEquipment")
    @ResponseBody
    public Set<SportEvent> getSportEquipment(@RequestParam String sportType) throws Exception {
        return sportEventService.getSportEquipmentFromTypeSet(sportType);
    }


    @PostMapping("/addSystemCourse.do")
    public String addSystemCourse(ModelMap model, @Valid SystemCourse systemCourse, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "frames/add_system_course";
        }

        systemCourseService.insertSystemCourse(systemCourse);

        List<SystemCourse> systemCourses = systemCourseService.getAllSystemCoursesList();
        model.addAttribute("systemCourses", systemCourses);
        model.addAttribute("successMessage", "新增系統課程成功");

        return "redirect:/course/systemCourseList";
    }





    @ModelAttribute("sportEventsList")
    protected List<SportEvent> referenceSportEventsList() throws Exception {
        return sportEventService.getSportEventsList();
    }




    public BindingResult removeFieldError(Object targetObject, BindingResult result, String... removedFieldnames) {
        Set<String> removedFieldnamesSet = new HashSet<>(Arrays.asList(removedFieldnames));

        List<FieldError> errorsListToKeep = result.getFieldErrors().stream()
                .filter(fieldError -> !removedFieldnamesSet.contains(fieldError.getField()))
                .toList();

        result = new BeanPropertyBindingResult(targetObject, result.getObjectName());
        for (FieldError fieldError : errorsListToKeep) {
            result.addError(fieldError);
        }
        return result;
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ModelAndView handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException ex, Model model) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            strBuilder.append(violation.getMessage()).append("<br>");
        }

        String requestURI = req.getRequestURI();
        String viewName;
        if (requestURI.contains("/addSystemCourse")) {
            viewName = "frames/add_system_course";
        } else {
            viewName = "error/default_error_page";
        }

        model.addAttribute("errorMessage", "請修正以下錯誤:<br>" + strBuilder.toString());

        return new ModelAndView(viewName, model.asMap());
    }

}
