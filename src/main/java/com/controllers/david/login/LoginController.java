package com.controllers.david.login;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.model.CoachMemberRepository;
import com.tia102g3.coachmember.service.CoachMemberService;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;
import com.tia102g3.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        // 根據帳號查詢會員
        Member member = memberRepository.findByAccount(username);
        CoachMember coachMember = coachMemberRepository.findByAccount(username);

        // 檢查密碼並確定用戶身份
        if (member != null && member.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", member); // 設置會員到 session
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
            return new RedirectView("/coach_account_information"); // 教練會員頁面
        }

        // 如果身份驗證失敗，返回登入頁面，並帶上錯誤參數
        return new RedirectView("/login?error=invalid");
    }
    
    @PostMapping("/register")
    public String register(
            @RequestParam("account") String account,
            @RequestParam("password") String password,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address,
            @RequestParam("membership") String membership,
            HttpSession session) {
    	
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
    
    @PostMapping("/saveEmail")
    public String saveEmail(
            @RequestParam("email") String email,
            HttpSession session) {

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
