package com.controllers.david.courseorder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.service.CourseOrderService;

@Controller
public class CourseOrderPageController {

	@Autowired
	CourseOrderService courseOrderSvc;
	
	@GetMapping("/course_order/select_page")
	public String select_page1(Model model) {
		return "back-end/course_order/select_page";
	}

	@GetMapping("/course_order/listAllCourseOrder")
	public String listAllCourseOrder(Model model) {
		return "back-end/course_order/listAllCourseOrder";
	}

	@ModelAttribute("courseOrderListData") // for select_page.html 第97 109行用 // for listAllMember.html 第85行用
	protected List<CourseOrder> referenceListData1(Model model) {

		List<CourseOrder> list = courseOrderSvc.getAll();
		return list;
	}
}
