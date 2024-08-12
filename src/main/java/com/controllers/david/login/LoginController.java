package com.controllers.david.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.tia102g3.coachmember.model.CoachMember;
import com.tia102g3.coachmember.model.CoachMemberRepository;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;

@Controller
public class LoginController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CoachMemberRepository coachMemberRepository;

    @PostMapping("/login")
    public RedirectView login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        // 根據帳號查詢會員
        Member member = memberRepository.findByAccount(username);
        CoachMember coachMember = coachMemberRepository.findByAccount(username);

        // 檢查密碼並確定用戶身份
        if (member != null && member.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", member); // 設置會員到 session
            return new RedirectView("/account_information"); // 一般會員頁面
        } else if (coachMember != null && coachMember.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", coachMember); // 設置教練會員到 session
            return new RedirectView("/coach_account_information"); // 教練會員頁面
        }

        // 如果身份驗證失敗，返回登入頁面，並帶上錯誤參數
        return new RedirectView("/login?error=invalid");
    }
}
