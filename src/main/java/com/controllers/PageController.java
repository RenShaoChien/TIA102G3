package com.controllers;


import java.util.List;


import com.tia102g3.coachcourse.model.CourseStatus;
import com.tia102g3.coachcourse.service.CoachCourseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.product.model.ProductVO;
import com.tia102g3.product.model.ProductService;

import com.tia102g3.food.model.FoodService;
import com.tia102g3.food.model.FoodVO;
import com.tia102g3.likefood.model.LikeFoodVO;

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
    
    @Autowired
    private FoodService foodSvc;

    @GetMapping("/")
    public String index(Model model) {
        return "adminLogin";
    }
    
    @GetMapping("/index")
    public String about(Model model) {
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

    @GetMapping("/menu")
    public String menu(Model model) {
        LikeFoodVO likeFoodVO = new LikeFoodVO();
        model.addAttribute("likeFoodVO", likeFoodVO);
        model.addAttribute("FoodListData", foodSvc.getAll());
        return "menu";
    }
    
    @GetMapping("/pricing")
    public String pricing(Model model) {
        return "pricing";
    }
    
    @GetMapping("/productintro")
    public String productintro(Model model) {
        return "product/product_intro";
    }
    
    @GetMapping("/shoppingcart")
    public String shoppingcart(Model model) {
        return "product/shopping_cart";
    }
    
    @ModelAttribute("productList")
    protected List<ProductVO> productList(Model model){
    	List<ProductVO> products = productservice.getAll();
    	return products;
    }
    
    
}
