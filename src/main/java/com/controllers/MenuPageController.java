package com.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.controllers.mark.ProductInfo;
import com.controllers.mark.RedisService;
import com.tia102g3.adminlogin.service.AdminLoginService;
import com.tia102g3.food.model.FoodService;
import com.tia102g3.healthstatus.model.HealthStatusVO;
import com.tia102g3.likefood.model.LikeFoodVO;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;
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
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private MemberRepository memberSvc;

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
        
//        HttpSession session = request.getSession();
//        String adminUsername = (String) session.getAttribute("adminUsername");
//        Long adminId = adminSvc.getAdminId(adminUsername);
//        System.out.println("adminId: " + adminId);
//        System.out.println("adminUsername: " + session.getAttribute("adminUsername"));
        return "menu";
    }
    
    
    //========== test shopping cart ==========
    @GetMapping("/shoppingcart")
    public String shoppingcart(Model model) {
        List<ProductInfo> productList = redisService.getProductList("1234");
        model.addAttribute("productList", productList);
        
//        for(ProductInfo pinfo : productList) {
//            System.out.print("ID: " + pinfo.getId() + ",");
//            System.out.print("Name: " + pinfo.getName() + ",");
//            System.out.print("Quantity: " + pinfo.getQuantity() + ",");
//            System.out.println("Price: " + pinfo.getPrice());
//            
//        }
        
        return "product/shopping_cart";
    }
    
//    @GetMapping("/credit_card")
//    public String creditCard(Model model) {  
//        
//        return "product/credit_card";
//    }
    
    @PostMapping("/orderCheck")
    public String orderCheck(Model model) {
        List<ProductInfo> productList = redisService.getProductList("1234");
        model.addAttribute("productList", productList);
        Member member = memberSvc.getById(1);
        model.addAttribute("member",member);
        
        return "product/order_checking";
    }
    
//    @GetMapping("/delivery")
//    public String delivery(Model model) {        
//        
//        return "product/delivery";
//    }
    
    @GetMapping("/orderFinish")
    public String delivery(Model model) {        
        redisService.deleteProductList("1234");
        return "product/order_finish";
    }
    
    
    
}
