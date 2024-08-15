package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tia102g3.product.model.ProductVO;
import com.tia102g3.product.model.ProductService;

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
        return "trainers";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        return "menu";
    }

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
    
    @GetMapping("/shoppingcart")
    public String shoppingcart(Model model) {
        return "product/shopping_cart";
    }
    
    @ModelAttribute("productList")
    protected List<ProductVO> productList(Model model){
    	List<ProductVO> products = productservice.getAll();
    	return products;
    }
    
    @GetMapping("/orderChecking")
    public String OrderChecking(Model model) {
        return "product/order_checking";
    }
    
    
}
