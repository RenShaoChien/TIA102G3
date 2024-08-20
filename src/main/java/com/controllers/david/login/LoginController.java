package com.controllers.david.login;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.tia102g3.adminlogin.model.AdminLogin;
import com.tia102g3.adminlogin.model.AdminLoginRepository;
import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.model.CoachMemberRepository;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.email.EmailServiceImpl;
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
	private AdminLoginRepository adminLoginRepository;

	@Autowired
	private MemberService memberService;

	@Autowired
	private CoachMemberService coachMemberService;
	
	@Autowired
	private EmailServiceImpl emailService;

	@PostMapping("/login")
	public RedirectView login(@RequestParam String username, @RequestParam String password,
	        HttpServletRequest request, HttpServletResponse response) {

	    // 尝试从数据库中获取不同类型的用户
	    Member member = memberRepository.findByAccount(username);
	    CoachMember coachMember = coachMemberRepository.findByAccount(username);
	    AdminLogin adminLogin = adminLoginRepository.findByAdminUsername(username);

	    HttpSession session = request.getSession();

	    if (member != null && member.getPassword().equals(password)) {
	        session.setAttribute("user", member);
	        session.setAttribute("loggedIn", true);
	        addLoginCookie(response, username);

	        if (session.getAttribute("pendingCourseID") != null) {
	            return handlePendingCourse(session);
	        }
	        return new RedirectView("/account_information");
	    } 

	    else if (coachMember != null && coachMember.getPassword().equals(password)) {
	        session.setAttribute("user", coachMember);
	        session.setAttribute("loggedIn", true);
	        addLoginCookie(response, username);

	        if (session.getAttribute("pendingCourseID") != null) {
	            return handlePendingCourse(session);
	        }
	        return new RedirectView("/coach_account_information");
	    } 

	    else if (adminLogin != null && adminLogin.getAdminPassword().equals(password)) {
	        session.setAttribute("admin", adminLogin);
	        session.setAttribute("loggedIn", true);
	        addLoginCookie(response, username);

	        return new RedirectView("/admin");
	    }

	    return new RedirectView("/login?error=invalid");
	}

	private void addLoginCookie(HttpServletResponse response, String username) {
	    Cookie loginCookie = new Cookie("user", username);
	    loginCookie.setMaxAge(1 * 24 * 60 * 60);
	    loginCookie.setPath("/");
	    response.addCookie(loginCookie);
	}

	private RedirectView handlePendingCourse(HttpSession session) {
	    String pendingCourseID = session.getAttribute("pendingCourseID").toString();
	    int coachCourseID = Integer.parseInt(pendingCourseID);
	    session.removeAttribute("pendingCourseID");
	    return new RedirectView("/trainers/orderCoachCourse?coachCourseID=" + coachCourseID);
	}

	@GetMapping("/loggedIn")
	public String showLoginPage(HttpServletRequest request, Model model) {
	    HttpSession session = request.getSession(false);
	    if (session != null && Boolean.TRUE.equals(session.getAttribute("loggedIn"))) {
	        Object user = session.getAttribute("user");
	        if (user instanceof Member) {
	            return "redirect:/account_information";
	        } else if (user instanceof CoachMember) {
	            return "redirect:/coach_account_information";
	        } else if (user instanceof AdminLogin) {
	            return "redirect:/admin";
	        }
	    }
	    return "frontend/login/login";
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
	    return "somePage";
	}

	@PostMapping("/register")
	public String register(
	        @RequestParam("account") String account, 
	        @RequestParam("password") String password,
	        @RequestParam("confirmPassword") String confirmPassword,
	        @RequestParam("name") String name, 
	        @RequestParam("phone") String phone,
	        @RequestParam("address") String address, 
	        @RequestParam("membership") String membership, 
	        HttpSession session,
	        Model model) {

	    // 檢查帳號是否已存在
	    if (memberService.existsByAccount(account) || coachMemberService.existsByAccount(account)) {
	        model.addAttribute("error", "該帳號已被註冊");
	        return "register"; // 返回註冊頁面並顯示錯誤
	    }

	    // 檢查密碼和確認密碼是否一致
	    if (!password.equals(confirmPassword)) {
	        model.addAttribute("error", "密碼和確認密碼不一致");
	        return "register"; // 返回註冊頁面並顯示錯誤
	    }

	    session.setAttribute("account", account);

	    if ("general".equals(membership)) {
	        Member member = new Member();
	        member.setAccount(account);
	        member.setPassword(password);
	        member.setName(name);
	        member.setPhone(phone);
	        member.setAddress(address);
	        member.setRegDate(new java.sql.Date(System.currentTimeMillis())); // 設置當前時間

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
	        coachMember.setRegDate(new java.sql.Date(System.currentTimeMillis())); // 設置當前時間

	        coachMemberService.addCoachMember(coachMember);

	        // 儲存 cMemberID 到 Session
	        session.setAttribute("cMemberID", coachMember.getCMemberID());

	        return "redirect:/coach_verify_email";
	    }

	    return "redirect:/register?error=true";
	}

	@PostMapping("/verifyEmail")
	public String saveEmail(@RequestParam("email") String email, HttpSession session, Model model) {

	    // 檢查電子郵件是否已存在
	    if (memberService.existsByEmail(email) || coachMemberService.existsByEmail(email)) {
	        model.addAttribute("error", "該電子郵件已被註冊");
	        return "verify_email"; // 返回驗證頁面並顯示錯誤
	    }

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

	@PostMapping("/sendTempPassword")
	@ResponseBody
	public String sendTempPassword(@RequestParam("email") String email, 
			@RequestParam("tempPassword") String tempPassword) {
		// 查找會員或教練會員
		Member member = memberService.findByEmail(email);
		if (member != null) {
			// 更新會員密碼
			member.setPassword(tempPassword);
			memberService.updateMember(member);
			
			// 發送臨時密碼郵件
			try {
				emailService.sendEmail(email, "您的臨時密碼", "您的臨時密碼是：" + tempPassword);
			} catch (MessagingException e) {
				return "發送郵件時出錯：" + e.getMessage();
			}
			
			return "臨時密碼已成功發送至您的郵箱";
		}
		
		CoachMember coachMember = coachMemberService.findByEmail(email);
		if (coachMember != null) {
			// 更新教練會員密碼
			coachMember.setPassword(tempPassword);
			coachMemberService.updateCoachMember(coachMember);
			
			// 發送臨時密碼郵件
			try {
				emailService.sendEmail(email, "您的臨時密碼", "您的臨時密碼是：" + tempPassword);
			} catch (MessagingException e) {
				return "發送郵件時出錯：" + e.getMessage();
			}
			
			return "臨時密碼已成功發送至您的郵箱";
		}
		
		return "無法找到與此電子郵件對應的會員";
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
	
	@GetMapping("/email/check")
    public ResponseEntity<Boolean> checkEmail(@RequestParam("email") String email) {
        // 檢查是否有一般會員使用該電子郵件
        boolean isEmailTaken = memberService.findByEmail(email) != null;

        // 如果該電子郵件未被一般會員使用，繼續檢查是否有教練會員使用該電子郵件
        if (!isEmailTaken) {
            isEmailTaken = coachMemberService.findByEmail(email) != null;
        }

        return ResponseEntity.ok(isEmailTaken);
    }
}