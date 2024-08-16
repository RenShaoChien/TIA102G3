package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tia102g3.adminlogin.service.AdminLoginService;
import com.tia102g3.food.model.FoodService;
import com.tia102g3.healthstatus.model.HealthStatusVO;
import com.tia102g3.likefood.model.LikeFoodVO;
import com.tia102g3.menu.model.MenuService;
import com.tia102g3.menu.model.MenuVO;

@Controller
public class MenuPageController {
    
    @Autowired
    private FoodService foodSvc;
    
    @Autowired
    private MenuService menuSvc;
    
    @Autowired
    private AdminLoginService adminSvc;

    @GetMapping("/menu")
    public String menu(Model model, HttpServletRequest request) {
        LikeFoodVO likeFoodVO = new LikeFoodVO();
        HealthStatusVO healthStatusVO = new HealthStatusVO();
        List<MenuVO> menuVO = new ArrayList<>();
        menuVO = menuSvc.getAll();
        model.addAttribute("likeFoodVO", likeFoodVO);
        model.addAttribute("healthStatusVO", healthStatusVO);
        model.addAttribute("FoodListData", foodSvc.getAll());
        model.addAttribute("menuVO", menuVO);
        
        HttpSession session = request.getSession();
        String adminUsername = (String) session.getAttribute("adminUsername");
        Long adminId = adminSvc.getAdminId(adminUsername);
        System.out.println("adminId: " + adminId);
        System.out.println("adminUsername: " + session.getAttribute("adminUsername"));
        return "menu";
    }
    
    
}
