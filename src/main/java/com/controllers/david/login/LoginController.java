package com.controllers.david.login;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.model.CoachMemberRepository;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;
import com.tia102g3.member.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private CoachMemberRepository coachMemberRepository;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CoachMemberService coachMemberService;

	@PostMapping("/login")
	public RedirectView login(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request,
			HttpServletResponse response) {
		// 根據帳號查詢會員
		Member member = memberRepository.findByAccount(username);
		CoachMember coachMember = coachMemberRepository.findByAccount(username);

		// 檢查密碼並確定用戶身份
		if (member != null && member.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", member); // 設置會員到 session
			session.setAttribute("loggedIn", true); // 設置登入狀態

			Cookie loginCookie = new Cookie("user", username);
			loginCookie.setMaxAge(1 * 24 * 60 * 60); // 有效期為 1 天
			loginCookie.setPath("/");
			response.addCookie(loginCookie);

			if (session.getAttribute("pendingCourseID") != null) {
				String pendingCourseID = session.getAttribute("pendingCourseID").toString();
				int coachCourseID = Integer.parseInt(pendingCourseID);
				session.removeAttribute("pendingCourseID");
				return new RedirectView("/trainers/orderCoachCourse?coachCourseID=" + coachCourseID);
			}
			return new RedirectView("/account_information"); // 一般會員頁面
		} else if (coachMember != null && coachMember.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", coachMember); // 設置教練會員到 session
			session.setAttribute("loggedIn", true); // 設置登入狀態

			Cookie loginCookie = new Cookie("user", username);
			loginCookie.setMaxAge(1 * 24 * 60 * 60); // 有效期為 1 天
			loginCookie.setPath("/");
			response.addCookie(loginCookie);

			return new RedirectView("/coach_account_information"); // 教練會員頁面
		}

		// 如果身份驗證失敗，返回登入頁面，並帶上錯誤參數
		return new RedirectView("/login?error=invalid");
	}

	@GetMapping("/loggedIn")
	public String showLoginPage(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    if (session != null && Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
	        // 會員之前已經登錄，直接跳轉畫面
	        Object user = session.getAttribute("user");
	        if (user instanceof Member) {
	            return "redirect:/account_information";
	        } else if (user instanceof CoachMember) {
	            return "redirect:/coach_account_information";
	        }
	    }
	    return "frontend/login/login"; // 返回登入頁面
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if (session != null) {
	        session.invalidate(); // 清除 session
	    }
	    return "redirect:/login?error=loggedOut"; // 帶上錯誤參數
	}
	
	@GetMapping("/somePage")
	public String somePage(Model model, HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    boolean loggedIn = session != null && Boolean.TRUE.equals(session.getAttribute("loggedIn"));
	    model.addAttribute("loggedIn", loggedIn);
	    return "somePage"; // Thymeleaf 模板名稱
	}

	@PostMapping("/register")
	public String register(@RequestParam("account") String account, @RequestParam("password") String password,
			@RequestParam("name") String name, @RequestParam("phone") String phone,
			@RequestParam("address") String address, @RequestParam("membership") String membership, HttpSession session,
			Model model) {

		session.setAttribute("account", account);

		if ("general".equals(membership)) {
			Member member = new Member();
			member.setAccount(account);
			member.setPassword(password);
			member.setName(name);
			member.setPhone(phone);
			member.setAddress(address);

			memberService.addMember(member);

			// 儲存 memberID 到 Session
			session.setAttribute("memberID", member.getMemberID());

			return "redirect:/verify_email";

		} else if ("coach".equals(membership)) {
			CoachMember coachMember = new CoachMember();
			coachMember.setAccount(account);
			coachMember.setPassword(password);
			coachMember.setName(name);
			coachMember.setPhone(phone);
			coachMember.setAddress(address);

			coachMemberService.addCoachMember(coachMember);

			// 儲存 memberID 到 Session
			session.setAttribute("cMemberID", coachMember.getCMemberID());

			return "redirect:/coach_verify_email";
		}

		return "redirect:/register?error=true";
	}

	@GetMapping("/check-account")
	public ResponseEntity<Boolean> checkAccount(@RequestParam("account") String account) {
		boolean exists = memberService.existsByAccount(account) || coachMemberService.existsByAccount(account);
		return ResponseEntity.ok(exists);
	}

	@PostMapping("/saveEmail")
	public String saveEmail(@RequestParam("email") String email, HttpSession session) {

		// 從 Session 中獲取 memberID 和 cMemberID
		Integer memberID = (Integer) session.getAttribute("memberID");
		Integer cMemberID = (Integer) session.getAttribute("cMemberID");

		// 處理一般會員
		if (memberID != null) {
			// 根據 memberID 查詢會員
			Member member = memberService.findById(memberID);

			if (member != null) {
				// 更新會員的電子郵件
				member.setEmail(email);
				memberService.updateMember(member);

				return "redirect:/complete_page";
			}
		}

		// 處理教練會員
		if (cMemberID != null) {
			// 根據 cMemberID 查詢教練會員
			CoachMember coachMember = coachMemberService.findById(cMemberID);

			if (coachMember != null) {
				// 更新教練會員的電子郵件
				coachMember.setEmail(email);
				coachMemberService.updateCoachMember(coachMember);

				return "redirect:/coach_skill";
			}
		}

		// 錯誤處理
		return "redirect:/verify_email?error=true";
	}
}
