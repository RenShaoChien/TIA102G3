package com.controllers.david.coachmember;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.service.CoachMemberService;

@Controller
public class CoachMemberPageController {

	@Autowired
	CoachMemberService coachMemberSvc;

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

	// 前台教練會員註冊、登入頁面Controller
	@GetMapping("coach_verify_email")
	public String coach_verify_email() {
		return "frontend/coach_member/coach_verify_email";
	}
	
	@GetMapping("coach_skill")
	public String coach_skill() {
		return "frontend/coach_member/coach_skill";
	}
	
	@GetMapping("coach_complete_page")
	public String coach_complete_page() {
		return "frontend/coach_member/coach_complete_page";
	}
	
	// 前台教練會員頁面Controller
	@GetMapping("/coach_account_information")
	public String coachAccountInformation(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    
	    // 檢查是否登入
	    if (session == null || !Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
	        return "redirect:/login?error=not_logged_in"; // 重定向到登入頁面，帶上錯誤參數
	    }
	    
	    // 獲取 CoachMember 物件
	    CoachMember coachMember = (CoachMember) session.getAttribute("user");
	    
	    if (coachMember != null) {
	        Integer cMemberID = coachMember.getCMemberID();
	        CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
	        
	        if (coachMemberDetails != null) {
	            model.addAttribute("name", coachMemberDetails.getName()); // 添加 name 屬性到模型中
	        }
	    }

	    return "frontend/coach_member/coach_account_information"; // 返回對應的視圖名稱
	}
	
	@GetMapping("coach_account_skill")
	public String coach_account_skill(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("user");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
                model.addAttribute("name", coachMemberDetails.getName());
            }
        }
		return "frontend/coach_member/coach_account_skill";
	}
	
	@GetMapping("coach_change_password")
	public String coach_change_password(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("user");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
                model.addAttribute("name", coachMemberDetails.getName());
            }
        }
		return "frontend/coach_member/coach_change_password";
	}
	
	@GetMapping("coach_historical_orders")
	public String coach_historical_orders() {
		return "frontend/coach_member/coach_historical_orders";
	}
	
	@GetMapping("coach_receipt_information")
	public String coach_receipt_information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("user");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
                model.addAttribute("name", coachMemberDetails.getName());
            }
        }
		return "frontend/coach_member/coach_receipt_information";
	}
	
	@GetMapping("coach_acc_information")
	public String coach_acc_information(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        CoachMember coachMember = (CoachMember) session.getAttribute("user");

        if (coachMember != null) {
            Integer cMemberID = coachMember.getCMemberID();
            CoachMember coachMemberDetails = coachMemberSvc.findById(cMemberID);
            
            if (coachMemberDetails != null) {
                model.addAttribute("name", coachMemberDetails.getName());
            }
        }
		return "frontend/coach_member/coach_acc_information";
	}
	
	@GetMapping("coach_course_status")
	public String coach_course_status() {
		return "frontend/coach_member/coach_course_status";
	}
	
	@GetMapping("coach_course_products")
	public String coach_course_products() {
		return "frontend/coach_member/coach_course_products";
	}
}
