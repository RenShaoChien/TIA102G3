package com.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.courseorder.model.CourseOrder;
import com.tia102g3.courseorder.service.CourseOrderService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.service.MemberService;

@Controller
public class MemberPageController {

	@Autowired
	MemberService memberSvc;

	@Autowired
	CourseOrderService courseOrderSvc;
	
	@Autowired
	CoachMemberService coachMemberSvc;
	
	@GetMapping("/member/member_courseOrder_index")
	public String member_courseOrder_index(Model model) {
		return "member_courseOrder_index";
	}

	@GetMapping("/member/select_page")
	public String select_page(Model model) {
		return "back-end/member/select_page";
	}

	@GetMapping("/member/listAllMember")
	public String listAllMember(Model model) {
		return "back-end/member/listAllMember";
	}

	@ModelAttribute("memberListData") // for select_page.html 第97 109行用 // for listAllMember.html 第85行用
	protected List<Member> referenceListData(Model model) {

		List<Member> list = memberSvc.getAll();
		return list;
	}

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

//	@ModelAttribute("deptListData") // for select_page.html 第135行用
//	protected List<DeptVO> referenceListData_Dept(Model model) {
//		model.addAttribute("deptVO", new DeptVO()); // for select_page.html 第133行用
//		List<DeptVO> list = deptSvc.getAll();
//		return list;
//	}
	
	@GetMapping("/coach_member/coachHome_page")
	public String coachHome_page(Model model) {
		return "back-end/coach_member/coachHome_page";
	}

	@GetMapping("/coach_member/listAllCoachMember")
	public String listAllCoachMember(Model model) {
		return "back-end/coach_member/listAllCoachMember";
	}

	@ModelAttribute("coachMemberListData") // for select_page.html 第97 109行用 // for listAllMember.html 第85行用
	protected List<CoachMember> referenceListData2(Model model) {

		List<CoachMember> list = coachMemberSvc.getAll();
		return list;
	}
}