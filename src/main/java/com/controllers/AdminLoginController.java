package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tia102g3.adminlogin.service.AdminLoginService;

@Controller
public class AdminLoginController {

    @Autowired
    private AdminLoginService adminService;

    @GetMapping("/adminLogin")
    public String loginPage() {
        return "admin";
    }

    @PostMapping("/adminLogin")
    public String login(@RequestParam("adminUsername") String adminUsername,
                        @RequestParam("adminPassword") String adminPassword,
                        Model model) {
        if (adminService.validateAdmin(adminUsername, adminPassword)) {
            return "admin";
        } else {
            model.addAttribute("error", "帳號或密碼錯誤");
            return "adminLogin";
        }
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

