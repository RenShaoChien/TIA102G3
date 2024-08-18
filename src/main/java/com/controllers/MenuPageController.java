package com.controllers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.controllers.mark.ProductInfo;
import com.controllers.mark.RedisService;
import com.tia102g3.adminlogin.service.AdminLoginService;
import com.tia102g3.food.model.FoodService;
import com.tia102g3.healthstatus.model.HealthStatusVO;
import com.tia102g3.likefood.model.LikeFoodService;
import com.tia102g3.likefood.model.LikeFoodVO;
import com.tia102g3.member.model.Member;
import com.tia102g3.member.model.MemberRepository;
import com.tia102g3.menu.model.MenuService;
import com.tia102g3.menu.model.MenuVO;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;

@Controller
public class MenuPageController {
    
    @Autowired
    private FoodService foodSvc;
    
    @Autowired
    private MenuService menuSvc;
    
    @Autowired
    private LikeFoodService likeFoodService;
    
    @Autowired
    private AdminLoginService adminSvc;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private MemberRepository memberSvc;
    
    @Autowired
    private ProductService productservice;

    @GetMapping("/menu")
    public String menu(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String adminUsername = (String) session.getAttribute("adminUsername");
        Long adminId = adminSvc.getAdminId(adminUsername);
        System.out.println("adminId: " + adminId);
        System.out.println("adminUsername: " + session.getAttribute("adminUsername"));
        
        int memberID = -1;
     // get member data
        if (session != null) {
            // 從 session 中取出 member 物件
            Member member = (Member) session.getAttribute("user");
            if(member != null) {
                memberID = member.getMemberID();
                System.out.println("ID: " + member.getMemberID());
                System.out.println("Account: " + member.getAccount());             
            }
        }      
        
        LikeFoodVO likeFoodVO = new LikeFoodVO();
        HealthStatusVO healthStatusVO = new HealthStatusVO();
//        List<MenuVO> menuVO = new ArrayList<>();
//        menuVO = menuSvc.getAll();
        List<MenuVO> menuVO = menuSvc.getAll();

        // 隨機打亂列表
        Collections.shuffle(menuVO);

        // 確保列表長度至少為四，然後選取前四個
        List<MenuVO> randomMenuVO = menuVO.stream()
                                          .limit(4)
                                          .collect(Collectors.toList());
        
        
        // get insert data
        likeFoodVO.setMemberID(memberID);
        model.addAttribute("likeFoodVO", likeFoodVO);
        model.addAttribute("healthStatusVO", healthStatusVO);
        
        // display front-end data        
//        List<LikeFoodVO> likeFoodList = likeFoodService.getByMemberID(memberID);
        model.addAttribute("LikeFoodList", likeFoodService.getByMemberID(memberID));
        model.addAttribute("FoodListData", foodSvc.getAll());
//        model.addAttribute("menuVO", menuVO);
        model.addAttribute("menuVO", randomMenuVO);
        model.addAttribute("loginID", memberID);
        
        return "menu";
    }
    
    @PostMapping("/updateMenu")
    public String updateMenu(Model model) {
     // 確保列表長度至少為四，然後選取前四個
        List<MenuVO> randomMenuVO = menuSvc.getAll().stream()
                                          .limit(4)
                                          .collect(Collectors.toList());
        model.addAttribute("menuVO", randomMenuVO);
        
        return "redirect:/menu";
    }
    
    
    //========== test shopping cart ==========
    
    @GetMapping("/pricing")
    public String pricing(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        int memberID = 1;
        if (session != null) {
            // 從 session 中取出 member 物件
            Member member = (Member) session.getAttribute("user");
            if(member != null) {
                memberID = member.getMemberID();
                model.addAttribute("memberID", memberID);
//                System.out.println("ID: " + member.getMemberID());
//                System.out.println("Account: " + member.getAccount());             
            }
        }   

        return "pricing";
    }
    
    
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
    public String orderCheck(Model model, HttpServletRequest request) {
        // get member data
        HttpSession session = request.getSession();
        int memberID = 1;
        if (session != null) {
            // 從 session 中取出 member 物件
            Member member = (Member) session.getAttribute("user");
            if(member != null) {
                memberID = member.getMemberID();
                System.out.println("ID: " + member.getMemberID());
                System.out.println("Account: " + member.getAccount());             
            }
        }   
        
        List<ProductInfo> productList = redisService.getProductList(String.valueOf(memberID));
        int totalPrice = 0;
        for(ProductInfo product : productList) {
            totalPrice = totalPrice + product.getPrice() * product.getQuantity();
        }
        
        model.addAttribute("productList", productList);
        model.addAttribute("totalPrice", totalPrice);
        
        Member member = memberSvc.getById(memberID);
        model.addAttribute("member",member);
        
        return "product/order_checking";
    }
    
//    @GetMapping("/delivery")
//    public String delivery(Model model) {        
//        
//        return "product/delivery";
//    }
    
    @GetMapping("/orderFinish")
    public String delivery(Model model, HttpServletRequest request) {
     // get member data
        HttpSession session = request.getSession();
        int memberID = 1;
        if (session != null) {
            // 從 session 中取出 member 物件
            Member member = (Member) session.getAttribute("user");
            if(member != null) {
                memberID = member.getMemberID();
                System.out.println("ID: " + member.getMemberID());
                System.out.println("Account: " + member.getAccount());             
            }
        }
        
        redisService.deleteProductList(String.valueOf(memberID));
        return "product/order_finish";
    }
    
    
    @ModelAttribute("productList")
    protected List<ProductVO> productList(Model model){
        List<ProductVO> products = productservice.getAll();
        return products;
    }
    
//    @ModelAttribute("menuVO")
//    protected List<MenuVO> menuVO(Model model,HttpServletRequest request){
//        HttpSession session = request.getSession();
//        int memberID = -1;
//     // get member data
//        if (session != null) {
//            // 從 session 中取出 member 物件
//            Member member = (Member) session.getAttribute("user");
//            if(member != null) {
//                memberID = member.getMemberID();
//                System.out.println("ID: " + member.getMemberID());
//                System.out.println("Account: " + member.getAccount());             
//            }
//        }      
//        List<MenuVO> randomMenuVO = menuSvc.getAll();
//
//        // 隨機打亂列表
//        Collections.shuffle(randomMenuVO);
//
//        // 確保列表長度至少為四，然後選取前四個
//        List<MenuVO> menuVO = randomMenuVO.stream()
//                                          .limit(4)
//                                          .collect(Collectors.toList());
//        
//        
////        List<MenuVO> menuVO = productservice.getAll();
//        return menuVO;
//    }
    
    
    
}
