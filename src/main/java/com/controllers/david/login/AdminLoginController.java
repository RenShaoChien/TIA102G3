package com.controllers.david.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.adminlogin.service.AdminLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminService;

    @GetMapping("/adminLogin")
    public String loginPage() {
        return "adminLogin";
    }

    @PostMapping("/adminLogin")
    public String login(@RequestParam("adminUsername") String adminUsername,
                        @RequestParam("adminPassword") String adminPassword,
                        Model model, HttpServletRequest request) {
        if (adminService.validateAdmin(adminUsername, adminPassword)) {
            Long adminId = adminService.getAdminId(adminUsername);
            if (adminId != null) {
                HttpSession session = request.getSession();
                session.setAttribute("adminUsername", adminUsername);

                // 添加管理者名稱到模型中
                String adminName = adminService.getAdminName(adminUsername);
                session.setAttribute("adminName", adminName);
                
                String location = (String) session.getAttribute("location");
                if (location != null) {
                    session.removeAttribute("location");
                    return "redirect:" + location;
                }

                return "redirect:/admin";
            } else {
                model.addAttribute("error", "用戶不存在");
                return "adminLogin";
            }
        } else {
            model.addAttribute("error", "帳號或密碼錯誤");
            return "adminLogin";
        }
    }

    @GetMapping("/adminLogout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 使會話無效
        }
        return "redirect:/adminLogin"; // 重定向到登錄頁面
    }

    @GetMapping("/adminRegister")
    public String registerPage() {
        return "adminRegister";
    }

    @PostMapping("/adminRegister")
    public String register(@RequestParam("adminName") String adminName,
                           @RequestParam("adminUsername") String adminUsername,
                           @RequestParam("adminPassword") String adminPassword,
                           @RequestParam("adminEmail") String adminEmail,
                           Model model) {
        try {
            adminService.registerAdmin(adminName, adminUsername, adminPassword, adminEmail);
            return "redirect:/adminLogin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "adminRegister";
        }
    }
}
