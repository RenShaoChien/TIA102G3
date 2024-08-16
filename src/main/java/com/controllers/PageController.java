package com.controllers;



import java.util.List;

import javax.servlet.http.HttpSession;

import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;
import com.tia102g3.product.model.ProductService;
import com.tia102g3.product.model.ProductVO;

/**
 * ClassName： PageController package：com.controllers Description：
 *
 * @Author 任少騫
 * @Create 2024/8/2 上午12:17
 * @Version 1.0
 */
@Controller
public class PageController {
	
	@Autowired
	ProductService productservice;


    @Autowired
    private CoachCourseServiceImpl ccService;

//    @Autowired
//    private FoodService foodSvc;
//
//    @Autowired
//    private MenuService menuSvc;


    @GetMapping({"/", "/index"})
    public String indexPage(HttpSession session, Model model) {
        Boolean loggedIn = (Boolean) session.getAttribute("loggedIn");
        model.addAttribute("loggedIn", loggedIn != null && loggedIn);
        return "index";
    }

    @GetMapping("/trainers")
    public String trainers(Model model) {
        model.addAttribute("coachCourseList", ccService.findAllByStatus(CourseStatus.IN_PROGRESS));
        return "trainers";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }


//    @GetMapping("/menu")
//    public String menu(Model model) {
//        LikeFoodVO likeFoodVO = new LikeFoodVO();
//        List<MenuVO> menuVO = new ArrayList<>();
//        menuVO = menuSvc.getAll();
//        model.addAttribute("likeFoodVO", likeFoodVO);
//        model.addAttribute("FoodListData", foodSvc.getAll());
//        model.addAttribute("menuVO", menuVO);
//        return "menu";
//    }

//    @GetMapping("/menu")
//    public String menu(Model model) {
//        return "menu";
//    }


    @GetMapping("/member")
    public String member(Model model) {
        return "member";
    }


    @GetMapping("/pricing")
    public String pricing(Model model) {
        return "pricing";
    }
    
    @GetMapping("/productintro")
    public String productintro(Model model) {
        return "product/product_intro";
    }
    
//    @GetMapping("/shoppingcart")
//    public String shoppingcart(Model model) {
//        return "product/shopping_cart";
//    }
    
    @ModelAttribute("productList")
    protected List<ProductVO> productList(Model model){
    	List<ProductVO> products = productservice.getAll();
    	return products;
    }
    

    @GetMapping("/orderChecking")
    public String OrderChecking(Model model) {
        return "product/order_checking";
    }
    
    @GetMapping("/qa")
    public String qaPage() {
        return "qa"; 
    }

    
    @GetMapping("/contact")
    public String contactPage() {
        return "contact"; 
    }
    
    @GetMapping("/foodmap")
    public String foodmapPage() {
        return "foodmap"; 
    }
    
}
