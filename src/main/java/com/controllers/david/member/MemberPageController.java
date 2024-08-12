package com.controllers.david.member;

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
	
	// 前台會員註冊、登入頁面Controller
	@GetMapping("login")
    public String login(Model model) {
        return "frontend/login/login";
    }
	
	@GetMapping("register")
    public String register(Model model) {
        return "frontend/login/register";
    }
	
	@GetMapping("verify_email")
	public String verify_email() {
	    return "frontend/login/verify_email"; 
	}
	
	@GetMapping("complete_page")
	public String complete_page() {
	    return "frontend/login/complete_page"; 
	}
	
	// 前台忘記密碼頁面Controller
	@GetMapping("password_verify_email")
    public String password_verify_email(Model model) {
        return "frontend/login/password_verify_email";
    }
	
	@GetMapping("modify_forgotten_password")
	public String modify_forgotten_password(Model model) {
		return "frontend/login/modify_forgotten_password";
	}
	
	@GetMapping("password_complete_page")
	public String password_complete_page(Model model) {
		return "frontend/login/password_complete_page";
	}
	
	// 前台會員頁面Controller
	@GetMapping("account_information")
    public String account_information(Model model) {
        return "frontend/member/account_information";
    }
	
	@GetMapping("change_password")
    public String change_password(Model model) {
        return "frontend/member/change_password";
    }
	
	@GetMapping("historical_orders")
	public String historical_orders(Model model) {
		return "frontend/member/historical_orders";
	}
	
	@GetMapping("receipt_information")
	public String receipt_information(Model model) {
		return "frontend/member/receipt_information";
	}
	
	@GetMapping("acc_information")
	public String acc_information(Model model) {
		return "frontend/member/acc_information";
	}
}