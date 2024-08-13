package com.controllers.david.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
	public String account_information(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            Integer memberID = member.getMemberID();
            Member memberDetails = memberSvc.findById(memberID);
            
            if (memberDetails != null) {
                model.addAttribute("name", memberDetails.getName());
            }
        }
        return "frontend/member/account_information";
    }
	
	@GetMapping("change_password")
	public String change_password(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            Integer memberID = member.getMemberID();
            Member memberDetails = memberSvc.findById(memberID);
            
            if (memberDetails != null) {
                model.addAttribute("name", memberDetails.getName());
            }
        }
        return "frontend/member/change_password";
    }
	
	@GetMapping("historical_orders")
	public String historical_orders(Model model) {
		return "frontend/member/historical_orders";
	}
	
	@GetMapping("receipt_information")
	public String receipt_information(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            Integer memberID = member.getMemberID();
            Member memberDetails = memberSvc.findById(memberID);
            
            if (memberDetails != null) {
                model.addAttribute("name", memberDetails.getName());
            }
        }
		return "frontend/member/receipt_information";
	}
	
	@GetMapping("acc_information")
	public String acc_information(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("user");

        if (member != null) {
            Integer memberID = member.getMemberID();
            Member memberDetails = memberSvc.findById(memberID);
            
            if (memberDetails != null) {
                model.addAttribute("name", memberDetails.getName());
            }
        }
		return "frontend/member/acc_information";
	}
}